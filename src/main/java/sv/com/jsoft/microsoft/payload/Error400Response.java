package sv.com.jsoft.microsoft.payload;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Respuesta de error 400 de Microsoft Graph API")
public record Error400Response(
        @Schema(description = "Detalles del error de validación", required = true)
        ErrorDetail error
) {
    @Schema(description = "Detalles específicos del error 400")
    record ErrorDetail(
            @Schema(description = "Código de error", example = "BadRequest")
            String code,
            @Schema(description = "Mensaje descriptivo del error", example = "Invalid request body or parameters")
            String message
    ) {

    }
}
