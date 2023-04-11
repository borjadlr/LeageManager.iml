package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {

        private JTextField dni;
        private JTextField password;
        private JButton login;
        private JButton createAccount;

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        private Color backgroundColor;

        public static final String LOGIN_BUTTON = "LOGIN_BUTTON";
        public static final String BACK_BUTTON = "BACK_BUTTON";

        public LoginGUI() {

            this.backgroundColor = Color.darkGray;
            this.setLayout(new GridBagLayout());
            this.setBackground(backgroundColor);
            //this.setOpaque(false);
            this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // General panel
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
            panel.setBackground(backgroundColor);
            panel.setOpaque(true);

            // Image panel
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(backgroundColor);
            imagePanel.setOpaque(true);
            imagePanel.setLayout(new BorderLayout());
            imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

            // Image
        /*JImagePanel spotifyLogo = new JImagePanel("pictures/spotifyLogo.png");
        spotifyLogo.setOpaque(false);
        spotifyLogo.setPreferredSize(new Dimension(150, 150));
        spotifyLogo.setSize(150, 150);
        spotifyLogo.setBackground(new Color(34, 246, 219));
        spotifyLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.add(spotifyLogo);
        panel.add(imagePanel);*/

            panel.add(Box.createVerticalGlue());

            //Title
            JLabel title = new JLabel("LeagueManager");
            title.setFont(new Font("Apple Casual", Font.PLAIN, 25));
            title.setForeground(Color.white);
            title.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(title);

            //Username
            password = new JTextField("admin");
            panel.add(password);

            //Password
            dni = new JPasswordField("admin");
            panel.add(dni);

            //Login
            login = new JButton("Login");
            login.setActionCommand(LOGIN_BUTTON);
            login.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(login);

            //Create account
            createAccount = new JButton("Create account");
            //createAccount.setActionCommand(REGISTER_BUTTON);
            createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(createAccount);

            this.add(panel);

        }

        public void registerListener(ActionListener actionListener) {

            password.addActionListener(actionListener);

            dni.addActionListener(actionListener);

            login.addActionListener(actionListener);

            createAccount.addActionListener(actionListener);
        }

        public String getPasswordInfo(){
            return this.dni.getText();
        }

        public String getUsernameInfo(){
            return this.password.getText();
        }

}



