package Presentation.Views;

import Business.Entities.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The ListPlayerGUI class represents a panel that displays a list of players.
 */
public class ListPlayerGUI extends JPanel {
    private final JLabel titleLabel;
    private final DefaultTableModel tableModel;
    private static final String COLUMN1 = "Dni";
    private static final String COLUMN2 = "Email";
    private static final String COLUMN3 = "Phone";
    private static final String COLUMN4 = "Dorsal";
    private static final String BLANK = "";
    private static final String LetterType = "Inter";


    /**
     * Constructs a ListPlayerGUI object.
     */
    public ListPlayerGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        titleLabel = new JLabel(BLANK);
        titleLabel.setFont(new Font(LetterType, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);


        String[] columnNames = {COLUMN1, COLUMN2, COLUMN3, COLUMN4};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
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
     * Adds a list of players to the table.
     * @param playerNames the list of players to add
     */
    public void addPlayers(List<User> playerNames) {
        tableModel.setRowCount(0);
        for (User playerName : playerNames) {
            Object[] rowData = {playerName.getDni(), playerName.getEmail(), playerName.getPhone(), playerName.getNumber()};
            tableModel.addRow(rowData);
        }
    }

    /**
     * Sets the title of the panel.
     * @param title the title to set
     */
    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}