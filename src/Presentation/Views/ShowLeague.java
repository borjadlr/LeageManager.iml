package Presentation.Views;
import Business.Entities.League;
import Presentation.Controllers.ShowLeagueController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowLeague extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private ShowLeagueController controller;

    public ShowLeague() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        JLabel titleLabel = new JLabel("List of Leagues");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"League Name", "Number of Participating Teams", "Current Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing for all cells
            }
        };
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        // Set cell renderer for aligning the content
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

    public void setController(ShowLeagueController controller) {
        this.controller = controller;
        table.addMouseListener(controller);
    }

    public void addLeagues(List<League> leagues) {
        tableModel.setRowCount(0);

        // Add league data to the table
        for (League league : leagues) {
            Object[] rowData = {league.getName(), league.getNumber_teams(), league.isState()};
            tableModel.addRow(rowData);
        }
    }

    public void parseMessage() {
        JOptionPane.showMessageDialog(null, "Call 666445481 if this error occurs.");
    }
}
