package Persistance.dao;

import Business.Entities.User;
import Persistance.UserDAOInt;

import java.sql.*;
import java.util.LinkedList;

public class UserDAO implements UserDAOInt {
    private static String dbURL = "jdbc:mariadb://localhost:3306/leage_manager";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;

    /**
     * Metodo que se emplea para insertar en la base de datos los datos de un usuario.
     * @param dni identificador del usuario
     * @param email email del usuario
     * @param pass password del usuario
     * @param number numero de dorsal
     * @param phone telefono del usuario
     */
    public void InsertDataUser(String dni, String email, String pass,int number, String phone) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos una sentencia sql para insertar en la tabla user sus parametros
            String sql = "INSERT INTO user (DNI,USER_EMAIL,USER_PASSWORD,USER_NUMBER,USER_PHONE) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(2, email);
            statement.setString(3, pass);
            statement.setInt(4, number);
            statement.setString(5, phone);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Metodo que se utiliza para actualizar en base de datos los datos de un usuario.
     * @param dni1 dni a actualizar
     * @param email email del usuario a actualizar
     * @param pass password del usuario a actualizar
     * @param number dorsal del usuario
     * @param phone numero de telefono del usuario
     * @param dni2 antiguo dni del ususario
     */



    public void UpdateDataUser(String dni1, String email,String pass,int number, String phone, String dni2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para actualizar la tabla user dependiendo del username
            String sql = "UPDATE user SET DNI=?,USER_EMAIL=?,USER_PASSWORD = ?,USER_NUMBER = ?,USER_PHONE = ? WHERE USER_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni1);
            statement.setString(2, email);
            statement.setString(3, pass);
            statement.setInt(4, number);
            statement.setString(5, phone);
            statement.setString(5, dni2);



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    /**
     * Metodo que se emplea para eliminar los datos de un usuario.
     * @param dni identificador del usuario
     */


    public void DeleteDataUser(String dni){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para eliminar dependiendo del username
            String sql = "DELETE FROM user WHERE USER_NAME = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Metodo que se utiliza para seleccionar obtener los datos de todos los usuarios.
     * @return users Linked list con los datos del usuario
     */
    public LinkedList<User> SelectDataUser(){
        String dni, emails, pass, phone;
        int number;
        //Creamos linkedlists de user
        LinkedList<User> users = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos statement sql para seleccionar de la tabla user.
            String sql = "SELECT * FROM user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            //blucle para ir llenando la linked list con la informacion extraida de la base de datos
            while (rs.next())
            {

                dni = rs.getString("DNI");
                emails = rs.getString("USER_EMAIL");
                pass = rs.getString("USER_PASSWORD");
                phone = rs.getString("USER_PHONE");
                number = rs.getInt("USER_NUMBER");

                User newUser = new User(dni, emails, pass,number,phone);
                users.add(newUser);


            }
            statement.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }







    /**
     * Metodo que cierra la conexion con la base de datos
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: "+e.getSQLState()+"("+e.getMessage() + ")");
        }
    }
}
