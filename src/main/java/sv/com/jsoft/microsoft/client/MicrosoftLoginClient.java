package sv.com.jsoft.microsoft.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;
import sv.com.jsoft.microsoft.payload.TokenResponse;

@RegisterRestClient(configKey = "graph-microsoft-login")
public interface MicrosoftLoginClient {

    @POST
    @Path("{tenantId}/oauth2/v2.0/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    TokenResponse getToken(
            @PathParam("tenantId") String tenantId,
            @RestForm("client_id") String clientId,
            @RestForm("scope") String scope,
            @RestForm("client_secret") String clientSecret,
            @RestForm("grant_type") String grantType
    );

}
