// Usuario.java
package prj_primer_parcial.negocio;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String password;

    // Constructor
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
