package sv.com.wompi.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCreditoDebido {
    @ToString.Exclude
    private String numeroTarjeta;
    @ToString.Exclude
    private String cvv;
    private int mesVencimiento;
    private int anioVencimiento;
}
