package sv.com.jsoft.microsoft.payload;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Respuesta de error 401 de Microsoft Graph API")
public record Error401Response(
        @Schema(description = "Detalles del error de autenticación", required = true)
        Error401Detail error
) {
    @Schema(description = "Detalles específicos del error 401")
    record Error401Detail(
            @Schema(description = "Código de error", example = "InvalidAuthenticationToken")
            String code,
            @Schema(description = "Mensaje descriptivo del error", example = "Access token is empty.")
            String message,
            @Schema(description = "Fecha y hora del error", example = "2025-10-20T21:07:23")
            String date,
            @Schema(description = "ID de la solicitud del servidor", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
            String requestId,
            @Schema(description = "ID de la solicitud del cliente", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
            String clientRequestId
    ) {
    }
}
