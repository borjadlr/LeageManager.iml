package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanelGUI extends JPanel {

    private final JButton login;
    private final JButton register;
    private static final String TITLE = "LeagueManager";

    private static final int MAX_SPACE_BUTTONS = 15;

    private static final int MAX_SPACE_TITTLE = 90;
    private final Color backgroundColor;
    private static final String LOGIN_BUTTON = "LOGIN_BUTTON";
    private static final String REGISTER_BUTTON = "REGISTER_BUTTON";


    public MainPanelGUI() {

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
        JLabel title = new JLabel(TITLE);
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(MAX_SPACE_TITTLE));

        login = new JButton("           Login          ");
        login.setActionCommand(LOGIN_BUTTON);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(login);

        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));

        register = new JButton("        Register        ");
        register.setActionCommand(REGISTER_BUTTON);
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(register);

        this.add(panel);
    }


    /**
     *
     * @param actionListener
     */
    public void registerListener(ActionListener actionListener){
        login.addActionListener(actionListener);
        register.addActionListener(actionListener);
    }
    }