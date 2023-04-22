package Persistance;

import Business.Entities.User;

import java.util.LinkedList;

public interface UserDAOInt {
    public void InsertDataUser(String dni, String email,int id_equipo, String pass, int number, String phone);

    public void DeleteDataUser(String dni);

    public void UpdateDataUser(String dni1, String email,int id_equipo,String pass,int number, String phone, String dni2);

    public LinkedList<User> SelectDataUser();


    void InsertDataUser2(User user);
}
