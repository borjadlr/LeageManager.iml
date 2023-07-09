package Presentation.Views;

import Business.Entities.Match;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * A JPanel class representing the GUI for simulating a league.
 */

public class SimulationLeagueGUI extends JPanel {
    private final DefaultTableModel tableModel;
    private static final String COLUMN1 = "Home Team";
    private static final String COLUMN2 = "GOALS HOME";
    private static final String COLUMN3 = "GOALS AWAY";
    private static final String COLUMN4 = "Away Team";
    private static final String LetterType = "Inter";
    private static final String TITLE = "League Simulation";
    private static final String TitleLetterType = "Apple Casual";

    /**
     * Constructs a SimulationLeagueGUI object.
     * Initializes the GUI components and sets up the layout.
     */

    public SimulationLeagueGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(TITLE);
        titleLabel.setFont(new Font(TitleLetterType, Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {COLUMN1, COLUMN2, COLUMN3, COLUMN4};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font(LetterType, Font.BOLD, 14));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableHeader.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Adds match data to the table.
     * Clears the existing table data and populates it with the provided matches.
     *
     * @param matches The list of Match objects representing the matches to be added.
     */
    public void addMatch(List<Match> matches) {
        tableModel.setRowCount(0);

        for (Match match : matches) {
            Object[] rowData = {match.getLocal(), match.getGolesLocal(), match.getGolesVisitante(), match.getVisitante()};
            tableModel.addRow(rowData);
        }
    }
}
