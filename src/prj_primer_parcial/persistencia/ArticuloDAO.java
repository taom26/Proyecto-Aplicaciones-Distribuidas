/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_primer_parcial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prj_primer_parcial.negocio.Articulo;

/**
 *
 * @author bptec
 */
public class ArticuloDAO {

    public void insertarArticulo(Articulo articulo) throws SQLException {
        String sql = "INSERT INTO inv_sim_1 (nombre_inv, precio_inv) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getPrecio());
            ps.executeUpdate();
        }
    }

    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String sql = "UPDATE inv_sim_1 SET nombre_inv = ?, precio_inv = ? WHERE nombre_inv = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getPrecio());
            ps.executeUpdate();
        }
    }

    public void eliminarArticulo(String articulo) throws SQLException {
        String sql = "DELETE FROM inv_sim_1 WHERE nombre_inv = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, articulo);
            ps.executeUpdate();
        }
    }

    public List<Articulo> buscarArticulos(String articulo) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM inv_sim_1 WHERE nombre_inv LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + articulo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Articulo user = new Articulo(
                            rs.getString("articulo"),
                            rs.getString("precio")
                    );
                    articulos.add(user);
                }
            }
        }
        return articulos;
    }
}
