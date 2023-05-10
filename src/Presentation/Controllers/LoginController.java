package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.*;
import Exceptions.DNIException;
import Presentation.Views.LoginGUI;
import Presentation.Views.MainFrameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private final MainFrameGUI mainView;
    private final LoginGUI view;
    private final UserManager userManager;

    private static final String VALIDATE_LOGIN = "Validate Login";
    private static final String BACK_LOGIN = "Back Login";

    public LoginController(MainFrameGUI mainView, LoginGUI view, UserManager userManager) {
        this.mainView = mainView;
        this.view = view;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case VALIDATE_LOGIN:
                String username = view.getUsernameInfo();
                String password = view.getPasswordInfo();
                try {
                    userManager.signIn(username,password);
                    if(username.equals("admin") && password.equals("admin")){
                        mainView.showMenuAdmin();
                    }else {
                        mainView.showMenuUser();
                    }

                } catch (DNIDontExistException ex) {
                   view.userNotExistMessage();
                } catch (IncorrectPassword4UserException ex) {
                   view.passwordIsWrong();
                }
                break;

                default:

        }
    }
}
