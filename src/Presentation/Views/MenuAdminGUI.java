package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The graphical user interface for the admin menu panel of the application.
 */
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
    private static final String TitleLetterType = "Apple Casual";
    private static final String TITLE = "Admin Menu";
    private static final String CreateTeam = "Create Team";
    private static final String CreateNewLeague = "Create New League";
    private static final String ViewLeagues = "View Leagues";
    private static final String ViewStatistics = "View Statistics";
    private static final String ViewMatches = "View Matches";
    private static final int MAX_SPACE_BUTTONS = 5;

    /**
     * Creates a new instance of the MenuAdminGUI class.
     */
    public MenuAdminGUI() {

        Color backgroundColor = Color.WHITE;
        setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        panel.add(Box.createVerticalGlue());

        JLabel title = new JLabel(TITLE);
        title.setFont(new Font(TitleLetterType, Font.PLAIN, 60));
        title.setForeground(Color.black);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(90));

        createNewTeam = new JButton(CreateTeam);
        createNewTeam.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        createNewTeam.setActionCommand(CREATE_NEW_TEAM);
        createNewTeam.setAlignmentX(Component.CENTER_ALIGNMENT);

        newLeague = new JButton(CreateNewLeague);
        newLeague.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        newLeague.setActionCommand(NEW_LEAGUE);
        newLeague.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewLeagues = new JButton(ViewLeagues);
        viewLeagues.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewLeagues.setActionCommand(VIEW_LEAGUES);
        viewLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewStatistics = new JButton(ViewStatistics);
        viewStatistics.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewStatistics.setActionCommand(VIEW_STATISTICS);
        viewStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewMatches = new JButton(ViewMatches);
        viewMatches.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        viewMatches.setActionCommand(VIEW_MATCHES);
        viewMatches.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(250, 120);
        createNewTeam.setMaximumSize(buttonSize);
        newLeague.setMaximumSize(buttonSize);
        viewLeagues.setMaximumSize(buttonSize);
        viewStatistics.setMaximumSize(buttonSize);
        viewMatches.setMaximumSize(buttonSize);

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

    /**
     * Registers an action listener for the admin menu buttons.
     * @param actionListener The action listener to register.
     */
    public void menuAdminListener(ActionListener actionListener) {
        createNewTeam.addActionListener(actionListener);
        newLeague.addActionListener(actionListener);
        viewLeagues.addActionListener(actionListener);
        viewStatistics.addActionListener(actionListener);
        viewMatches.addActionListener(actionListener);
    }

    /**
     * Displays an exception message in a dialog box.
     * @param message The exception message to display.
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}