package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class SimulationGameGUI extends JPanel {
    private JTextArea updatesTextArea;
    private JLabel scoreLabel;
    private JLabel homeTeamLabel;
    private JLabel awayTeamLabel;
    public SimulationGameGUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titleLabel = new JLabel("CURRENT MATCH");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Área de actualizaciones
        updatesTextArea = new JTextArea();
        updatesTextArea.setEditable(false);
        updatesTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        updatesTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(updatesTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);

        // Marcador
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

    public void addUpdate(int minute, String update) {
        updatesTextArea.append("Minuto " + minute + ":\n- " + update + "\n\n");
        updatesTextArea.setCaretPosition(updatesTextArea.getDocument().getLength());
    }

    public void setScore(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
        homeTeamLabel.setText(homeTeamName);
        awayTeamLabel.setText(awayTeamName);
        scoreLabel.setText(homeTeamScore + " - " + awayTeamScore);
    }

    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}