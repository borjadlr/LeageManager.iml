package Persistance;

import Business.Entities.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface UserDAOInt {
    void InsertDataUser(String dni, String email, String pass_jugador, int number, String phone);
    void InsertDataUser2(User jugador);
    void UpdateDataUser(String dni1, String email,String pass_jugador,int number, String phone, String dni2);
    void actualizarJugador(User jugador) throws SQLException;
    void DeleteDataUser(String dni);
    void DeleteDataUserEmail(String email);
    boolean updatePassword(String email, String newPassword);
    LinkedList<User> SelectDataUser();
    List<User> getAllUsers() throws SQLException;
    List<String> obtenerDNIs() throws SQLException;
    List<String> getLeaguesOfPlayer(String dni);

}
