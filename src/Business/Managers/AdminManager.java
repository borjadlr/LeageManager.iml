package Business.Managers;

import Business.Entities.Admin;

/**
 * La clase AdminManager gestiona las operaciones relacionadas con los administradores.
 */
public class AdminManager {

    /**
     * Constructor de la clase AdminManager.
     */
    public AdminManager() {
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

