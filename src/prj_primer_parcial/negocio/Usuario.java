package prj_primer_parcial.negocio;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Id;
    private String usuario;
    private String password;

    // Constructor
    public Usuario(String Id, String usuario, String password) {
        this.Id = Id;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    
    public String getId() {
        return Id;
    }
    
    public void setId(String Id) {
        this.Id = Id;
    }
    
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
