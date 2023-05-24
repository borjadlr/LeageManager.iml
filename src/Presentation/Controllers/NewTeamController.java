package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.NewTeamGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewTeamController implements ActionListener {
    private JTextField filePathField;
    private MainFrameGUI mainFrame;
    private NewTeamGUI view;


    public NewTeamController(MainFrameGUI mainFrame, NewTeamGUI view){
        this.view = view;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            switch (e.getActionCommand()){
                case "SEARCH_BUTTON":
                    //mainFrame.showNewTeam();
                    searchFile();
                    //Crida logica
                    break;
            }
        }
    }


    private void searchFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = ((File) selectedFile).getAbsolutePath();
            System.out.println(filePath);
        }
    }
}
