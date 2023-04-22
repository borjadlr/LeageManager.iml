package Persistance;

import Business.Entities.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface UserDAOInt {
    public void InsertDataUser(String dni, String email,int id_equipo, String pass, int number, String phone);

    public void DeleteDataUser(String dni);

    public void UpdateDataUser(String dni1, String email,int id_equipo, String pass,int number, String phone, String dni2);

    public LinkedList<User> SelectDataUser();

    public List<String> obtenerDNIs() throws SQLException;

    public void InsertDataUser2(User user);
}
