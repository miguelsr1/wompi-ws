package sv.com.wompi.ws.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;
import sv.com.wompi.ws.dto.TokenResponse;


@RegisterRestClient(configKey = "wompi-id")
public interface WompiClientId {

    @POST
    @Path("/connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    TokenResponse getToken(
            @RestForm("grant_type") String grantType,
            @RestForm("client_id") String clientId,
            @RestForm("client_secret") String clientSecret,
            @RestForm("audience") String audience
    );
}
