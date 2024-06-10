/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_primer_parcial.persistencia;

/**
 *
 * @author keyne
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prj_primer_parcial.negocio.Facturacion;


public class FacturacionDAO {

    public void insertarFacturacion(Facturacion facturacion) throws SQLException {
        String sql = "INSERT INTO fact_sim_1 (ruc, nombre_fac_cli, dir) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, facturacion.getRuc());
            ps.setString(2, facturacion.getNombre());
            ps.setString(3, facturacion.getDirrecion()); 
            ps.executeUpdate();
        }
    }

    public void actualizarFacturacion(Facturacion facturacion) throws SQLException {
        String sql = "UPDATE fact_sim_1 SET ruc = ?, nombre_fac_cli = ?, dir= ?, WHERE ruc = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, facturacion.getRuc());
            ps.setString(2, facturacion.getNombre());
            ps.setString(3, facturacion.getDirrecion()); // Corrige esta línea
            ps.executeUpdate();
        }
    }

    public void eliminarFacturacion(String facturacion) throws SQLException {
        String sql = "DELETE FROM fact_sim_1 WHERE ruc = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, facturacion);
            ps.executeUpdate();
        }
    }
    
    public List<Facturacion> obtenerFacturaciones() throws SQLException {
        List<Facturacion> facturaciones = new ArrayList<>();
        String sql = "SELECT * FROM fact_sim_1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Facturacion facturacion= new Facturacion(
                        rs.getString("ruc"),
                        rs.getString("nombre_fac_cli"),
                        rs.getString("dir")
                );
                facturaciones.add(facturacion);
            }
        }
        return facturaciones;
    }
    public List<Facturacion> buscarFacturacion(String ruc) throws SQLException {
        List<Facturacion> facturaciones = new ArrayList<>();
        String sql = "SELECT * FROM fact_sim_1 WHERE ruc LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + ruc + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Facturacion facturacion = new Facturacion(
                        rs.getString("ruc"),
                        rs.getString("nombre_fac_cli"),
                        rs.getString("dir")
                    );
                    facturaciones.add(facturacion);
                }
            }
        }
        return facturaciones;
    }
}