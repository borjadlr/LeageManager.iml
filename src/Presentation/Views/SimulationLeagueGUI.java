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
    private JTable table;
    private final DefaultTableModel tableModel;

    /**
     * Constructs a SimulationLeagueGUI object.
     * Initializes the GUI components and sets up the layout.
     */

    public SimulationLeagueGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("League Simulation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Equipo Local", "Local", "Goles Visitante", "Equipo Visitante"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
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
