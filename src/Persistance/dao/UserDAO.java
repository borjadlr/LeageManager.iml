package Persistance.dao;

import Business.Entities.User;
import Persistance.UserDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements UserDAOInt {

    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
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

    public void InsertDataUser(String dni, String email, String pass_jugador, int number, String phone) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos una sentencia sql para insertar en la tabla user sus parametros
            String sql = "INSERT INTO jugador (dni, email, contrasena, dorsal, telefono) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(3, pass_jugador);
            statement.setString(2, email);
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
    public void InsertDataUser2(User jugador) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");



            //Generamos una sentencia sql para insertar en la tabla user sus parametros
            String sql = "INSERT INTO jugador (dni, email, contrasena, dorsal, telefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, jugador.getDni());
            statement.setString(2, jugador.getEmail());
            statement.setString(3, jugador.getPassword());
            statement.setInt(4, jugador.getNumber());
            statement.setString(5, jugador.getPhone());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void UpdateDataUser(String dni1, String email,String pass_jugador,int number, String phone, String dni2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para actualizar la tabla user dependiendo del username
            String sql = "UPDATE jugador SET dni=?,email=?,contrasena=?,dorsal = ?,telefono = ? WHERE dni=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni1);
            statement.setString(3, pass_jugador);
            statement.setString(2, email);
            statement.setInt(4, number);
            statement.setString(5, phone);
            statement.setString(6, dni2);



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public void actualizarJugador(User jugador) throws SQLException {
        String sql = "UPDATE jugador SET email = ?, contrasena = ?, dorsal = ?, telefono = ? WHERE dni = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, jugador.getEmail());
        statement.setString(2, jugador.getPassword());
        statement.setInt(3, jugador.getNumber());
        statement.setString(4, jugador.getPhone());
        statement.setString(5, jugador.getDni());
        statement.executeUpdate();
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
            String sql = "DELETE FROM jugador WHERE dni = ?";

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
            String sql = "SELECT * FROM jugador";
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


    public User obtenerJugadorPorDni(String dni) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Conexion ok");

            // Generamos statement SQL para seleccionar los datos de la tabla jugadores por el DNI
            String sql = "SELECT * FROM jugador WHERE dni = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String email = result.getString("email");
                String contrasena = result.getString("contrasena");
                int dorsal = result.getInt("dorsal");
                String telefono = result.getString("telefono");
                User jugador = new User(dni, email, contrasena, dorsal, telefono);
                return jugador;
            } else {
                return null;
            }

            // Si no existe un jugador con ese DNI, retornamos null


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public List<String> obtenerDNIs() throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos statement sql para seleccionar de la tabla user.
            String sql = "SELECT dni FROM jugador";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            //blucle para ir llenando la linked list con la informacion extraida de la base de datos
            List<String> dniList = new ArrayList<>();

            while (rs.next()) {
                String dni = rs.getString("dni");
                dniList.add(dni);
            }
            statement.close();
            return dniList ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM jugador";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String dni = rs.getString("dni");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int dorsal = rs.getInt("dorsal");
                String phone = rs.getString("phone");

                User user = new User(dni, email, password, dorsal, phone);
                users.add(user);
            }
        }

        return users;
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
