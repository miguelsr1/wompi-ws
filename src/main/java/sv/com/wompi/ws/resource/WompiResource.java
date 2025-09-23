package sv.com.wompi.ws.resource;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import sv.com.wompi.ws.dto.Region;
import sv.com.wompi.ws.dto.Transaccion3DSRequest;
import sv.com.wompi.ws.dto.Transaccion3DSResponse;
import sv.com.wompi.ws.service.WompiService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/wompi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WompiResource {

    @Inject
    WompiService wompiService;

    @POST
    @Path("/transaccion/3ds")
    @Operation(summary = "Crea una nueva transacción 3DS")
    @APIResponse(
            responseCode = "200",
            description = "Transacción creada exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Transaccion3DSResponse.class))
    )
    public Response crearTransaccion3DS(
            @RequestBody(required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaccion3DSRequest.class)))
            Transaccion3DSRequest request) {
        return wompiService.crearTransaccion3DS(request);
    }

    @GET
    @Path("/regiones")
    @Operation(summary = "Obtiene la lista de regiones y territorios")
    @APIResponse(
            responseCode = "200",
            description = "Lista de regiones obtenida exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Region[].class))
    )
    public List<Region> getRegiones() {
        return wompiService.getRegiones();
    }
}
