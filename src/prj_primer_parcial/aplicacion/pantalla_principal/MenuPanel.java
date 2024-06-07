package prj_primer_parcial.aplicacion.pantalla_principal;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton clientesButton;
    private JButton facturacionButton;
    private JButton inventariosButton;
    private JButton cxcButton;
    private JButton contabilidadButton;

    public MenuPanel() {
        setLayout(new GridLayout(5, 1));

        clientesButton = new JButton("Clientes");
        facturacionButton = new JButton("Facturación");
        inventariosButton = new JButton("Inventarios");
        cxcButton = new JButton("Cuentas por Cobrar");
        contabilidadButton = new JButton("Contabilidad");

        add(clientesButton);
        add(facturacionButton);
        add(inventariosButton);
        add(cxcButton);
        add(contabilidadButton);
    }

    public JButton getBotonClientes() {
        return clientesButton;
    }
}