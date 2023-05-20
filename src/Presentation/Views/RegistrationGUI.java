package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class RegistrationGUI extends JPanel {

    private final JTextField dni;
    private final JTextField number;
    private final JTextField dorsal;

    private final JTextField email;

    private static final int MAX_SPACE_BUTTONS = 15;

    private static final int THICKNESS_BORDER = 2;

    private static final int MAX_SPACE_TITTLE = 90;

    private static final int SIZE_TITLE = 60;

    private static final String DNI_ALREADY_EXIST = "This DNI already exist";

    private final Color backgroundColor;


    public RegistrationGUI() {

        this.backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        //General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        //Title
        JLabel title = new JLabel("LaegueManager Registration");
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setFont(new Font("Apple Casual", PLAIN, SIZE_TITLE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(MAX_SPACE_TITTLE));


        String defaultPhoneNumberText = "Phone Number: ";
        number = new JTextField(defaultPhoneNumberText);
        number.setFont(new Font("Inter", PLAIN, 20));
        number.setBackground(Color.decode("#D9D9D9"));
        number.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS_BORDER));
        panel.add(number);

        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));

        String defaultDorsalText = "Dorsal: ";
        dorsal = new JTextField(defaultDorsalText);
        dorsal.setFont(new Font("Inter", PLAIN, 20));
        dorsal.setBackground(Color.decode("#D9D9D9"));
        dorsal.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS_BORDER));
        panel.add(dorsal);

        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));

        String defaultDniText = "Dni: ";
        dni = new JTextField(defaultDniText);
        dni.setFont(new Font("Inter", PLAIN, 20));
        dni.setBackground(Color.decode("#D9D9D9"));
        dni.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS_BORDER));
        panel.add(dni);


        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));

        String defaultEmailText = "Email: ";
        email = new JTextField(defaultEmailText);
        email.setFont(new Font("Inter", PLAIN, 20));
        email.setBackground(Color.decode("#D9D9D9"));
        email.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS_BORDER));
        panel.add(email);

        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));

        JButton ok = new JButton("    OK    ");
        ok.setActionCommand("OK");
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, THICKNESS_BORDER));
        panel.add(ok);
        

        this.add(panel);
    }

    public void exceptionMessage (String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public String getDniText() {
        return dni.getText();
    }

    public int getDorsalText() {
        return Integer.parseInt(dorsal.getText());
    }
    public String getEmailText(){
        return email.getText();
    }

    public String getPhoneNumberText(){
        return number.getText();
    }
}

