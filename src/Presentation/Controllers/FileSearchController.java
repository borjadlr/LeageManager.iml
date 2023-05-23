package Presentation.Controllers;

import Presentation.Views.FileSearchGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileSearchController implements ActionListener {
    private JTextField filePathField;
    private MainFrameGUI mainFrame;
    private FileSearchGUI view;


    public FileSearchController(MainFrameGUI mainFrame, FileSearchGUI view){
        this.view = view;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            switch (e.getActionCommand()){
                case "SEARCH_BUTTON":
                    mainFrame.fileSearchView();
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
            filePathField.setText(filePath);
        }
    }
}
