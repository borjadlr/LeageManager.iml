package Presentation.Controllers;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowLeagueController implements ListSelectionListener {
    private JTable table;
    private DefaultTableModel tableModel;

    public ShowLeagueController() {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable table = (JTable) e.getSource();
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Handle row selection here
                String leagueName = table.getValueAt(selectedRow, 0).toString();
                String teamCount = table.getValueAt(selectedRow, 1).toString();
                String status = table.getValueAt(selectedRow, 2).toString();
                System.out.println("Selected League: " + leagueName);
                System.out.println("Number of Teams: " + teamCount);
                System.out.println("Status: " + status);
            }
        }
    }
}

