package prj_primer_parcial.escucha;

import prj_primer_parcial.negocio.Cliente;
import prj_primer_parcial.persistencia.ClienteDAO;
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
                    ClienteDAO clienteDAO = new ClienteDAO();
                    switch (action) {
                        case "insertarCliente":
                            Cliente clienteInsertar = (Cliente) ois.readObject();
                            clienteDAO.insertarCliente(clienteInsertar);
                            oos.writeUTF("Cliente insertado correctamente");
                            break;
                        case "actualizarCliente":
                            Cliente clienteActualizar = (Cliente) ois.readObject();
                            clienteDAO.actualizarCliente(clienteActualizar);
                            oos.writeUTF("Cliente actualizado correctamente");
                            break;
                        case "obtenerClientes":
                            List<Cliente> clientes = clienteDAO.obtenerClientes();
                            oos.writeObject(clientes);
                            break;
                        case "eliminarCliente":
                            String ruc = ois.readUTF();
                            clienteDAO.eliminarCliente(ruc);
                            oos.writeUTF("Cliente eliminado correctamente");
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
