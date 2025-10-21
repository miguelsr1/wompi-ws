package sv.com.jsoft.microsoft.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sv.com.jsoft.microsoft.client.MicrosoftLoginClient;
import sv.com.jsoft.microsoft.payload.MailRequest;
import sv.com.jsoft.microsoft.payload.SendMailRequest;
import sv.com.jsoft.microsoft.payload.TokenResponse;

@ApplicationScoped
public class MicrosoftService {
    @Inject
    @RestClient
    MicrosoftLoginClient microsoftLoginClient;

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


    public void sendMail(MailRequest mailRequest){
        SendMailRequest sendMailRequest = new SendMailRequest();

    }
}
