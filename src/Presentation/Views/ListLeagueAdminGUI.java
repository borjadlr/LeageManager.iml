package Presentation.Views;

import Business.Entities.League;
import Presentation.Controllers.ListLeagueAdminController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The ListLeagueAdminGUI class represents a panel that displays a list of leagues for an administrator.
 */
public class ListLeagueAdminGUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton deleteButton;

    private static final String DELETE = "Delete";
    private static final String LIST_OF_LEAGUES = "List of Leagues";
    private static final String COLUMN1 = "League Name";
    private static final String COLUMN2 = "Number of Participating Teams";
    private static final String COLUMN3 = "Current Status";
    private static final String COLUMN4 = "Select";
    private static final String MESSAGE_DELETE = "Are you sure you want to delete?";
    private static final String MESSAGE_LEAGUE_SELECTED = "Please select at least one league to delete.";
    private static final String MESSAGE_NO_LEAGUE_SELECTED = "No League Selected";
    private static final String LetterType = "Arial";

    /**
     * Constructs a ListLeagueAdminGUI object.
     */
    public ListLeagueAdminGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(LIST_OF_LEAGUES);
        titleLabel.setFont(new Font(LetterType, Font.BOLD, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {COLUMN1, COLUMN2, COLUMN3, COLUMN4};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
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

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        deleteButton = new JButton(DELETE);
        deleteButton.setPreferredSize(new Dimension(80, 30));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(deleteButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Returns the JTable component used to display the list of leagues.
     * @return the JTable component
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Sets the controller (ListLeagueAdminController) for the table and delete button.
     * @param controller the ListLeagueAdminController controller
     */
    public void setController(ListLeagueAdminController controller) {
        table.addMouseListener(controller);
        deleteButton.addActionListener(controller);
    }

    /**
     * Displays a warning message indicating that at least one league should be selected for deletion.
     */
    public void showWarningAtLeastOneLeague() {
        JOptionPane.showMessageDialog(null, MESSAGE_LEAGUE_SELECTED,
                MESSAGE_NO_LEAGUE_SELECTED, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Displays a confirmation dialog for deleting leagues and returns the user's choice.
     * @return the user's choice (JOptionPane.YES_OPTION or JOptionPane.NO_OPTION)
     */
    public int showAreYouSureDelete() {
        return JOptionPane.showConfirmDialog(null, MESSAGE_DELETE,
                DELETE, JOptionPane.YES_NO_OPTION);
    }

    /**
     * Adds a list of leagues to the table.
     * @param leagues the list of leagues to add
     */
    public void addLeagues(List<League> leagues) {
        tableModel.setRowCount(0);
        // Add league data to the table
        for (League league : leagues) {
            Object[] rowData = {league.getName(), league.getNumber_teams(), league.isState(), false};
            tableModel.addRow(rowData);
        }
    }

    /**
     * Displays an exception message.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}