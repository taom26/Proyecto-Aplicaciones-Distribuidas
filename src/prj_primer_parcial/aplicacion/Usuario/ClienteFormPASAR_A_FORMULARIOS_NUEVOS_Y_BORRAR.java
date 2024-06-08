package prj_primer_parcial.aplicacion.Usuario;

import prj_primer_parcial.negocio.Cliente;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ClienteFormPASAR_A_FORMULARIOS_NUEVOS_Y_BORRAR extends JFrame {
    private JTextField rucField;
    private JTextField nombreField;
    private JTextField direccionField;
    private JTextField buscarField;
    private JButton guardarButton;
    private JButton obtenerButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTable clientesTable;
    private DefaultTableModel tableModel;

    public ClienteFormPASAR_A_FORMULARIOS_NUEVOS_Y_BORRAR() {
        setTitle("Gestión de Clientes");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        rucField = new JTextField(15);
        nombreField = new JTextField(15);
        direccionField = new JTextField(15);
        buscarField = new JTextField(15);
        guardarButton = new JButton("Guardar");
        obtenerButton = new JButton("Obtener Clientes");
        eliminarButton = new JButton("Eliminar");
        actualizarButton = new JButton("Actualizar");
        buscarButton = new JButton("Buscar");

        panel.add(new JLabel("RUC:"));
        panel.add(rucField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Dirección:"));
        panel.add(direccionField);
        panel.add(guardarButton);
        panel.add(obtenerButton);
        panel.add(eliminarButton);
        panel.add(actualizarButton);

        panel.add(new JLabel("Buscar por Nombre:"));
        panel.add(buscarField);
        panel.add(buscarButton);

        tableModel = new DefaultTableModel(new String[]{"RUC", "Nombre", "Dirección"}, 0);
        clientesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientesTable);

        panel.add(scrollPane);

        guardarButton.addActionListener(e -> guardarCliente());
        obtenerButton.addActionListener(e -> obtenerClientes());
        eliminarButton.addActionListener(e -> eliminarCliente());
        actualizarButton.addActionListener(e -> actualizarCliente());
        buscarButton.addActionListener(e -> buscarClientes());

        // Agregar listener para seleccionar fila en la tabla de clientes
        clientesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && clientesTable.getSelectedRow() != -1) {
                    TableModel model = clientesTable.getModel();
                    int selectedRow = clientesTable.getSelectedRow();
                    rucField.setText(model.getValueAt(selectedRow, 0).toString());
                    nombreField.setText(model.getValueAt(selectedRow, 1).toString());
                    direccionField.setText(model.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        add(panel);
    }

    private void guardarCliente() {
        if (rucField.getText().isEmpty() || nombreField.getText().isEmpty() || direccionField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            Cliente cliente = new Cliente(rucField.getText(), nombreField.getText(), direccionField.getText());

            oos.writeUTF("insertarCliente");
            oos.writeObject(cliente);

            String response = ois.readUTF();
            JOptionPane.showMessageDialog(this, response);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente");
        }
    }

    private void obtenerClientes() {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeUTF("obtenerClientes");
            oos.flush();

            @SuppressWarnings("unchecked")
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            tableModel.setRowCount(0);  // Limpiar la tabla
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{cliente.getRuc(), cliente.getNombre(), cliente.getDireccion()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener los clientes");
        }
    }

    private void eliminarCliente() {
        int selectedRow = clientesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
            return;
        }
        String ruc = (String) tableModel.getValueAt(selectedRow, 0);
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeUTF("eliminarCliente");
            oos.writeUTF(ruc);
            oos.flush();

            String response = ois.readUTF();
            JOptionPane.showMessageDialog(this, response);
            obtenerClientes();  // Actualizar la lista de clientes
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente");
        }
    }

    private void actualizarCliente() {
        int selectedRow = clientesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para actualizar");
            return;
        }
        String ruc = (String) tableModel.getValueAt(selectedRow, 0);
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        if (nombre.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            Cliente cliente = new Cliente(ruc, nombre, direccion);

            oos.writeUTF("actualizarCliente");
            oos.writeObject(cliente);

            String response = ois.readUTF();
            JOptionPane.showMessageDialog(this, response);
            obtenerClientes();  // Actualizar la lista de clientes
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente");
        }
    }

    private void buscarClientes() {
        String nombre = buscarField.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de búsqueda está vacío.");
            return;
        }
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeUTF("buscarClientes");
            oos.writeUTF(nombre);
            oos.flush();

            @SuppressWarnings("unchecked")
            List<Cliente> clientes = (List<Cliente>) ois.readObject();
            tableModel.setRowCount(0);  // Limpiar la tabla
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{cliente.getRuc(), cliente.getNombre(), cliente.getDireccion()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar los clientes");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteFormPASAR_A_FORMULARIOS_NUEVOS_Y_BORRAR form = new ClienteFormPASAR_A_FORMULARIOS_NUEVOS_Y_BORRAR();
            form.setVisible(true);
        });
    }
}
