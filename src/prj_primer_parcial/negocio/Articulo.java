package prj_primer_parcial.negocio;

import java.io.Serializable;

public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String precio;

    // Constructor
    public Articulo(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
