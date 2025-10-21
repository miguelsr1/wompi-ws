package sv.com.jsoft.wompi.resource;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import sv.com.jsoft.wompi.dto.errors.ResponseErrorDto;
import sv.com.jsoft.wompi.dto.Region;
import sv.com.jsoft.wompi.dto.Transaccion3DSRequest;
import sv.com.jsoft.wompi.dto.Transaccion3DSResponse;
import sv.com.jsoft.wompi.service.WompiService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/wompi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Wompi API", description = "Endpoints para procesamiento de pagos con Wompi")
public class WompiResource {

    @Inject
    WompiService wompiService;

    @POST
    @Path("/transaccion/3ds")
    @Operation(
            summary = "Crear transacción 3DS",
            description = "Crea una nueva transacción con autenticación 3D Secure (3DS) a través de Wompi"
    )
    @RequestBody(
            description = "Datos de la transacción 3DS a crear",
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Transaccion3DSRequest.class)
            )
    )
    @APIResponse(
            responseCode = "200",
            description = "Transacción 3DS creada exitosamente",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Transaccion3DSResponse.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Solicitud inválida - Error en los datos de la transacción o recurso no encontrado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ResponseErrorDto.class)
            )
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la transacción"
    )
    public Response crearTransaccion3DS(Transaccion3DSRequest request) {
        return wompiService.crearTransaccion3DS(request);
    }

    @GET
    @Path("/regiones")
    @Operation(
            summary = "Obtener regiones y territorios",
            description = "Obtiene la lista completa de regiones y territorios disponibles para transacciones"
    )
    @APIResponse(
            responseCode = "200",
            description = "Lista de regiones obtenida exitosamente",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Region[].class)
            )
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno del servidor al obtener las regiones"
    )
    public List<Region> getRegiones() {
        return wompiService.getRegiones();
    }
}
