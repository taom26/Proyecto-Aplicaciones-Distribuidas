package prj_primer_parcial.aplicacion.pantalla_principal;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private JLabel imageLabel;
    private JLabel textLabel;

    public RightPanel(ImageIcon imageIcon, String text, int width, int height) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        JPanel imagePanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel(scaledIcon, SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        add(imagePanel, gbc);

        gbc.gridy = 1;
        textLabel = new JLabel(text, SwingConstants.CENTER);
        Font font = textLabel.getFont();
        textLabel.setFont(font.deriveFont(Font.PLAIN, 18));
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 20));
        textPanel.add(textLabel, BorderLayout.CENTER);

        add(textPanel, gbc);
    }

    public void setImageIcon(ImageIcon imageIcon) {
        ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT));
        imageLabel.setIcon(scaledIcon);
    }

    public void setText(String text) {
        textLabel.setText(text);
    }
}