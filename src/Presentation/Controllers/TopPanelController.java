package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.TopPanelGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class TopPanelController implements ActionListener {
    public MainFrameGUI mainFrame;
    public TopPanelGUI view;
    public TopPanelController(MainFrameGUI mainFrame, TopPanelGUI view){
        this.mainFrame = mainFrame;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "DROPDOWN_BUTTON" -> view.showDropdownMenu();
            case "LOGOUT_BUTTON" -> logout();
            case "DELETE_ACCOUNT_BUTTON" -> deleteAccount();
            case "BACK_BUTTON" -> mainFrame.showGoBack();
        }
    }

    private void logout() {
        if (view.messageAreYouSure() == JOptionPane.YES_OPTION) {
            mainFrame.showMainPanel();
        }
    }

    private void deleteAccount() {

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Delete Account", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            mainFrame.deleteAccount();
        }
    }
}
