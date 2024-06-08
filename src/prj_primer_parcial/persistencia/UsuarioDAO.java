package prj_primer_parcial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prj_primer_parcial.negocio.Usuario;

public class UsuarioDAO {

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO user (usuario, password) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
        }
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE user SET usuario = ?, password = ? WHERE usuario = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getUsuario()); // Corrige esta línea
            ps.executeUpdate();
        }
    }

    public void eliminarUsuario(String id_use) throws SQLException {
        String sql = "DELETE FROM user WHERE id_use = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id_use);
            ps.executeUpdate();
        }
    }

    public List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("usuario"),
                        rs.getString("password")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public List<Usuario> buscarUsuarios(String usuario) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE usuario LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + usuario + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario user = new Usuario(
                            rs.getString("usuario"),
                            rs.getString("password")
                    );
                    usuarios.add(user);
                }
            }
        }
        return usuarios;
    }
}
