package Business.Managers;

import Business.Entities.Admin;
import Business.Entities.User;
import Persistance.UserDAOInt;

public class AdminManager {

    private final UserDAOInt userDAO;

    public AdminManager(UserDAOInt userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isAdmin(String name){
        if (name.equalsIgnoreCase("admin")){
            Admin admin = new Admin();
            admin.setName(name.toLowerCase());
            return true;
        }
        return false;
    }

}
