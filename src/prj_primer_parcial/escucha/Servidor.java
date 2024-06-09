package prj_primer_parcial.escucha;

import prj_primer_parcial.negocio.Usuario;
import prj_primer_parcial.persistencia.UsuarioDAO;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

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
