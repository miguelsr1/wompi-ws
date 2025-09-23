package sv.com.wompi.ws.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDto {
    private String servicioError;
    private List<String> mensajes;
    private String subTipoError;
}
