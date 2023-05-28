package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.TopPanelGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * The controller class for the top panel of the user interface.
 * It handles the actions performed by the buttons in the top panel.
 */
public class TopPanelController implements ActionListener {
    public MainFrameGUI mainFrame;
    public TopPanelGUI view;

    /**
     * Constructs a TopPanelController object.
     *
     * @param mainFrame The main frame GUI object.
     * @param view The top panel GUI object.
     */
    public TopPanelController(MainFrameGUI mainFrame, TopPanelGUI view){
        this.mainFrame = mainFrame;
        this.view = view;
    }

    /**
     * Performs an action in response to an event.
     *
     * @param e The event that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("DROPDOWN_BUTTON")) {
            view.showDropdownMenu();
        } else if (command.equals("LOGOUT_BUTTON")) {
            logout();
        } else if (command.equals("DELETE_ACCOUNT_BUTTON")) {
            deleteAccount();
        } else if (command.equals("BACK_BUTTON")) {
            mainFrame.showGoBack();
        }
    }

    /**
     * Logs out the user from the application.
     * Asks for confirmation before logging out.
     */
    private void logout() {
        if (view.messageAreYouSure() == JOptionPane.YES_OPTION) {
            mainFrame.showMainPanel();
        }
    }

    /**
     * Deletes the user account.
     * Asks for confirmation before deleting the account.
     */
    private void deleteAccount() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Delete Account", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            mainFrame.deleteAccount();
        }
    }
}

