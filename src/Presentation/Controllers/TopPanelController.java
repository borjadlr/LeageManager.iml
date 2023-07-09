package Presentation.Controllers;

import Business.Managers.UserManager;
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
    private final MainFrameGUI mainFrame;
    private final TopPanelGUI view;
    private final UserManager userManager;
    private static final String DROPDOWN_BUTTON = "DROPDOWN_BUTTON";
    private static final String LOGOUT_BUTTON = "LOGOUT_BUTTON";
    private static final String DELETE_ACCOUNT_BUTTON = "DELETE_ACCOUNT_BUTTON";
    private static final String BACK_BUTTON = "BACK_BUTTON";


    /**
     * Constructs a TopPanelController object.
     * @param mainFrame The main frame GUI object.
     * @param view The top panel GUI object.
     */
    public TopPanelController(MainFrameGUI mainFrame, TopPanelGUI view, UserManager userManager){
        this.mainFrame = mainFrame;
        this.view = view;
        this.userManager = userManager;
    }

    /**
     * Performs an action in response to an event.
     * @param e The event that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case DROPDOWN_BUTTON -> view.showDropdownMenu();
            case LOGOUT_BUTTON -> logout();
            case DELETE_ACCOUNT_BUTTON -> deleteAccount();
            case BACK_BUTTON -> mainFrame.showGoBack();
        }
    }

    /**
     * Logs out the user from the application.
     * Asks for confirmation before logging out.
     */
    private void logout() {
        if (view.messageAreYouSure() == JOptionPane.YES_OPTION) {
            mainFrame.showMainPanel();
            userManager.logOut();
        }
    }

    /**
     * Deletes the user account.
     * Asks for confirmation before deleting the account.
     */
    private void deleteAccount() {
        int confirm = view.messageAreYouSure();
        if (confirm == JOptionPane.YES_OPTION) {
            mainFrame.deleteAccount();
        }
    }
}

