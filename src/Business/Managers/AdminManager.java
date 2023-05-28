package Business.Managers;

import Business.Entities.Admin;
import Persistance.UserDAOInt;

/**
 * La clase AdminManager gestiona las operaciones relacionadas con los administradores.
 */
public class AdminManager {

    private final UserDAOInt userDAO;
    private final LeagueManager leagueManager;
    private final TeamManager teamManager;

    /**
     * Constructor de la clase AdminManager.
     *
     * @param userDAO        Objeto UserDAOInt que proporciona acceso a los datos de usuario.
     * @param leagueManager  Objeto LeagueManager que gestiona las operaciones relacionadas con las ligas.
     * @param teamManager    Objeto TeamManager que gestiona las operaciones relacionadas con los equipos.
     */
    public AdminManager(UserDAOInt userDAO, LeagueManager leagueManager, TeamManager teamManager) {
        this.userDAO = userDAO;
        this.leagueManager = leagueManager;
        this.teamManager = teamManager;
    }

    /**
     * Verifica si el usuario es un administrador.
     *
     * @param name Nombre del usuario a verificar.
     * @return true si el usuario es un administrador, false de lo contrario.
     */
    public boolean isAdmin(String name) {
        if (name.equalsIgnoreCase("admin")) {
            Admin admin = new Admin();
            admin.setName(name.toLowerCase());
            return true;
        }
        return false;
    }
}

