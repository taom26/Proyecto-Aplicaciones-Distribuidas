package prj_primer_parcial.aplicacion.pantalla_principal;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

import prj_primer_parcial.aplicacion.Cliente.ClienteForm;

public class PantallaPrincipal extends JFrame {
    private JPanel contentPanel;
    private JPanel mainPanel;

    public PantallaPrincipal() {
        setTitle("Pantalla Principal");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel(new BorderLayout());
        mainPanel = new JPanel(new CardLayout());

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.getBotonClientes().addActionListener(e -> mostrarPantallaClientes());

        contentPanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        ImageIcon imageIcon = createImageIcon("../../assets/ESPE.png");
        RightPanel rightPanel = new RightPanel(imageIcon, "Universidad de las Fuerzas Armadas ESPE", 200, 400);
        contentPanel.add(rightPanel, BorderLayout.EAST);

        add(contentPanel);
    }

    private void mostrarPantallaClientes() {
        ClienteForm clienteForm = new ClienteForm();
        JDialog dialog = new JDialog(this, "Gestión de Clientes", true);
        dialog.getContentPane().add(clienteForm.getContentPane());
        dialog.pack();
        dialog.setLocationRelativeTo(this); 
        dialog.setVisible(true);
    }

    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.setVisible(true);
        });
    }
}