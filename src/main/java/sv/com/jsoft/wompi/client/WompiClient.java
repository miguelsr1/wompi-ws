package sv.com.jsoft.wompi.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sv.com.jsoft.wompi.dto.Transaccion3DSRequest;
import sv.com.jsoft.wompi.dto.Transaccion3DSResponse;
import sv.com.jsoft.wompi.dto.Region;
import java.util.List;

@RegisterRestClient(configKey = "wompi-api")
public interface WompiClient {

    @POST
    @Path("/TransaccionCompra/3Ds")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Transaccion3DSResponse crearTransaccion3DS(
            @HeaderParam("Authorization") String authorization,
            Transaccion3DSRequest request
    );

    @GET
    @Path("/api/Regiones")
    @Produces(MediaType.APPLICATION_JSON)
    List<Region> getRegiones(
            @HeaderParam("Authorization") String authorization
    );
}
