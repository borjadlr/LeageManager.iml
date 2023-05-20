package Presentation.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PlayersListGUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public PlayersListGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        JLabel titleLabel = new JLabel("Players List");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Player Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
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

    public void addPlayers(ArrayList<String> playerNames) {
        tableModel.setRowCount(0);

        // Add player names to the table
        for (String playerName : playerNames) {
            Object[] rowData = {playerName};
            tableModel.addRow(rowData);
        }
    }
}
