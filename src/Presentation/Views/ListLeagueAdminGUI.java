package Presentation.Views;

import Business.Entities.League;
import Presentation.Controllers.ListLeagueAdminController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListLeagueAdminGUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton deleteButton;

    public ListLeagueAdminGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create a title label
        JLabel titleLabel = new JLabel("List of Leagues");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"League Name", "Number of Participating Teams", "Current Status", "Select"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only allow editing the checkbox column
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
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
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Create the Delete button
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(80, 30));

        // Set the layout manager for the bottom area
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        // Add the Delete button to the bottom panel
        bottomPanel.add(deleteButton);

        // Add the bottom panel to the main panel
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }

    public void setController(ListLeagueAdminController controller) {
        table.addMouseListener(controller);
        deleteButton.addActionListener(controller);
    }

    public void noLeaguesMessage() {
        JOptionPane.showMessageDialog(null, "There are no leagues, so you cannot list them. Create one before!");
    }


    /*public void addLeagues(List<League> leagues) {
        tableModel.setRowCount(0);

        // Add league data to the table
        for (League league : leagues) {
            Object[] rowData = {league.getName(), league.getNumberOfTeams(), league.getStatus(), false};
            tableModel.addRow(rowData);
        }
    }

     */

}