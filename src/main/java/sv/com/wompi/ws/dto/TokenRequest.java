package sv.com.wompi.ws.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TokenRequest {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String audience;

    // Getters y Setters
    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
