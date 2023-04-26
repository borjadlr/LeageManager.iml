package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class RegistrationGUI extends JFrame {

    private JTextField name;
    private JTextField dni;
    private JTextField team;
    private JTextField number;
    private JTextField dorsal;
    private JTextField email;

    private JButton dropdownButton;
    private static String leagueNameText;

    private String phoneNumberText;
    private String dorsalText;
    private String emailText;
    private String nameText;
    private String dniText;
    private String teamText;

    private JMenuItem logout;
    private JMenuItem deleteAccount;

    public RegistrationGUI() {
        //FRAME LLIGA
        setTitle("New League");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        //Barra de dalt.
        JPanel upSection = new JPanel(new BorderLayout());
        upSection.setPreferredSize(new Dimension(0, 50)); // set a different preferred height here
        add(upSection, BorderLayout.NORTH);
        upSection.setBackground(Color.WHITE);

        //Botó enrera
        JButton atras = new JButton("◀");
        atras.setFont(new Font("Inter", PLAIN, 20));
        atras.setBackground(Color.white);
        atras.setSize(new Dimension(30, 5));
        atras.setBorder(BorderFactory.createEmptyBorder());
        Border emptyBorder = BorderFactory.createEmptyBorder();
        upSection.setBorder(emptyBorder);
        upSection.add(atras, BorderLayout.WEST);

        //Desplegable
        dropdownButton = new JButton("  ▼  ");
        JPanel drop = new JPanel();
        drop.add(dropdownButton);

        dropdownButton.setPreferredSize(new Dimension(40,2)); // set a different preferred height here
        dropdownButton.setBackground(Color.decode("#D9D9D9"));
        dropdownButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        upSection.add(dropdownButton, BorderLayout.EAST);
        dropdownButton.setVisible(true);

        JPopupMenu dropdownMenu = new JPopupMenu();
        dropdownMenu.setBackground(Color.black);
        logout = new JMenuItem("Log out");
        logout.setBackground(Color.decode("#D9D9D9"));
        logout.setPreferredSize(new Dimension(90, 30)); // set preferred size of logout button
        logout.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteAccount = new JMenuItem("Delete Account");
        deleteAccount.setBackground(Color.decode("#D9D9D9"));
        deleteAccount.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteAccount.setPreferredSize(new Dimension(90, 30)); // set preferred size of delete button

        //Panel on s'ha de fer CardLayout. AQUIIIIIIIII
        JPanel restSection = new JPanel(new BorderLayout());
        restSection.setVisible(true);
        restSection.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        add(restSection, BorderLayout.CENTER);
        restSection.setBackground(Color.WHITE);

        JLabel Titol = new JLabel("LaegueManager Registration");
        Titol.setHorizontalAlignment(JLabel.CENTER);
        Titol.setVerticalAlignment(JLabel.NORTH);
        Titol.setFont(new Font("Inter", PLAIN, 60));
        restSection.add(Titol, BorderLayout.NORTH);

        //Panel que fa que el boto ok no sigui gegant per la cara.
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        restSection.add(inputPanel, BorderLayout.CENTER);
        inputPanel.setBackground(Color.WHITE);

        JLabel NameLabel = new JLabel("Name: ");
        NameLabel.setFont(new Font("Inter", PLAIN, 20));
        NameLabel.setBounds(528, 30, 150, 25);
        inputPanel.add(NameLabel);
        name = new JTextField(20);
        name.setFont(new Font("Inter", PLAIN, 20));
        name.setBounds(590, 30, 200, 30);
        name.setBackground(Color.decode("#D9D9D9"));
        name.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(name);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Inter", PLAIN, 20));
        emailLabel.setBounds(532, 90, 150, 25);
        inputPanel.add(emailLabel);
        email = new JTextField(20);
        email.setFont(new Font("Inter", PLAIN, 20));
        email.setBounds(590, 90, 200, 30);
        email.setBackground(Color.decode("#D9D9D9"));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(email);

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setFont(new Font("Inter", PLAIN, 20));
        phoneNumber.setBounds(447, 150, 150, 25);
        inputPanel.add(phoneNumber);
        number = new JTextField(20);
        number.setFont(new Font("Inter", PLAIN, 20));
        number.setBounds(590, 150, 200, 30);
        number.setBackground(Color.decode("#D9D9D9"));
        number.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(number);

        JLabel dorsalLabel = new JLabel("Dorsal:");
        dorsalLabel.setFont(new Font("Inter", PLAIN, 20));
        dorsalLabel.setBounds(522, 210, 150, 25);
        inputPanel.add(dorsalLabel);

        dorsal = new JTextField(20);
        dorsal.setFont(new Font("Inter", PLAIN, 20));
        dorsal.setBounds(590, 210, 200, 30);
        dorsal.setBackground(Color.decode("#D9D9D9"));
        dorsal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(dorsal);

        JLabel teamLabel = new JLabel("Team:");
        teamLabel.setFont(new Font("Inter", PLAIN, 20));
        teamLabel.setBounds(530, 270, 150, 25);
        inputPanel.add(teamLabel);
        team = new JTextField(20);
        team.setFont(new Font("Inter", PLAIN, 20));
        team.setBounds(590, 270, 200, 30);
        team.setBackground(Color.decode("#D9D9D9"));
        team.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(team);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Inter", PLAIN, 20));
        dniLabel.setBounds(546, 330, 150, 25);
        inputPanel.add(dniLabel);
        dni = new JTextField(20);
        dni.setFont(new Font("Inter", PLAIN, 20));
        dni.setBounds(590, 330, 200, 30);
        dni.setBackground(Color.decode("#D9D9D9"));
        dni.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(dni);

        dropdownMenu.add(logout);
        dropdownMenu.add(deleteAccount);

        JButton ok = new JButton("OK");

        ok.setBounds(628, 390, 40, 30);
        ok.setBorder(BorderFactory.createLineBorder(Color.black,2));
        inputPanel.add(ok);

        //BARRA DE SOTA
        JPanel downSection = new JPanel(new BorderLayout());
        downSection.setPreferredSize(new Dimension(0, 30)); // set a different preferred height here
        add(downSection, BorderLayout.SOUTH);
        downSection.setBackground(Color.white);
        downSection.setBorder(BorderFactory.createEmptyBorder(0,0,10,5));
        JButton changePassword = new JButton("change current password");
        changePassword.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        changePassword.setFont(Font.getFont("INTER"));
        changePassword.setPreferredSize(new Dimension(180,30));
        changePassword.setBackground(Color.WHITE);

        //Listener botó cap a sota.
        downSection.add(changePassword, BorderLayout.EAST);
        dropdownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(dropdownButton, -dropdownButton.getWidth(), dropdownButton.getHeight());
            }
        });

        //Listener text.
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText = name.getText();
                dniText = dni.getText();
                teamText = team.getText();
                dorsalText = dorsal.getText();
                emailText = email.getText();
                phoneNumberText = number.getText();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1000, 800);
        setTitle("New League");
    }
}


