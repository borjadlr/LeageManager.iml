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

        if (command.equals("DROPDOWN_BUTTON")) {
            view.showDropdownMenu();
        } else if (command.equals("LOGOUT_BUTTON")) {
            logout();
            mainFrame.showMainPanel();
        } else if (command.equals("DELETE_ACCOUNT_BUTTON")) {
            deleteAccount();
            mainFrame.showMainPanel();
        }else if(command.equals("BACK_BUTTON")){
            mainFrame.showTeamList();
        }
    }

    private void logout() {
        // Lógica para realizar el logout
        // Por ejemplo, cerrar la sesión del usuario actual
        JOptionPane.showMessageDialog(null, "Logout button clicked!");
    }

    private void deleteAccount() {

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Delete Account", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Account deleted!");
        }
    }
}
