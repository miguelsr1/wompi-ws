package sv.com.jsoft.wompi.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Transaccion3DSResponse {
    private String idTransaccion;
    private boolean esReal;
    private String urlCompletarPago3Ds;
    private double monto;

    // Getters y Setters
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public boolean isEsReal() {
        return esReal;
    }

    public void setEsReal(boolean esReal) {
        this.esReal = esReal;
    }

    public String getUrlCompletarPago3Ds() {
        return urlCompletarPago3Ds;
    }

    public void setUrlCompletarPago3Ds(String urlCompletarPago3Ds) {
        this.urlCompletarPago3Ds = urlCompletarPago3Ds;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
