package Presentation.Views;

import Business.Entities.Team;
import Presentation.Controllers.TeamListCreateLeagueController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The TeamListCreateLeague class represents the panel for displaying and selecting teams
 * during the creation of a new league.
 */
public class TeamListCreateLeague extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;
    private final JButton add;
    private static final String TITLE = "List of Teams Create New League";
    private static final String TitleLetterType = "Apple Casual";
    private static final String COLUMN1 = "Team Name";
    private static final String COLUMN2 = "Select";
    private static final String ADD_TEAMS = "Add Teams";
    private static final String NO_TEAMS_SELECTED = "No Team Selected";
    private static final String ONE_TEAM_MESSAGE = "Please select at least one team to add.";
    private static final String AYS_MESSAGE = "Are you sure you want to add?";

    /**
     * Constructs a TeamListCreateLeague object.
     */
    public TeamListCreateLeague() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create a title label
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(new Font(TitleLetterType, Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {COLUMN1, COLUMN2};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
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
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        add = new JButton(ADD_TEAMS);
        add.setPreferredSize(new Dimension(120, 30));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(add);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds teams to the table.
     *
     * @param teams the list of teams to add
     */
    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);
        for (Team team : teams) {
            Object[] rowData = {team.getName(), false};
            tableModel.addRow(rowData);
        }
    }

    /**
     * Sets the controller for handling user actions.
     *
     * @param controller the TeamListCreateLeagueController object
     */
    public void setController(TeamListCreateLeagueController controller) {
        table.addMouseListener(controller);
        add.addActionListener(controller);
    }

    /**
     * Sets the title of the panel.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    /**
     * Returns the table component.
     *
     * @return the JTable object representing the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Displays a warning message when no team is selected for adding.
     */
    public void showWarningAtLeastOneTeam() {
        JOptionPane.showMessageDialog(null, ONE_TEAM_MESSAGE,
                NO_TEAMS_SELECTED, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Displays a confirmation dialog for adding teams.
     *
     * @return the user's choice (YES or NO)
     */
    public int showAreYouSure() {
        int confirm = JOptionPane.showConfirmDialog(null, AYS_MESSAGE,
                ADD_TEAMS, JOptionPane.YES_NO_OPTION);
        return confirm;
    }

    /**
     * Displays an exception message.
     *
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
