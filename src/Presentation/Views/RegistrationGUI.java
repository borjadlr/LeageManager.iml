package Presentation.Views;

import Presentation.Controllers.RegistrationController;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import static java.awt.Font.PLAIN;

public class RegistrationGUI extends JPanel {

    private JTextField name;
    private JTextField dni;
    private JTextField team;
    private JTextField dorsal;
    private JButton ok;
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

        String defaultNameText = "Name: ";
        name = new JTextField(defaultNameText);
        name.setFont(new Font("Inter", PLAIN, 20));
        name.setBackground(Color.decode("#D9D9D9"));
        name.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(name);

        panel.add(Box.createVerticalStrut(15));

        String defaultPhoneNumberText = "Phone Number: ";
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        JFormattedTextField field = new JFormattedTextField(formatter);
        field.setText(defaultPhoneNumberText);
        field.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        field.setBackground(Color.decode("#D9D9D9"));
        field.setFont(new Font("Inner", PLAIN, 20));
        panel.add(field);

        panel.add(Box.createVerticalStrut(15));

        String defaultDorsalText = "Dorsal: ";
        dorsal = new JTextField(defaultDorsalText);
        dorsal.setFont(new Font("Inter", PLAIN, 20));
        dorsal.setBackground(Color.decode("#D9D9D9"));
        dorsal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dorsal);

        panel.add(Box.createVerticalStrut(15));

        String defaultTeamText = "Team:";
        team = new JTextField(defaultTeamText);
        team.setFont(new Font("Inter", PLAIN, 20));
        team.setBackground(Color.decode("#D9D9D9"));
        team.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(team);

        panel.add(Box.createVerticalStrut(15));

        String defaultDniText = "Dni: ";
        dni = new JTextField(defaultDniText);
        dni.setFont(new Font("Inter", PLAIN, 20));
        dni.setBackground(Color.decode("#D9D9D9"));
        dni.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dni);

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
        team.addFocusListener(focusListener);
        team.setName("Team");
        dorsal.addFocusListener(focusListener);
        dorsal.setName("Dorsal");
        name.addFocusListener(focusListener);
        name.setName("Name");
    }

    public String getNameText() {
        return name.getText();
    }

    public String getDniText() {
        return dni.getText();
    }

    public String getTeamText() {
        return team.getText();
    }

    public String getDorsalText() {
        return dorsal.getText();
    }

    public void setNameText(String text) {
        name.setText(text);
    }

    public void setDniText(String text) {
        dni.setText(text);
    }

    public void setTeamText(String text) {
        team.setText(text);
    }

    public void setDorsalText(String text) {
        dorsal.setText(text);
    }



    public String getEmailText() {
        return "HOLA";
    }

    public int getPhoneNumberText() {
        return 643634634;
    }
}
