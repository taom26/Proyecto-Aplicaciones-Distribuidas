package prj_primer_parcial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prj_primer_parcial.negocio.Cliente;

public class ClienteDAO {

    public void insertarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (ruc, nombre, direccion) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getRuc());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.executeUpdate();
        }
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nombre = ?, direccion = ? WHERE ruc = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getRuc());
            ps.executeUpdate();
        }
    }

    public void eliminarCliente(String ruc) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE ruc = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ruc);
            ps.executeUpdate();
        }
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("ruc"),
                        rs.getString("nombre"),
                        rs.getString("direccion")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
