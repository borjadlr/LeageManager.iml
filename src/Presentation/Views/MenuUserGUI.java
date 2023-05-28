package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuUserGUI extends JPanel {
    private JButton watchMatches;
    private JButton viewAvaiableLeagues;
    private Color backgroundColor;
    private JButton viewStatistics;

    private static final String WATCH_MATCHES = "WATCH_MATCHES";
    private static final String CREATE_PLAYER = "CREATE_PLAYER";
    private static final String VIEW_AVAIABLE_LEAGUES = "VIEW_AVAIABLE_LEAGUES";
    private static final String VIEW_STATISTICS = "VIEW_STATISTICS";

    public MenuUserGUI() {

        backgroundColor = Color.white;
        setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        //panel.setOpaque(true);

        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(backgroundColor);
        imagePanel.setOpaque(true);
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        panel.add(Box.createVerticalGlue());

        //Title
        JLabel title = new JLabel("User Menu");
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setForeground(Color.black);
        title.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(90));

        //Watch Live Matches
        watchMatches = new JButton("Watch Live Matches");
        watchMatches.setActionCommand(WATCH_MATCHES);
        watchMatches.setPreferredSize(new Dimension(150, 30));
        watchMatches.setAlignmentX(Component.CENTER_ALIGNMENT);
        watchMatches.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(watchMatches);

        panel.add(Box.createVerticalStrut(15));

        //View avaiable matches
        viewAvaiableLeagues = new JButton("View Available Leagues");
        viewAvaiableLeagues.setActionCommand(VIEW_AVAIABLE_LEAGUES);
        viewAvaiableLeagues.setPreferredSize(new Dimension(150, 30));
        viewAvaiableLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAvaiableLeagues.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(viewAvaiableLeagues);

        panel.add(Box.createVerticalStrut(15));

        //Button of statistics
        viewStatistics = new JButton("View Statistics");
        viewStatistics.setActionCommand(VIEW_STATISTICS);
        viewStatistics.setPreferredSize(new Dimension(150, 30));
        viewStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewStatistics.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(viewStatistics);

        panel.add(Box.createVerticalStrut(15));

        this.add(panel);

    }

    public void registerListener(ActionListener listener) {
        watchMatches.addActionListener(listener);
        viewAvaiableLeagues.addActionListener(listener);
        viewStatistics.addActionListener(listener);
    }

}
