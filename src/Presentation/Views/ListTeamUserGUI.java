package Presentation.Views;

import Presentation.Controllers.ListTeamUserController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The ListTeamUserGUI class represents a panel that displays a list of teams in a table format.
 */
public class ListTeamUserGUI extends JPanel {
    private final JTable table;
    private final JLabel titleLabel;
    private static final String COLUMN1 = "Team Name";
    private static final String COLUMN2 = "Points";
    private static final String COLUMN3 = "Wins";
    private static final String COLUMN4 = "Draws";
    private static final String COLUMN5 = "Losses";
    private static final String LIST_OF_TEAMS = "List of Teams";
    private static final String LetterType = "Arial";

    /**
     * Constructs a ListTeamUserGUI object.
     */
    public ListTeamUserGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        titleLabel = new JLabel(LIST_OF_TEAMS);
        titleLabel.setFont(new Font(LetterType, Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {COLUMN1, COLUMN2, COLUMN3, COLUMN4, COLUMN5};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
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

    /**
     * Displays an exception message in a dialog box.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}