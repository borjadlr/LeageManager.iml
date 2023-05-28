package Persistance;

import Business.Entities.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Interface for accessing and manipulating Userteams data in a database
 */
public interface UserTeamsDAOInt {
    void createUserTeam(String dniJugador, String nombreEquipo);
    void readUserTeam(String dniJugador, String nombreEquipo);
    void updateUserTeam(String dniJugador, String nombreEquipo, String nuevoEquipo);
    void deleteUserTeam(String dniJugador, String nombreEquipo);
    LinkedList<User> getTeamPlayers(String teamName);

}
