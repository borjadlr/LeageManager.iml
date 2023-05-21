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

    public UserDAO() {
        try {
            // Establecer la conexión aquí
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


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


    public void actualizarJugador(User jugador) {
        try {
            System.out.println("Successful connection...");

            String sql = "UPDATE jugador SET email = ?, contrasena = ?, dorsal = ?, telefono = ? WHERE dni = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, jugador.getEmail());
            statement.setString(2, jugador.getPassword());
            statement.setInt(3, jugador.getNumber());
            statement.setString(4, jugador.getPhone());
            statement.setString(5, jugador.getDni());

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
     * Elimina un usuario y todas sus relaciones en todas las tablas donde se guarda información del usuario.
     *
     * @param email El correo electrónico del usuario que será eliminado.
     */
    public void DeleteDataUserEmail(String email) {
        // Conectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexión ok...");

            // Primero, obtenemos el DNI del jugador usando el correo electrónico
            String sqlFetch = "SELECT dni FROM jugador WHERE email = ?";
            PreparedStatement statementFetch = conn.prepareStatement(sqlFetch);
            statementFetch.setString(1, email);
            ResultSet result = statementFetch.executeQuery();
            if (result.next()) {
                String dni = result.getString("dni");

                // Segundo, eliminamos las filas correspondientes en la tabla jugador_equipo
                String sqlDeleteEquipo = "DELETE FROM jugador_equipo WHERE dni_jugador = ?";
                PreparedStatement statementDeleteEquipo = conn.prepareStatement(sqlDeleteEquipo);
                statementDeleteEquipo.setString(1, dni);
                statementDeleteEquipo.executeUpdate();

                // Finalmente, eliminamos la fila de la tabla jugador
                String sqlDeleteJugador = "DELETE FROM jugador WHERE dni = ?";
                PreparedStatement statementDeleteJugador = conn.prepareStatement(sqlDeleteJugador);
                statementDeleteJugador.setString(1, dni);
                int rowsDeleted = statementDeleteJugador.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("¡Un usuario ha sido eliminado con éxito!");
                }
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

                dni = rs.getString("dni");
                emails = rs.getString("email");
                pass = rs.getString("contrasena");
                phone = rs.getString("telefono");
                number = rs.getInt("dorsal");

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
                String password = rs.getString("contrasena");
                int dorsal = rs.getInt("dorsal");
                String phone = rs.getString("telefono");

                User user = new User(dni, email, password, dorsal, phone);
                users.add(user);
            }
        }

        return users;
    }


    public boolean updatePassword(String email, String newPassword) {
        String updateQuery = "UPDATE jugador SET contrasena = ? WHERE email = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obtener las ligas en las que un jugador está registrado.
     *
     * @param dni El DNI del jugador.
     * @return Lista de nombres de ligas en las que participa el jugador.
     */
    public List<String> getLeaguesOfPlayer(String dni) {
        // Creamos una lista para guardar los nombres de las ligas
        List<String> leagueNames = new ArrayList<>();

        // Nos conectamos a la base de datos y controlamos las excepciones
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");

            // Generamos una consulta SQL para obtener los nombres de las ligas en las que participa el jugador
            String sql = "SELECT l.nombre " +
                    "FROM liga l " +
                    "INNER JOIN equipo_liga el ON l.nombre = el.nombre_liga " +
                    "INNER JOIN jugador_equipo je ON el.nombre_equipo = je.nombre_equipo " +
                    "WHERE je.dni_jugador = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dni);

            // Ejecutamos la consulta y procesamos el ResultSet
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    leagueNames.add(rs.getString("nombre"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Devolvemos los nombres de las ligas
        return leagueNames;
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
