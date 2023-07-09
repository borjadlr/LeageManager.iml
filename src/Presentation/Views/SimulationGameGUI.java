package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel class representing the GUI for simulating a game.
 */
public class SimulationGameGUI extends JPanel {
    private static final String LetterType = "Inter";
    private static final String TITLE = "CURRENT MATCH";
    private static final String HOME_TEAM = "Home Team";
    private static final String AWAY_TEAM = "Away Team";
    private static final String TitleLetterType = "Apple Casual";

    /**
     * Constructs a SimulationGameGUI object.
     * Initializes the GUI components and sets up the layout.
     */
    public SimulationGameGUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(TITLE);
        titleLabel.setFont(new Font(TitleLetterType, Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JTextArea updatesTextArea = new JTextArea();
        updatesTextArea.setEditable(false);
        updatesTextArea.setFont(new Font(LetterType, Font.PLAIN, 12));
        updatesTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(updatesTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);

        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scorePanel.setBackground(Color.WHITE);

        JLabel homeTeamLabel = new JLabel(HOME_TEAM);
        homeTeamLabel.setFont(new Font(LetterType, Font.BOLD, 16));
        homeTeamLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(homeTeamLabel, BorderLayout.WEST);

        JLabel scoreLabel = new JLabel("0 - 0");
        scoreLabel.setFont(new Font(LetterType, Font.BOLD, 24));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(scoreLabel, BorderLayout.CENTER);

        JLabel awayTeamLabel = new JLabel(AWAY_TEAM);
        awayTeamLabel.setFont(new Font(LetterType, Font.BOLD, 16));
        awayTeamLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(awayTeamLabel, BorderLayout.EAST);

        add(scorePanel, BorderLayout.SOUTH);
    }

    /**
     * Displays an exception message in a JOptionPane.
     *
     * @param message The exception message to display.
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
