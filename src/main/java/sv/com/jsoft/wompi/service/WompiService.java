package sv.com.jsoft.wompi.service;

import com.google.gson.Gson;
import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import sv.com.jsoft.wompi.client.WompiClient;
import sv.com.jsoft.wompi.client.WompiClientId;
import sv.com.jsoft.wompi.config.WompiConfig;
import sv.com.jsoft.wompi.dto.Region;
import sv.com.jsoft.wompi.dto.TokenResponse;
import sv.com.jsoft.wompi.dto.Transaccion3DSRequest;
import sv.com.jsoft.wompi.dto.Transaccion3DSResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import sv.com.jsoft.wompi.dto.errors.ResponseErrorDto;

import java.util.List;

@ApplicationScoped
public class WompiService {

    @Inject
    @RestClient
    WompiClient wompiClient;
    @Inject
    @RestClient
    WompiClientId wompiClientId;

    @Inject
    WompiConfig config;

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
            TokenResponse response = wompiClientId.getToken(
                    config.getGrantType(),
                    config.getClientId(),
                    config.getClientSecret(),
                    config.getAudience()
            );

            this.token = response.getToken_type() + " " + response.getAccess_token();
            // Refresh token 5 minutes before it expires
            this.tokenExpirationTime = System.currentTimeMillis() + (response.getExpires_in() - 300) * 1000L;

            Log.info("Wompi token refreshed successfully");
        } catch (Exception e) {
            Log.error("Error refreshing Wompi token", e);
            throw new RuntimeException("Failed to obtain authentication token", e);
        }
    }

    public Response crearTransaccion3DS(Transaccion3DSRequest request) {
        try {
            Log.info("REQUEST PAGO: " + new Gson().toJson(request));
            Transaccion3DSResponse response = wompiClient.crearTransaccion3DS(getToken(), request);
            Log.info("RESPONSE PAGO: " + new Gson().toJson(response));

            return Response.status(Response.Status.OK).entity(response).build();
        } catch (ClientWebApplicationException e) {
            ResponseErrorDto responseErrorDto;
            switch (e.getResponse().getStatus()) {
                case 400:
                    responseErrorDto = e.getResponse().readEntity(ResponseErrorDto.class);
                    Log.error("ERROR EN LA TRANSACCION: " + responseErrorDto);

                    return Response.status(Response.Status.BAD_REQUEST).entity(responseErrorDto).build();
                case 404:
                    responseErrorDto = e.getResponse().readEntity(ResponseErrorDto.class);
                    Log.error("ERROR EN LA TRANSACCION: " + responseErrorDto);

                    return Response.status(Response.Status.BAD_REQUEST).entity(responseErrorDto).build();
                case 500:
                    break;
            }
            Log.error("Error creating 3DS transaction", e);
            throw new RuntimeException("Failed to create 3DS transaction", e);
        }
    }

    public List<Region> getRegiones() {
        try {
            return wompiClient.getRegiones(getToken());
        } catch (Exception e) {
            Log.error("Error getting regions", e);
            throw new RuntimeException("Failed to get regions", e);
        }
    }
}
