package sv.com.jsoft.microsoft.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sv.com.jsoft.microsoft.client.MicrosfotMailClient;
import sv.com.jsoft.microsoft.client.MicrosoftLoginClient;
import sv.com.jsoft.microsoft.payload.*;

@ApplicationScoped
public class MicrosoftService {
    @Inject
    @RestClient
    MicrosoftLoginClient microsoftLoginClient;


    @Inject
    @RestClient
    MicrosfotMailClient microsoftMailClient;

    @ConfigProperty(name = "graph-microsoft.auth.tenant-id")
    String tenantId;

    @ConfigProperty(name = "graph-microsoft.auth.client-id")
    String clientId;

    @ConfigProperty(name = "graph-microsoft.auth.client-secret")
    String clientSecret;

    @ConfigProperty(name = "graph-microsoft.auth.scope")
    String scope;

    @ConfigProperty(name = "graph-microsoft.auth.grant-type")
    String grantType;

    @ConfigProperty(name = "graph-microsoft.auth.from")
    String from;

    private String token;
    private long tokenExpirationTime;

    public String getToken() {
        if (token == null || System.currentTimeMillis() > tokenExpirationTime) {
            refreshToken();
        }
        return token;
    }

    private synchronized void refreshToken() {
        try {
            TokenResponse response = microsoftLoginClient.getToken(tenantId, clientId, scope, clientSecret, grantType);

            this.token = response.access_token();
            // Refresh token 5 minutes before it expires
            this.tokenExpirationTime = System.currentTimeMillis() + (response.expires_in() - 300) * 1000L;

            Log.info("Microsoft token refreshed successfully");
        } catch (Exception e) {
            Log.error("Error refreshing Microsoft token", e);
            throw new RuntimeException("Failed to obtain authentication token", e);
        }
    }

    public Response sendMail(MailRequest mailRequest) throws JsonProcessingException {
        SendMailRequest request = castRequest(mailRequest);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // para formatear el JSON

        String json = mapper.writeValueAsString(request);

        Response response = microsoftMailClient.sendMail(getToken(), from, json);

        return
                switch (response.getStatus()) {
                    case 202 -> Response.status(Response.Status.OK).build();

                    case 400 -> Response.status(Response.Status.BAD_REQUEST)
                            .entity(response.readEntity(Error400Response.class))
                            .build();

                    case 401 -> Response.status(Response.Status.UNAUTHORIZED)
                            .entity(response.readEntity(Error401Response.class))
                            .build();
                    default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .build();

                };
    }


    private SendMailRequest castRequest(MailRequest mailRequest) {
        // Validaciones
        if (mailRequest == null) {
            throw new IllegalArgumentException("MailRequest cannot be null");
        }
        if (mailRequest.getTo() == null || mailRequest.getTo().isBlank()) {
            throw new IllegalArgumentException("Recipient email cannot be null or empty");
        }
        if (mailRequest.getSubject() == null || mailRequest.getSubject().isBlank()) {
            throw new IllegalArgumentException("Subject cannot be null or empty");
        }
        if (mailRequest.getBody() == null || mailRequest.getBody().isBlank()) {
            throw new IllegalArgumentException("Body cannot be null or empty");
        }

        SendMailRequest sendMailRequest = new SendMailRequest();

        sendMailRequest.getMessage().setSubject(mailRequest.getSubject());

        sendMailRequest.getMessage().getBody().setContent(mailRequest.getBody());
        sendMailRequest.getMessage().getBody().setContentType("html");

        sendMailRequest.getMessage()
                .getToRecipients()
                .add(new Recipient(new EmailAddress(mailRequest.getTo())));

        // Protección contra null en attachments
        if (mailRequest.getAttachments() != null && !mailRequest.getAttachments().isEmpty()) {
            mailRequest.getAttachments().forEach(attachment -> {
                sendMailRequest.getMessage()
                        .getAttachments()
                        .add(new Attachment("#microsoft.graph.fileAttachment",
                                attachment.getName(),
                                attachment.getContentType(),
                                attachment.getBase64File()));
            });
        }

        sendMailRequest.setSaveToSentItems("true");

        return sendMailRequest;
    }
}
