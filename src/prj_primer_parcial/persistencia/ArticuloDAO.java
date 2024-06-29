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
        String sql = "INSERT INTO inv_sim_1 (codigo, nombre_inv, precio_inv) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, articulo.getCodigo());
            ps.setString(2, articulo.getNombre());
            ps.setString(3, articulo.getPrecio());
            ps.executeUpdate();
        }
    }

    public void actualizarArticulo(Articulo articulo) throws SQLException {
    String sql = "UPDATE inv_sim_1 SET codigo = ?, nombre_inv = ?, precio_inv = ? WHERE id_iv_sim1 = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, articulo.getCodigo());
        ps.setString(2, articulo.getNombre());
        ps.setString(3, articulo.getPrecio());
        ps.setInt(4, articulo.getId()); // Aquí se usa el id del artículo
        ps.executeUpdate();
    }
}


    public void eliminarArticulo(String articulo) throws SQLException {
        String sql = "DELETE FROM inv_sim_1 WHERE codigo = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, articulo);
            ps.executeUpdate();
        }
    }
    
    public List<Articulo> obtenerArticulos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM inv_sim_1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getString("codigo"),
                        rs.getString("nombre_inv"),
                        rs.getString("precio_inv")
                );
                articulos.add(articulo);
            }
        }
        return articulos;
    }

    public List<Articulo> buscarArticulos(String articulo) throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM inv_sim_1 WHERE codigo LIKE ? OR nombre_inv = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + articulo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Articulo user = new Articulo(
                            rs.getString("codigo"),
                            rs.getString("nombre_inv"),
                            rs.getString("precio_inv")
                    );
                    articulos.add(user);
                }
            }
        }
        return articulos;
    }
}
