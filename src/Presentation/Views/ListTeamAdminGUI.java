package Presentation.Views;

import Business.Entities.Team;
import Presentation.Controllers.ListTeamAdminController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The ListTeamAdminGUI class represents a panel that displays a list of teams with additional administrative functionality.
 */
public class ListTeamAdminGUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;
    private final JButton delete;

    /**
     * Constructs a ListTeamAdminGUI object.
     */
    public ListTeamAdminGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        titleLabel = new JLabel("List of Teams");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

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

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(80, 30)); // Set the preferred size of the button

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(delete);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the title of the panel.
     * @param title the title to set
     */
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    /**
     * Adds a list of teams to the table.
     * @param teams the list of teams to add
     */
    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);
        // Add team data to the table
        for (Team team : teams) {
            Object[] rowData = {team.getName(), team.getPoints(), team.getWins(), team.getTies(), team.getLosses(), team.getNPlayers(), false}; // Add checkbox column data
            tableModel.addRow(rowData);
        }
    }

    /**
     * Sets the controller for handling user interactions with the table and delete button.
     * @param controller the ListTeamAdminController to set as the controller
     */
    public void setController(ListTeamAdminController controller) {
        table.addMouseListener(controller);
        delete.addActionListener(controller);
    }

    /**
     * Displays a confirmation dialog for deleting a team and returns the user's choice.
     * @return the user's choice (JOptionPane.YES_OPTION or JOptionPane.NO_OPTION)
     */
    public int showAreYouSureDelete() {
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Retrieves the JTable component used for displaying the teams.
     * @return the JTable component
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Displays an exception message in a dialog box.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}