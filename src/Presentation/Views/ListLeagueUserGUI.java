package Presentation.Views;

import Business.Entities.League;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * The ListLeagueUserGUI class represents a panel that displays a list of leagues for a user.
 */
public class ListLeagueUserGUI extends JPanel {
    private final JTable table;
    private static final String LetterType = "Arial";
    private static final String LIST_OF_LEAGUES = "List of Leagues";
    private static final String COLUMN1 = "League Name";
    private static final String COLUMN2 = "Number of Participating Teams";
    private static final String COLUMN3 = "Current Status";


    private final DefaultTableModel tableModel;

    /**
     * Constructs a ListLeagueUserGUI object.
     */
    public ListLeagueUserGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        JLabel titleLabel = new JLabel(LIST_OF_LEAGUES);
        titleLabel.setFont(new Font(LetterType, Font.BOLD, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {COLUMN1, COLUMN2, COLUMN3};
        // Disable cell editing for all cells
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

    /**
     * Returns the JTable component used to display the list of leagues.
     * @return the JTable component
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Sets the controller (MouseListener) for the table.
     * @param controller the MouseListener controller
     */
    public void setController(MouseListener controller) {
        table.addMouseListener(controller);
    }

    /**
     * Adds a list of leagues to the table.
     * @param leagues the list of leagues to add
     */
    public void addLeagues(List<League> leagues) {
        tableModel.setRowCount(0);

        for (League league : leagues) {
            Object[] rowData = {league.getName(), league.getNumber_teams(), league.isState()};
            tableModel.addRow(rowData);
        }
    }

    /**
     * Displays an exception message in a dialog box.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}