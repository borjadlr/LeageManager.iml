package Presentation.Views;

import Business.Entities.Match;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class SimulationLeagueGUI extends JPanel {
    private JTable table;
    private final DefaultTableModel tableModel;

    public SimulationLeagueGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create a title label
        JLabel titleLabel = new JLabel("League Simulation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Equipo Local", "Local", "Goles Visitante", "Equipo Visitante"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        // Center align the content in the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Set table header font and alignment
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableHeader.setReorderingAllowed(false);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMatch(List<Match> matches) {
        tableModel.setRowCount(0);
        // Add match data to the table
        for (Match match : matches) {
            Object[] rowData = {match.getLocal(), match.getGolesLocal(), match.getGolesVisitante(), match.getVisitante()};
            tableModel.addRow(rowData);
        }
    }
}
