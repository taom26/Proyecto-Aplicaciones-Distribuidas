/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_primer_parcial.negocio;

import java.io.Serializable;

/**
 *
 * @author keyne
 */
public class Facturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String Ruc;
    private String nombre;
    private String Dirrecion;

    // Constructor
    public Facturacion(String Ruc, String nombre, String Dirrecion) {
        this.Ruc = Ruc;
        this.nombre = nombre;
        this.Dirrecion = Dirrecion;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String Ruc) {
        this.Ruc = Ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirrecion() {
        return Dirrecion;
    }

    public void setDirrecion(String Dirrecion) {
        this.Dirrecion = Dirrecion;
    }
}