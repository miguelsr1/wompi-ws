package sv.com.jsoft.microsoft.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import sv.com.jsoft.microsoft.payload.Error400Response;
import sv.com.jsoft.microsoft.payload.Error401Response;
import sv.com.jsoft.microsoft.payload.MailRequest;
import sv.com.jsoft.microsoft.service.MicrosoftService;

@Path("/api/microsoft")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Microsoft Graph API", description = "Endpoints para integración con Microsoft Graph API")
public class MicrosoftResource {

    @Inject
    MicrosoftService microsoftService;

    @POST
    @Path("/send-mail")
    @Operation(
            summary = "Enviar correo electrónico",
            description = "Envía un correo electrónico a través de Microsoft Graph API con soporte para adjuntos"
    )
    @RequestBody(
            description = "Datos del correo a enviar",
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = MailRequest.class)
            )
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Correo enviado exitosamente"
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Solicitud inválida - Error en los datos enviados",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Error400Response.class)
                    )
            ),
            @APIResponse(
                    responseCode = "401",
                    description = "No autorizado - Token de autenticación inválido o expirado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Error401Response.class)
                    )
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    public Response sendMail(MailRequest mailRequest) {
        try {
            return microsoftService.sendMail(mailRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
