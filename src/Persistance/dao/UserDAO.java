package Persistance.dao;

import Business.Entities.User;
import Persistance.UserDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements UserDAOInt {

    private static String dbURL = "jdbc:mariadb://localhost:3306/testeando";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;
    private static User user;


    /**
     * Metodo que se emplea para insertar en la base de datos los datos de un usuario.
     * @param dni identificador del usuario
     * @param email email del usuario
     * @param pass_jugador password del usuario
     * @param number numero de dorsal
     * @param phone telefono del usuario
     */

    public void InsertDataUser(String dni, String email,int id_equipo, String pass_jugador, int number, String phone) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos una sentencia sql para insertar en la tabla user sus parametros
            String sql = "INSERT INTO jugadores (dni_jugador,pass_jugador,id_equipo,email_jugador,numero_jugador,tel_jugador) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(2, pass_jugador);
            statement.setInt(3, id_equipo);
            statement.setString(4, email);
            statement.setInt(5, number);
            statement.setString(6, phone);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void InsertDataUser2(User user) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");



            //Generamos una sentencia sql para insertar en la tabla user sus parametros
            String sql = "INSERT INTO jugadores (dni_jugador,email_jugador,constrasena_jugador,numero_jugador,tel_jugador) VALUES (?, ?, ?, ?, ?)";



            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getDni());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getNumber());
            statement.setString(5, user.getPhone());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Metodo que se utiliza para actualizar en base de datos a que liga pertenece el usuario de un usuario.
     * @param dni1 dni a actualizar
     *
     */

    public void UpdateLeagueUser(String dni1,int id_equipo){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para actualizar la row id_equipo de la tabla user  dependiendo del dni
            String sql = "UPDATE jugadores SET id_equipo=? WHERE dni_jugador=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(2, dni1);
            statement.setInt(1, id_equipo);



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void UpdateDataUser(String dni1, String email,int id_equipo,String pass_jugador,int number, String phone, String dni2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para actualizar la tabla user dependiendo del username
            String sql = "UPDATE jugadores SET dni_jugador=?,email_jugador=?,id_equipo=?,pass_jugador = ?,numero_jugador = ?,tel_jugador = ? WHERE dni_jugador=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni1);
            statement.setString(4, pass_jugador);
            statement.setInt(3, id_equipo);
            statement.setString(2, email);
            statement.setInt(5, number);
            statement.setString(6, phone);
            statement.setString(7, dni2);



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
            String sql = "DELETE FROM jugadores WHERE dni_jugador = ?";

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
            String sql = "SELECT * FROM jugadores";
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

                User newUser = new User(dni, emails, pass, number, phone);
                users.add(newUser);


            }
            statement.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public List<String> obtenerDNIs() throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos statement sql para seleccionar de la tabla user.
            String sql = "SELECT dni_jugador FROM jugadores";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            //blucle para ir llenando la linked list con la informacion extraida de la base de datos
            List<String> dniList = new ArrayList<>();

            while (rs.next()) {
                String dni = rs.getString("dni_jugador");
                dniList.add(dni);
            }
            statement.close();
            return dniList ;
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
