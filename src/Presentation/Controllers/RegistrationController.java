package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.DNIDontExistException;
import Exceptions.IncorrectPassword4UserException;
import Presentation.Views.LoginGUI;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.RegistrationGUI;

import java.awt.event.ActionEvent;

public class RegistrationController {

    private final MainFrameGUI mainView;
    private final RegistrationGUI view;
    private final UserManager userManager;

    private static final String VALIDATE_LOGIN = "Validate Login";
    private static final String BACK_LOGIN = "Back Login";

    public RegistrationController(MainFrameGUI mainView, RegistrationGUI view, UserManager userManager) {
        this.mainView = mainView;
        this.view = view;
        this.userManager = userManager;
    }

    /*public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case VALIDATE_LOGIN:
                String username = view.getUsernameInfo();
                String password = view.getPasswordInfo();
                try {
                    userManager.signUp(username,password);

                } catch (DNIDontExistException ex) {
                    view.userNotExistMessage();
                } catch (IncorrectPassword4UserException ex) {
                    view.passwordIsWrong();
                }
                break;
            case BACK_LOGIN:

                break;
            default:

        }
    }
}*/


}
