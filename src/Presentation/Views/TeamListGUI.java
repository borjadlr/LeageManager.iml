package Presentation.Views;

import Business.Entities.Team;
import Presentation.Controllers.TeamListController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TeamListGUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    TeamListController controller;
    private JLabel titleLabel;

    public TeamListGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        titleLabel = new JLabel("Teams List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Team Name", "Total Score"};
        tableModel = new DefaultTableModel(columnNames, 0){
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
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);

        // Add team data to the table
        for (Team team : teams) {
            Object[] rowData = {team.getName(), team.getWins()};
            tableModel.addRow(rowData);
        }
    }

    public void setController(TeamListController controller) {
        this.controller = controller;
        table.addMouseListener(controller);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public JTable getTable() {
        return table;
    }
}
