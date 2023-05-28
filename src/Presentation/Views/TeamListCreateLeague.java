package Presentation.Views;
import Business.Entities.Team;
import Presentation.Controllers.TeamListCreateLeagueController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TeamListCreateLeague extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;
    private final JButton add;

    public TeamListCreateLeague() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set the background color of the panel to white

        // Create a title label
        titleLabel = new JLabel("List of Teams Create New League");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create the table with column names
        String[] columnNames = {"Team Name", "Select"};
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

        // Set cell renderer for aligning the content
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Create the OK button
        add = new JButton("Add Teams");
        add.setPreferredSize(new Dimension(120, 30)); // Set the preferred size of the button

        // Set the layout manager for the bottom area
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(add);
        // Add the bottom panel to the main panel
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addTeams(List<Team> teams) {
        tableModel.setRowCount(0);
        // Add team data to the table
        for (Team team : teams) {
            Object[] rowData = {team.getName(),false};
            tableModel.addRow(rowData);
        }
    }

   public void setController(TeamListCreateLeagueController controller) {
        table.addMouseListener(controller);
        add.addActionListener(controller);
    }


    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public JTable getTable() {
        return table;
    }

    public void showWarningAtLeastOneTeam(){
        JOptionPane.showMessageDialog(null, "Please select at least one team to add.",
                "No Team Selected", JOptionPane.WARNING_MESSAGE);
    }

    public int showAreYouSure(){
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to add?",
                "Add teams", JOptionPane.YES_NO_OPTION);
        return confirm;
    }

    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
