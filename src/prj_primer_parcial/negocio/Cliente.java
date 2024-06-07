// Cliente.java
package prj_primer_parcial.negocio;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ruc;
    private String nombre;
    private String direccion;

    // Constructor con tres argumentos
    public Cliente(String ruc, String nombre, String direccion) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
