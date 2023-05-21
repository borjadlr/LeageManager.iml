package Persistance;

import java.util.List;

public interface UserTeamsDAOInt {
    void createUserTeam(String dniJugador, String nombreEquipo);
    void readUserTeam(String dniJugador, String nombreEquipo);
    void updateUserTeam(String dniJugador, String nombreEquipo, String nuevoEquipo);
    void deleteUserTeam(String dniJugador, String nombreEquipo);
    List<String> getPlayersDNI(String teamName);

}
