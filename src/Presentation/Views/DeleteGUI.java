package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import static java.awt.Font.PLAIN;

public class DeleteGUI extends JPanel {

    private final JTextField email;
    private final JTextField password;
    private final JButton delete;

    private Color backgroundColor;
    public static final String DELETE_BUTTON = "DELETE_BUTTON";

    public DeleteGUI() {
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

        //Title
        JLabel title = new JLabel("Delete Account");
        title.setFont(new Font("Apple Casual", PLAIN, 60));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        // Dni/Email
        String defaultDniText = "dni/email: ";
        email = new JTextField(defaultDniText);
        email.setFont(new Font("Inter", PLAIN, 20));
        email.setBackground(Color.decode("#D9D9D9"));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(email);

        panel.add(Box.createVerticalStrut(15));

        JPanel passwordPanel = new JPanel(new BorderLayout());

        panel.add(passwordPanel);

        String defaultPasswordText = "Password: ";
        password = new JPasswordField(defaultPasswordText);
        password.setFont(new Font("Inter", PLAIN, 20));
        password.setBackground(Color.decode("#D9D9D9"));
        password.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        passwordPanel.add(password, BorderLayout.CENTER);

        panel.add(Box.createVerticalStrut(15));


        delete = new JButton("Delete");
        delete.setActionCommand(DELETE_BUTTON);
        delete.setPreferredSize(new Dimension(150, 40));
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(delete);

        // add panel to this
        this.add(panel);
    }

    public void focusListener(FocusListener focusListener) {
        email.addFocusListener(focusListener);
        email.setName("Email or DNI");
        password.addFocusListener(focusListener);
        password.setName("Password");
    }

    public void actionListener(ActionListener actionListener){
        delete.addActionListener(actionListener);
    }
    public String getUsernameInfo() {
        return email.getText();
    }

    public String getPasswordInfo() {
        return password.getText();
    }
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}