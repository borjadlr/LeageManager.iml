package Presentation.Views;

import Business.Entities.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListPlayerGUI extends JPanel {
    private JTable table;
    private JLabel titleLabel;
    private DefaultTableModel tableModel;


    public ListPlayerGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        titleLabel = new JLabel("Players List");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Dni", "Email", "Phone", "Dorsal"};
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
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addPlayers(List<User> playerNames) {
        tableModel.setRowCount(0);
        for (User playerName : playerNames) {
            Object[] rowData = {playerName.getDni(), playerName.getEmail(), playerName.getPhone(), playerName.getNumber()};
            tableModel.addRow(rowData);
        }
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}

