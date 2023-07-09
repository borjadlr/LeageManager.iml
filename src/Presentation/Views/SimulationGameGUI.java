package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel class representing the GUI for simulating a game.
 */
public class SimulationGameGUI extends JPanel {
    private final JTextArea updatesTextArea;
    private final JLabel scoreLabel;
    private final JLabel homeTeamLabel;
    private final JLabel awayTeamLabel;

    /**
     * Constructs a SimulationGameGUI object.
     * Initializes the GUI components and sets up the layout.
     */
    public SimulationGameGUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("CURRENT MATCH");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        updatesTextArea = new JTextArea();
        updatesTextArea.setEditable(false);
        updatesTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        updatesTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(updatesTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);

        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scorePanel.setBackground(Color.WHITE);

        homeTeamLabel = new JLabel("Home Team");
        homeTeamLabel.setFont(new Font("Arial", Font.BOLD, 16));
        homeTeamLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(homeTeamLabel, BorderLayout.WEST);

        scoreLabel = new JLabel("0 - 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(scoreLabel, BorderLayout.CENTER);

        awayTeamLabel = new JLabel("Away Team");
        awayTeamLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
