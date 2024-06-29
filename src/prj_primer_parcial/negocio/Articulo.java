package prj_primer_parcial.negocio;

import java.io.Serializable;

public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String codigo;
    private String nombre;
    private String precio;

    // Constructor
    public Articulo(String codigo, String nombre, String precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    // Constructor con id
    public Articulo(int id, String codigo, String nombre, String precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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
