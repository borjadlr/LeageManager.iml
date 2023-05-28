package Presentation.Views;

import Business.Entities.Team;
import Presentation.Controllers.ListTeamUserController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ListTeamAdminGUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;
    private final JButton delete;

    public ListTeamAdminGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        titleLabel = new JLabel("List of Teams");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Team Name", "Points", "Wins", "Draws", "Losses", "Number of Players", "Select"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only allow editing the checkbox column
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) {
                    return Boolean.class; // Set checkbox column class to Boolean
                }
                return super.getColumnClass(columnIndex);
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

        // Create the OK button
        delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(80, 30)); // Set the preferred size of the button

        // Set the layout manager for the bottom area
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        // Add the OK button to the bottom panel
        bottomPanel.add(delete);

        // Add the bottom panel to the main panel
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);
        // Add team data to the table
        for (Team team : teams) {
            Object[] rowData = {team.getName(), team.getPoints() ,team.getWins(), team.getTies(), team.getLosses() , team.getNPlayers(),false}; // Add checkbox column data
            tableModel.addRow(rowData);
        }
    }

    public void setController(ListTeamUserController controller) {
        table.addMouseListener(controller);
        delete.addActionListener((ActionListener) controller);
    }


    public JTable getTable() {
        return table;
    }
}

