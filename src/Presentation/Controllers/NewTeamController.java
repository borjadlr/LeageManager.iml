package Presentation.Controllers;

import Business.Managers.TeamManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.NewTeamGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

/**
 * The controller class for the NewTeamGUI.
 * Handles the actions and events in the new team creation view.
 */
public class NewTeamController implements ActionListener {
    private final MainFrameGUI mainFrame;
    private final NewTeamGUI view;

    private final TeamManager teamManager;

    /**
     * Constructs a NewTeamController object.
     *
     * @param mainFrame   The MainFrameGUI instance.
     * @param view        The NewTeamGUI instance.
     * @param teamManager The TeamManager instance.
     */
    public NewTeamController(MainFrameGUI mainFrame, NewTeamGUI view, TeamManager teamManager) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
    }


    /**
     * Handles the action events in the new team creation view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if ("SEARCH_BUTTON".equals(e.getActionCommand())) {
                mainFrame.showNewTeam();
                try {
                    searchFile();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    System.out.println(view.getName());
                    teamManager.createTeam(view.getName());
                } catch (SQLException | NullPointerException ex) {
                    mainFrame.showMenuAdmin();
                }
            }
        }
    }


    /**
     * Displays a file chooser dialog to select a JSON file containing team data.
     * Creates a new team using the selected file.
     *
     * @throws SQLException if an error occurs during team creation.
     */
    private void searchFile() throws SQLException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            teamManager.createTeam(filePath);
            JOptionPane.showMessageDialog(null, "New teams have been added correctly");
        }
    }
}
