package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class NewLeagueGUI {

    public class NewLeague extends JFrame {

        private JTextField leagueName;
        private JTextField date;
        private JPasswordField passwordField;
        private JButton dropdownButton;
        private JButton registerButton;
        private static String leagueNameText;
        private String dateText;
        private String passwordText;

        private JMenuItem logout;
        private JMenuItem deleteAccount;

        public NewLeague() {
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

            //Panel on s'ha de fer CardLayout.
            JPanel restSection = new JPanel(new BorderLayout());
            restSection.setVisible(true);
            restSection.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
            add(restSection, BorderLayout.CENTER);
            restSection.setBackground(Color.white);

            JLabel Titol = new JLabel("Create a new league");
            Titol.setHorizontalAlignment(JLabel.CENTER);
            Titol.setVerticalAlignment(JLabel.NORTH);
            Titol.setFont(new Font("Inter", PLAIN, 60));
            restSection.add(Titol, BorderLayout.NORTH);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(null);
            restSection.add(inputPanel, BorderLayout.CENTER);
            inputPanel.setBackground(Color.white);

            JLabel leagueNameLabel = new JLabel("league name:");
            leagueNameLabel.setFont(new Font("Inter", PLAIN, 20));
            leagueNameLabel.setBounds(448, 30, 150, 25);
            inputPanel.add(leagueNameLabel);
            leagueName = new JTextField(20);
            leagueName.setFont(new Font("Inter", PLAIN, 20));
            leagueName.setBounds(590, 30, 200, 30);
            leagueName.setBackground(Color.decode("#D9D9D9"));
            leagueName.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            inputPanel.add(leagueName);

            JLabel dateLabel = new JLabel("date:");
            dateLabel.setFont(new Font("Inter", PLAIN, 20));
            dateLabel.setBounds(522, 90, 150, 25);
            inputPanel.add(dateLabel);
            date = new JTextField(20);
            date.setFont(new Font("Inter", PLAIN, 20));
            date.setBounds(590, 90, 200, 30);
            date.setBackground(Color.decode("#D9D9D9"));
            date.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            inputPanel.add(date);

            dropdownMenu.add(logout);
            dropdownMenu.add(deleteAccount);

            JButton ok = new JButton("OK");
            ok.setBounds(628, 140, 40, 30);
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
                    leagueNameText = leagueName.getText();
                    dateText = date.getText();

                    // Add code here to save the values to a file or database
                }
            });

            //Botó logout.
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Error: Logout functionality not implemented yet.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            //Botó delete.
            deleteAccount.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Error: Delete account functionality not implemented yet.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

    }

}
