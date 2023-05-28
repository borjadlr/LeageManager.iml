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

public class NewTeamController implements ActionListener {
    private JTextField filePathField;
    private MainFrameGUI mainFrame;
    private NewTeamGUI view;

    private TeamManager teamManager;


    public NewTeamController(MainFrameGUI mainFrame, NewTeamGUI view, TeamManager teamManager){
        this.view = view;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            switch (e.getActionCommand()){
                case "SEARCH_BUTTON":
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
                    break;
            }
        }
    }


    private void searchFile() throws SQLException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = ((File) selectedFile).getAbsolutePath();
            teamManager.createTeam(filePath);
            JOptionPane.showMessageDialog(null, "New teams have been added correctly");
        }
    }
}
