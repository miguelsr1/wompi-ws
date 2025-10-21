package sv.com.jsoft.wompi.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Territorio {
    private String id;
    private String nombre;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
