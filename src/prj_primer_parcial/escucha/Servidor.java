package prj_primer_parcial.escucha;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import prj_primer_parcial.negocio.Usuario;
import prj_primer_parcial.persistencia.UsuarioDAO;
import prj_primer_parcial.negocio.Articulo;
import prj_primer_parcial.negocio.Facturacion;
import prj_primer_parcial.persistencia.ArticuloDAO;
import prj_primer_parcial.persistencia.FacturacionDAO;

public class Servidor {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado y escuchando en el puerto " + PORT);
            while (true) {
                try (Socket socket = serverSocket.accept();
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                    String action = ois.readUTF();
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    ArticuloDAO articuloDAO = new ArticuloDAO();
                    FacturacionDAO facturacionDAO = new FacturacionDAO();

                    switch (action) {
                        case "insertarUsuario":
                            Usuario usuarioInsertar = (Usuario) ois.readObject();
                            usuarioDAO.insertarUsuario(usuarioInsertar);
                            oos.writeUTF("Usuario insertado correctamente");
                            break;
                        case "actualizarUsuario":
                            Usuario usuarioActualizar = (Usuario) ois.readObject();
                            usuarioDAO.actualizarUsuario(usuarioActualizar);
                            oos.writeUTF("Usuario actualizado con éxito");
                            break;
                        case "obtenerUsuarios":
                            List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
                            oos.writeObject(usuarios);
                            break;
                        case "eliminarUsuario":
                            String usuarioEliminar = ois.readUTF();
                            usuarioDAO.eliminarUsuario(usuarioEliminar);
                            oos.writeUTF("Usuario eliminado correctamente");
                            break;
                        case "buscarUsuarios":
                            String usuarioBuscado = ois.readUTF();
                            List<Usuario> usuariosBuscados = usuarioDAO.buscarUsuarios(usuarioBuscado);
                            oos.writeObject(usuariosBuscados);
                            break;
                        case "insertarArticulo":
                            Articulo articuloInsertar = (Articulo) ois.readObject();
                            articuloDAO.insertarArticulo(articuloInsertar);
                            oos.writeUTF("Articulo insertado correctamente");
                            break;
                        case "actualizarArticulo":
                            Articulo articuloActualizar = (Articulo) ois.readObject();
                            articuloDAO.actualizarArticulo(articuloActualizar);
                            oos.writeUTF("Articulo actualizado con éxito");
                            break;
                        case "obtenerArticulos":
                            System.out.println("Solicitud de obtener articulos recibida");
                            List<Articulo> articulos = articuloDAO.obtenerArticulos();
                            System.out.println("Articulos obtenidos: " + articulos.size());
                            oos.writeObject(articulos);
                            break;
                        case "eliminarArticulo":
                            String articuloEliminar = ois.readUTF();
                            articuloDAO.eliminarArticulo(articuloEliminar);
                            oos.writeUTF("Articulo eliminado correctamente");
                            break;
                        case "buscarArticulos":
                            String articuloBuscado = ois.readUTF();
                            List<Articulo> articulosBuscados = articuloDAO.buscarArticulos(articuloBuscado);
                            oos.writeObject(articulosBuscados);
                            break;
                         case "insertarFacturacion":
                            Facturacion facturacionInsertar = (Facturacion) ois.readObject();
                            facturacionDAO.insertarFacturacion(facturacionInsertar);
                            oos.writeUTF("Factura insertada correctamente");
                            break;
                        case "actualizarFacturacion":
                            Facturacion facturacionActualizar = (Facturacion) ois.readObject();
                            facturacionDAO.actualizarFacturacion(facturacionActualizar);
                            oos.writeUTF("Factura actualizada con éxito");
                            break;
                        case "obtenerFacturaciones":
                            List<Facturacion> facturaciones = facturacionDAO.obtenerFacturaciones();
                            oos.writeObject(facturaciones);
                            break;
                        case "eliminarFacturacion":
                            String facturacionEliminar = ois.readUTF();
                            facturacionDAO.eliminarFacturacion(facturacionEliminar);
                            oos.writeUTF("Usuario eliminado correctamente");
                            break;
                        case "buscarFacturaciones":
                            String facturacionBuscado = ois.readUTF();
                            List<Facturacion> facturacionesBuscados = facturacionDAO.buscarFacturacion(facturacionBuscado);
                            oos.writeObject(facturacionesBuscados);
                            break;                          
                          
                        default:
                            oos.writeUTF("Acción no reconocida");
                            break;
                    }
                    oos.flush();
                } catch (IOException | ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
