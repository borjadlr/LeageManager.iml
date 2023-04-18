package Business.Managers;

import Business.Entities.User;
import Exceptions.*;
import Persistance.UserDAOInt;

import java.util.List;

public class UserManager {
    private final UserDAOInt userDAO;
    private final LeagueManager leagueManager;
    private User userLocal;

    public UserManager(UserDAOInt userDAO, LeagueManager leagueManager, User user) {
        this.userDAO = userDAO;
        this.leagueManager = leagueManager;
        this.userLocal = user;
    }


    public void signIn(String input, String password) throws DNIDontExistException, IncorrectPassword4UserException {
        int i = 0;
        List<User> users = userDAO.getAllUsers();

        if (input.equalsIgnoreCase("admin")){
            adminManager();
            return;
        }

        while (i < users.size()) {
            if (users.get(i).getDni().equals(input) || users.get(i).getEmail().equals(input)) {
                if (users.get(i).getPassword().equals(password)) {
                    userLocal = createUser(input, password, users.get(i).getEmail());
                    return;
                } else {
                    throw new IncorrectPassword4UserException();
                }
            } else {
                i++;
            }
        }
        throw new DNIDontExistException();
    }

    public void adminManager(){

    }


    public void signUp(User user, String password) throws InvalidPasswordException, EmailAlreadyExistsException, ExistingDNIException, DNIDontExistException, InvalidEmailException, SamePasswordException {
        List<User> users = userDAO.getAllUsers();
        int i = 0;

        if (user.getPassword().equals(password)) {
            while (i < users.size()) {
                if (users.get(i).getEmail().equals(user.getEmail())) {
                    throw new EmailAlreadyExistsException();
                } else if (users.get(i).getDni().equals(user.getDni())) {
                    throw new ExistingDNIException();
                } else if (!comprovaCaractersPassword(user)) {
                    throw new InvalidPasswordException();
                } else if (!comprovaCaractersMail(user)) {
                    throw new InvalidEmailException();
                } else {
                    i++;
                }

            }
            userLocal = user;
            userDAO.InsertDataUser(user);

        } else {
            throw new SamePasswordException();
        }

    }

    public boolean comprovaCaractersPassword(User user) {
        String password = user.getPassword();
        final int MIN_Uppercase = 1;
        final int MIN_Lowercase = 1;
        final int MIN_Number = 1;
        final int MIN_Total = 8;
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;
        int digitCounter = 0;


        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c))
                uppercaseCounter++;
            else if (Character.isLowerCase(c))
                lowercaseCounter++;
            else if (Character.isDigit(c))
                digitCounter++;
        }

        return password.length() >= MIN_Total && uppercaseCounter >= MIN_Uppercase
                && lowercaseCounter >= MIN_Lowercase && digitCounter >= MIN_Number;
    }

    public boolean comprovaCaractersMail(User user) {
        String email = user.getEmail();
        final char dot = '.';
        final char arroba = '@';
        final int MIN_Dot = 1;
        final int MIN_Arroba = 1;
        int dotCounter = 0;
        int arrobaCounter = 0;


        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c == dot)
                dotCounter++;
            else if (c == arroba)
                arrobaCounter++;
        }

        return dotCounter >= MIN_Dot && arrobaCounter >= MIN_Arroba;
    }

    /*public boolean comprovaDNI(User user){

    }*/

    public User createUser(String username, String password, String email) {
        userLocal.;
        userLocal.setPassword(password);
        userLocal.setEmail(email);
        return userLocal;
    }


    public void deleteUser(String username, String password) throws DNIDontExistException, IncorrectPassword4UserException {
        List<User> users = userDAO.getAllUsers();
        //ArrayList<String> leagues = songManager.getSongByCreator(username);
        int i = 0, j = 0;
        while (i < users.size()) {
            if (users.get(i).getDni().equals(username)) {
                if (users.get(i).getPassword().equals(password)) {
                    /*while (songs.size() > j) {
                        songManager.deleteSong(songs.get(j), username, password);
                        j++;
                    }*/
                    userDAO.deleteUser(username);
                    logOut();
                    return;
                } else {
                    throw new IncorrectPassword4UserException();
                }
            } else i++;
        }
        throw new DNIDontExistException();
    }

    public void logOut() {
        userLocal.setDni(null);
        userLocal.setEmail(null);
        userLocal.setPassword(null);
    }

    public String getUsername() {
        return userLocal.getDni();
    }
}

