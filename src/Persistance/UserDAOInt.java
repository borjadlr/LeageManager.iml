package Persistance;

import java.util.LinkedList;

public interface UserDAOInt {
    public void InsertDataUser(String dni, String email, String pass,int number, String phone);

    public void DeleteDataUser(String dni);

    public void UpdateDataUser(String dni1, String email,String pass,int number, String phone, String dni2);

    public LinkedList<User> SelectDataUser();
}
