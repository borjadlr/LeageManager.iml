package Presentation.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelController implements ActionListener {

    private static final String MAIN_PANEL_LOGIN = "Login";

    private static final String MAIN_PANEL_REGISTER = "Register";

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MAIN_PANEL_LOGIN:
                //CAMBIA DE VISTA
                break;

            case MAIN_PANEL_REGISTER:
                //CAMBIA DE VISTA
                break;

        }
    }
}
