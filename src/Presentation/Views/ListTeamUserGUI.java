package Presentation.Views;

import Business.Entities.Team;
import Presentation.Controllers.ListTeamUserController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The ListTeamUserGUI class represents a panel that displays a list of teams in a table format.
 */
public class ListTeamUserGUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;

    /**
     * Constructs a ListTeamUserGUI object.
     */
    public ListTeamUserGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        titleLabel = new JLabel("Teams List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Team Name", "Points", "Wins", "Draws", "Losses"};
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

    /**
     * Adds a list of teams to the table.
     * @param teams the list of teams to add
     */
    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);

        // Add team data to the table
        for (Team team : teams) {
            Object[] rowData = {team.getName(), team.getPoints(), team.getWins(), team.getTies(), team.getLosses()};
            tableModel.addRow(rowData);
        }
    }

    /**
     * Sets the controller for handling user interactions with the table.
     * @param controller the ListTeamUserController to set as the controller
     */
    public void setController(ListTeamUserController controller) {
        table.addMouseListener(controller);
    }

    /**
     * Sets the title of the panel.
     * @param title the title to set
     */
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    /**
     * Retrieves the JTable component used for displaying the teams.
     * @return the JTable component
     */
    public JTable getTable() {
        return table;
    }
}