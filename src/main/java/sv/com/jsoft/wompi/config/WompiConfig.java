package sv.com.jsoft.wompi.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WompiConfig {

    @ConfigProperty(name = "wompi.api.base-url", defaultValue = "https://api.wompi.sv/")
    String baseUrl;

    @ConfigProperty(name = "wompi.auth.client-id")
    String clientId;

    @ConfigProperty(name = "wompi.auth.client-secret")
    String clientSecret;

    @ConfigProperty(name = "wompi.auth.audience", defaultValue = "wompi_api")
    String audience;

    @ConfigProperty(name = "wompi.auth.grant-type", defaultValue = "client_credentials")
    String grantType;

    // Getters
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getAudience() {
        return audience;
    }

    public String getGrantType() {
        return grantType;
    }
}
