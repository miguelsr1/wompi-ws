package sv.com.jsoft.wompi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion3DSRequest {
    private TarjetaCreditoDebido tarjetaCreditoDebido;
    private double monto;
    private String urlRedirect;
    private String nombre;
    private String apellido;
    private String email;
    private String ciudad;
    private String direccion;
    private String idPais;
    private String idRegion;
    private String codigoPostal;
    private String telefono;

}
