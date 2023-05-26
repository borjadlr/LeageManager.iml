package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuAdminGUI extends JPanel {
    private final JButton createNewTeam;
    private final JButton deleteTeam;
    private final JButton newLeague;
    private final JButton deleteLeague;
    private final JButton viewLeagues;

    public static final String CREATE_NEW_TEAM = "CREATE_NEW_TEAM";
    public static final String CREATE_NEW_ACCOUNT = "CREATE_NEW_ACCOUNT";
    public static final String DELETE_TEAM = "DELETE_TEAM";
    public static final String NEW_LEAGUE = "NEW_LEAGUE";
    public static final String DELETE_LEAGUE = "DELETE_LEAGUE";
    public static final String VIEW_LEAGUES = "VIEW_LEAGUES";
    private static final int MAX_SPACE_BUTTONS = 5;

    public MenuAdminGUI() {

        Color backgroundColor = Color.WHITE;
        setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
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

        panel.add(Box.createVerticalGlue());

        //Title
        JLabel title = new JLabel("Admin Menu");
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setForeground(Color.black);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(90));

        //Create account
        createNewTeam = new JButton("Create account");
        createNewTeam.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        createNewTeam.setActionCommand(CREATE_NEW_ACCOUNT);
        createNewTeam.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Delete team
        deleteTeam = new JButton("Create Team");
        deleteTeam.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        deleteTeam.setActionCommand(CREATE_NEW_TEAM);
        deleteTeam.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create new league
        newLeague = new JButton("Create New League");
        newLeague.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        newLeague.setActionCommand(NEW_LEAGUE);
        newLeague.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Delete League
        deleteLeague = new JButton("Delete League");
        deleteLeague.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        deleteLeague.setActionCommand(DELETE_LEAGUE);
        deleteLeague.setAlignmentX(Component.CENTER_ALIGNMENT);

        //View Leagues
        viewLeagues = new JButton("View Leagues");
        viewLeagues.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewLeagues.setActionCommand(VIEW_LEAGUES);
        viewLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Set the maximum size of each button to be the same
        Dimension buttonSize = new Dimension(250, 120); // set the size of the buttons
        createNewTeam.setMaximumSize(buttonSize);
        deleteTeam.setMaximumSize(buttonSize);
        newLeague.setMaximumSize(buttonSize);
        deleteLeague.setMaximumSize(buttonSize);
        viewLeagues.setMaximumSize(buttonSize);

        // Add the buttons to the panel in the same order as before
        panel.add(createNewTeam);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(deleteTeam);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(newLeague);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(deleteLeague);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(viewLeagues);

        this.add(panel);
    }

    public void menuAdminListener(ActionListener actionListener){
        createNewTeam.addActionListener(actionListener);
        deleteTeam.addActionListener(actionListener);
        newLeague.addActionListener(actionListener);
        deleteLeague.addActionListener(actionListener);
        viewLeagues.addActionListener(actionListener);
    }

    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }




}
