package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuAdminGUI extends JPanel {
    private final JButton createNewTeam;
    private final JButton newLeague;
    private final JButton viewLeagues;
    private final JButton viewStatistics;
    private final JButton viewMatches;

    private static final String CREATE_NEW_TEAM = "CREATE_NEW_TEAM";
    private static final String NEW_LEAGUE = "NEW_LEAGUE";
    private static final String VIEW_LEAGUES = "VIEW_LEAGUES";
    private static final String VIEW_STATISTICS = "STATISTICS_VIEW";
    private static final String VIEW_MATCHES = "VIEW_MATCHES";

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

        //Delete team
        createNewTeam = new JButton("Create Team");
        createNewTeam.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        createNewTeam.setActionCommand(CREATE_NEW_TEAM);
        createNewTeam.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create new league
        newLeague = new JButton("Create New League");
        newLeague.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        newLeague.setActionCommand(NEW_LEAGUE);
        newLeague.setAlignmentX(Component.CENTER_ALIGNMENT);

        //View Leagues
        viewLeagues = new JButton("View Leagues");
        viewLeagues.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewLeagues.setActionCommand(VIEW_LEAGUES);
        viewLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Button of statistics
        viewStatistics = new JButton("View Statistics");
        viewStatistics.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewStatistics.setActionCommand(VIEW_STATISTICS);
        viewStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Button of statistics
        viewMatches = new JButton("View Mathces");
        viewMatches.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewMatches.setActionCommand(VIEW_MATCHES);
        viewMatches.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the maximum size of each button to be the same
        Dimension buttonSize = new Dimension(250, 120); // set the size of the buttons
        createNewTeam.setMaximumSize(buttonSize);
        newLeague.setMaximumSize(buttonSize);
        viewLeagues.setMaximumSize(buttonSize);
        viewStatistics.setMaximumSize(buttonSize);
        viewMatches.setMaximumSize(buttonSize);


        // Add the buttons to the panel in the same order as before
        panel.add(createNewTeam);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(newLeague);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(viewLeagues);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(viewStatistics);
        panel.add(Box.createVerticalStrut(MAX_SPACE_BUTTONS));
        panel.add(viewMatches);

        this.add(panel);
    }

    public void menuAdminListener(ActionListener actionListener){
        createNewTeam.addActionListener(actionListener);
        newLeague.addActionListener(actionListener);
        viewLeagues.addActionListener(actionListener);
        viewStatistics.addActionListener(actionListener);
        viewMatches.addActionListener(actionListener);
    }
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
