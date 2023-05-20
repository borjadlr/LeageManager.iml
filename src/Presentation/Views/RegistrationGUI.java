package Presentation.Views;

import Presentation.Controllers.RegistrationController;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import static java.awt.Font.PLAIN;

public class RegistrationGUI extends JPanel {

    private final JTextField dni;
    private final JTextField email;

    private final JTextField phoneNumber;
    private final JTextField dorsal;
    private final JButton ok;
    private Color backgroundColor;

    public static final String OK_BUTTON = "OK_BUTTON";

    public RegistrationGUI() {
        this.backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        // Title
        JLabel title = new JLabel("LaegueManager Registration");
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setFont(new Font("Apple Casual", PLAIN, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        String defaultPhoneText = "Phone number:";
        phoneNumber = new JTextField(defaultPhoneText);
        phoneNumber.setFont(new Font("Inter", PLAIN, 20));
        phoneNumber.setBackground(Color.decode("#D9D9D9"));
        phoneNumber.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(phoneNumber);

        panel.add(Box.createVerticalStrut(15));

        String defaultEmailText = "Email:";
        email = new JTextField(defaultEmailText);
        email.setFont(new Font("Inter", PLAIN, 20));
        email.setBackground(Color.decode("#D9D9D9"));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(email);

        panel.add(Box.createVerticalStrut(15));

        String defaultDniText = "Dni: ";
        dni = new JTextField(defaultDniText);
        dni.setFont(new Font("Inter", PLAIN, 20));
        dni.setBackground(Color.decode("#D9D9D9"));
        dni.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dni);

        panel.add(Box.createVerticalStrut(15));

        String defaultDorsalText = "Dorsal: ";
        dorsal = new JTextField(defaultDorsalText);
        dorsal.setFont(new Font("Inter", PLAIN, 20));
        dorsal.setBackground(Color.decode("#D9D9D9"));
        dorsal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dorsal);

        panel.add(Box.createVerticalStrut(15));

        ok = new JButton("    OK    ");
        ok.setActionCommand(OK_BUTTON);
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ok.setBackground(Color.decode("#D9D9D9"));
        panel.add(ok);

        this.add(panel);
    }

    public void registerRegistration(ActionListener actionListener) {
        ok.addActionListener(actionListener);
    }

    public void foscusListener(FocusListener focusListener) {
        dni.addFocusListener(focusListener);
        dni.setName("DNI");
        email.addFocusListener(focusListener);
        email.setName("Email");
        dorsal.addFocusListener(focusListener);
        dorsal.setName("Dorsal");
        phoneNumber.addFocusListener(focusListener);
        phoneNumber.setName("Phone");
    }

    public String getDniText() {
        return dni.getText();
    }

    public String getEmailText() {
        return email.getText();
    }

    public String getDorsalText() {
        return dorsal.getText();
    }

    public String getPhoneNumberText() {
        return phoneNumber.getText();
    }

    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
