package Presentation.Views;

import Business.Entities.League;
import Presentation.Controllers.ShowLeagueController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShowLeague extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

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
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        // Set cell renderer for aligning the content
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        // Add selection listener to the table
        ListSelectionListener selectionListener = new ShowLeagueController();
        table.getSelectionModel().addListSelectionListener(selectionListener);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addLeagues(ArrayList<League> leagues) {
        tableModel.setRowCount(0);

        // Add league data to the table
        for (League league : leagues) {
            Object[] rowData = {league.getName(), league.getNumber_teams(), league.isState()};
            tableModel.addRow(rowData);
        }
    }
}
