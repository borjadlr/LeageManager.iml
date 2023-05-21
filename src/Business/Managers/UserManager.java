
package Business.Managers;

import Business.Entities.Admin;
import Business.Entities.League;
import Business.Entities.Team;
import Business.Entities.User;
import Exceptions.*;
import Persistance.TeamsLeagueDAOInt;
import Persistance.UserDAOInt;
import Persistance.UserTeamsDAOInt;
import Persistance.dao.TeamsLeagueDAO;
import Persistance.dao.UserDAO;
import Persistance.dao.UserTeamsDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UserManager {
    private final UserDAOInt userDAO;
    private final LeagueManager leagueManager;

    private final TeamManager teamManager;

    private final UserTeamsDAOInt userTeamsDAOInt;

    private final TeamsLeagueDAOInt teamsLeagueDAOInt;
    private User userLocal;

    private final AdminManager adminManager;

    public UserManager(UserDAOInt userDAO, LeagueManager leagueManager, TeamManager teamManager, UserTeamsDAOInt userTeamsDAOInt, TeamsLeagueDAOInt teamsLeagueDAOInt, User user, AdminManager adminManager) {
        this.userDAO = userDAO;
        this.leagueManager = leagueManager;
        this.teamManager = teamManager;
        this.userTeamsDAOInt = userTeamsDAOInt;
        this.teamsLeagueDAOInt = teamsLeagueDAOInt;
        this.userLocal = user;
        this.adminManager = adminManager;
    }


    public boolean signIn(String input, String password) throws DNIOrMailDontExistException, IncorrectPassword4UserException {
        int i = 0;
        List<User> users = userDAO.SelectDataUser();

        try {
            while (i < users.size()) {
                if (users.get(i).getDni().equals(input) || users.get(i).getEmail().equals(input)) {
                    if (users.get(i).getPassword().equals(password)) {
                        if (adminManager.isAdmin(input)){
                            return true;
                        }
                        userLocal = users.get(i);
                        return false;
                    } else {
                        throw new IncorrectPassword4UserException();
                    }
                } else {
                    i++;
                }
            }
            throw new DNIOrMailDontExistException();
        } catch (NullPointerException npe){
            throw new NullPointerException();
        }


    }


    public void signUp(User user, String password) throws InvalidPasswordException, EmailAlreadyExistsException, ExistingDNIException, DNIOrMailDontExistException, InvalidEmailException, SamePasswordException, DNIException, SQLException {
        List<User> users = userDAO.SelectDataUser();
        int i = 0;

        try {
            if (user.getPassword().equals(password)) {
                while (i < users.size()) {
                    if (users.get(i).getEmail().equals(user.getEmail())) {
                        throw new EmailAlreadyExistsException();
                    } else if (users.get(i).getDni().equals(user.getDni())) {
                        throw new ExistingDNIException();
                    } else if (!comprovaCaractersPassword(user)) {
                        throw new InvalidPasswordException();
                    } else if (comprovaCaractersMail(user)) {
                        throw new InvalidEmailException();
                    } else if (comprovaDNI(user.getDni())) {
                        throw new DNIException();
                    } else {
                        i++;
                    }

                }
                userLocal = user;
                userDAO.InsertDataUser2(user);

            } else {
                throw new SamePasswordException();
            }
        } catch (NullPointerException npe) {
            if (!comprovaCaractersPassword(user)) {
                throw new InvalidPasswordException();
            } else if (comprovaCaractersMail(user)) {
                throw new InvalidEmailException();
            } else if (comprovaDNI(user.getDni())) {
                throw new DNIException();
            } else {
                userLocal = user;
                userDAO.InsertDataUser2(user);
            }
        }
    }

    public static String generatePassword() {
        String MINUSCULA = "abcdefghijklmnopqrstuvwxyz";
        String MAYUSCULA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NOMBRES = "0123456789";
        String TODOS = MINUSCULA + MAYUSCULA + NOMBRES;
        int LONGITUD = 8;

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        // Una lletra en minuscula
        password.append(MINUSCULA.charAt(random.nextInt(MINUSCULA.length())));

        // Una lletra en mayuscula
        password.append(MAYUSCULA.charAt(random.nextInt(MAYUSCULA.length())));

        // Una numero com a minim
        password.append(NOMBRES.charAt(random.nextInt(NOMBRES.length())));

        // Altres numero random
        for (int i = 0; i < LONGITUD - 3; i++) {
            password.append(TODOS.charAt(random.nextInt(TODOS.length())));
        }

        return password.toString();
    }

    public boolean comprovaCaractersPassword(User user) {
        String password = user.getPassword();
        final int MIN_UPPERCASE = 1;
        final int MIN_LOWERCASE = 1;
        final int MIN_NUMBER = 1;
        final int MIN_TOTAL = 8;
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

        return password.length() >= MIN_TOTAL && uppercaseCounter >= MIN_UPPERCASE
                && lowercaseCounter >= MIN_LOWERCASE && digitCounter >= MIN_NUMBER;
    }

    public boolean comprovaCaractersMail(User user) {
        String email = user.getEmail();
        final char dot = '.';
        final char arroba = '@';
        final int MIN_DOT = 1;
        final int MIN_ARROBA = 1;
        int dotCounter = 0;
        int arrobaCounter = 0;


        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c == dot)
                dotCounter++;
            else if (c == arroba)
                arrobaCounter++;
        }

        return dotCounter < MIN_DOT || arrobaCounter < MIN_ARROBA;
    }

    public boolean comprovaDNI(String dni) throws SQLException {
        boolean isValid = false;
        List<String> dniList = userDAO.obtenerDNIs(); //getAllDNI()
        int i = 0;

        if (dni != null && dni.length() == 8) {
            while (dniList.size() > i){
                i++;
                isValid = isValidDNI(dni);
            }
        }
        return isValid;
    }

    public boolean isValidDNI(String dni) {
        if (dni == null || dni.isEmpty()) {
            return false;
        }
        if (!dni.matches("\\d{8}")) {
            return false;
        }
        int dniNumber = Integer.parseInt(dni.substring(0, 8));
        char verificationLetter = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(dniNumber % 23);
        return dni.charAt(8) == verificationLetter;
    }

    public User createUser(String dni, String password, String email, int number, String phone) {
        userLocal.setDni(dni);
        userLocal.setPassword(password);
        userLocal.setEmail(email);
        userLocal.setNumber(number);
        userLocal.setPhone(phone);
        return userLocal;
    }


    public void deleteUser(String dni, String password) throws DNIOrMailDontExistException, IncorrectPassword4UserException {
        List<User> users = userDAO.SelectDataUser();
        int i = 0;

        while (i < users.size()) {
            if ((users.get(i).getDni().equals(dni) || users.get(i).getEmail().equals(dni)) && isConnected(users.get(i))) {
                if (users.get(i).getPassword().equals(password)) {
                    userDAO.DeleteDataUser(dni);
                    logOut();
                    return;
                } else {
                    throw new IncorrectPassword4UserException();
                }
            } else i++;
        }
        throw new DNIOrMailDontExistException();
    }

    public boolean isConnected(User user) {
        return user.getDni().equals(userLocal.getDni());
    }

    public void logOut() {
        userLocal.setDni(null);
        userLocal.setEmail(null);
        userLocal.setPassword(null);
    }

    public String getDNI() {
        return userLocal.getDni();
    }

    public boolean comparePassword(String password1, String password2) {
        return Objects.equals(password1, password2);
    }

    public void canviContrasenya(String pastPassword, String newPassword, String repeatedPassword) throws InvalidPasswordException, SamePasswordException {
        userLocal.setPassword(newPassword);
        if (comprovaCaractersPassword(userLocal) && comparePassword(repeatedPassword, newPassword)){
            userDAO.updatePassword(userLocal.getEmail(), newPassword);
        } else if (!comprovaCaractersPassword(userLocal)){
            userLocal.setPassword(pastPassword);
            throw new InvalidPasswordException();
        } else {
            userLocal.setPassword(pastPassword);
            throw new SamePasswordException();
        }

    }

    public List<League> getLeagues() throws SQLException {
        List<League> leagues = leagueManager.listLeagues();

        List<League> leaguesUserActive = new ArrayList<>();
        int i = 0;

        if (userLocal instanceof Admin) {
            return leagues;
        } else {
            List<League> leaguesUser = getUserLeagues();
            while (leagues.size() > i){
                if (leagueManager.isLeagueActive(leagues.get(i))){
                    leaguesUserActive.add(leagues.get(i));
                    i++;
                }
            }
            return leaguesUserActive;
        }

    }

    public List<League> getUserLeagues() throws SQLException {
        List<League> leagues = leagueManager.listLeagues();
        List<League> leaguesUser = new ArrayList<>();
        List<String> leaguesNames = userDAO.getLeaguesOfPlayer(userLocal.getDni());
        int i = 0, j = 0;

        if (userLocal instanceof Admin){
            return leagueManager.listLeagues();
        } else {
            while (leagues.size() > i){
                while (leaguesNames.size() > j) {
                    if (leagues.get(i).getName().equals(leaguesNames.get(j))){
                        leaguesUser.add(leagues.get(i));
                        break;
                    }
                    j++;
                }
                j = 0;
                i++;
            }
            return leaguesUser;
        }

    }


}
