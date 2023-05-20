package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoGUI extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel dorsalLabel;
    private JLabel dniLabel;
    private JLabel phoneLabel;

    public PlayerInfoGUI() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and add labels for player information
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nameLabel);
        add(new JLabel());

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(emailLabel);
        add(new JLabel());

        dorsalLabel = new JLabel("Dorsal:");
        dorsalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(dorsalLabel);
        add(new JLabel());

        dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(dniLabel);
        add(new JLabel());

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(phoneLabel);
        add(new JLabel());
    }

    public void setPlayerInfo(String name, String email, int dorsal, String dni, String phoneNumber) {
        nameLabel.setText("Name: " + name);
        emailLabel.setText("Email: " + email);
        dorsalLabel.setText("Dorsal: " + dorsal);
        dniLabel.setText("DNI: " + dni);
        phoneLabel.setText("Phone Number: " + phoneNumber);
    }
}
