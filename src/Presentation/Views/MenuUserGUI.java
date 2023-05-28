package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MenuUserGUI class represents a graphical user interface panel for the user menu.
 * It provides buttons for watching live matches, viewing available leagues, and accessing statistics.
 */
public class MenuUserGUI extends JPanel {
    private final JButton watchMatches;
    private final JButton viewAvailableLeagues;
    private final JButton viewStatistics;
    private static final String WATCH_MATCHES = "WATCH_MATCHES";
    private static final String VIEW_AVAILABLE_LEAGUES = "VIEW_AVAILABLE_LEAGUES";
    private static final String VIEW_STATISTICS = "VIEW_STATISTICS";

    /**
     * Constructs a MenuUserGUI panel.
     */
    public MenuUserGUI() {
        // Set background color and layout
        Color backgroundColor = Color.white;
        setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);

        panel.add(Box.createVerticalGlue());

        // Add title label
        JLabel title = new JLabel("User Menu");
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setForeground(Color.black);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(90));

        // Add "Watch Live Matches" button
        watchMatches = new JButton("Watch Live Matches");
        watchMatches.setActionCommand(WATCH_MATCHES);
        watchMatches.setPreferredSize(new Dimension(150, 30));
        watchMatches.setAlignmentX(Component.CENTER_ALIGNMENT);
        watchMatches.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(watchMatches);

        panel.add(Box.createVerticalStrut(15));

        // Add "View Available Leagues" button
        viewAvailableLeagues = new JButton("View Available Leagues");
        viewAvailableLeagues.setActionCommand(VIEW_AVAILABLE_LEAGUES);
        viewAvailableLeagues.setPreferredSize(new Dimension(150, 30));
        viewAvailableLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAvailableLeagues.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(viewAvailableLeagues);

        panel.add(Box.createVerticalStrut(15));

        // Add "View Statistics" button
        viewStatistics = new JButton("View Statistics");
        viewStatistics.setActionCommand(VIEW_STATISTICS);
        viewStatistics.setPreferredSize(new Dimension(150, 30));
        viewStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewStatistics.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(viewStatistics);

        panel.add(Box.createVerticalStrut(15));

        this.add(panel);
    }

    /**
     * Registers an action listener for the buttons.
     *
     * @param listener the ActionListener to be registered
     */
    public void registerListener(ActionListener listener) {
        watchMatches.addActionListener(listener);
        viewAvailableLeagues.addActionListener(listener);
        viewStatistics.addActionListener(listener);
    }
}
