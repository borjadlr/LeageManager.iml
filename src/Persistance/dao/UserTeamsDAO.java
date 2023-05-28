package Persistance.dao;

import Business.Entities.Config;
import Business.Entities.User;
import Persistance.UserTeamsDAOInt;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the UserTeamsDAOInt interface and provides methods for interacting with the user_teams table in the database.
 */
public class UserTeamsDAO implements UserTeamsDAOInt {

    private String dbURL;
    private String username;
    private String password;
    private Connection conn;

    /**
     * Constructs a new UserTeamsDAO object.
     * Reads the database configuration from a JSON file and establishes a connection to the database.
     */
    public UserTeamsDAO() {
        try {
            // Read the JSON configuration and retrieve the corresponding values
            ConfigJsonDAO configJsonDAO = new ConfigJsonDAO();
            Config config = configJsonDAO.leerConfiguracionJson("C:\\Users\\borja\\LeageManager\\Files\\configs.json");

            // Assign the obtained values to the dbURL, username, and password variables
            dbURL = "jdbc:mysql://" + config.getIpServidorBD() + ":" + config.getPortConexionBD() + "/" + config.getNombreBD();
            username = config.getUsuarioBD();
            password = config.getContrasenaBD();

            // Establish the database connection here
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a new record in the jugador_equipo table.
     *
     * @param dniJugador   the DNI of the player
     * @param nombreEquipo the name of the team
     */
    public void createUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Prepare the SQL query
            String query = "INSERT INTO jugador_equipo (dni_jugador, nombre_equipo) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a record from the jugador_equipo table.
     *
     * @param dniJugador   the DNI of the player
     * @param nombreEquipo the name of the team
     */
    public void readUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Prepare the SQL query
            String query = "SELECT * FROM jugador_equipo WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the results
            while (resultSet.next()) {
                // Read the data from the row
                String dni = resultSet.getString("dni_jugador");
                String equipo = resultSet.getString("nombre_equipo");

                // Do something with the data...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a record in the jugador_equipo table.
     *
     * @param dniJugador    the DNI of the player
     * @param nombreEquipo  the name of the team
     * @param nuevoEquipo   the new name of the team
     */
    public void updateUserTeam(String dniJugador, String nombreEquipo, String nuevoEquipo) {
        try {
            // Prepare the SQL query
            String query = "UPDATE jugador_equipo SET nombre_equipo = ? WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoEquipo);
            statement.setString(2, dniJugador);
            statement.setString(3, nombreEquipo);

            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a record from the jugador_equipo table.
     *
     * @param dniJugador   the DNI of the player
     * @param nombreEquipo the name of the team
     */
    public void deleteUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Prepare the SQL query
            String query = "DELETE FROM jugador_equipo WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener la lista de todos los jugadores en un equipo.
     *
     * @param teamName El nombre del equipo.
     * @return LinkedList de objetos User que representan a los jugadores en el equipo.
     */
    public LinkedList<User> getTeamPlayers(String teamName) {
        // Creamos una LinkedList para guardar los objetos User de los jugadores
        LinkedList<User> players = new LinkedList<>();

        // Nos conectamos a la base de datos y controlamos las excepciones
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");

            // Generamos una consulta SQL para obtener los detalles de los jugadores en el equipo
            String sql = "SELECT j.* " +
                    "FROM jugador j INNER JOIN jugador_equipo je ON j.dni = je.dni_jugador " +
                    "WHERE je.nombre_equipo = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teamName);

            // Ejecutamos la consulta y procesamos el ResultSet
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setDni(rs.getString("dni"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("contrasena"));
                    user.setNumber(rs.getInt("dorsal"));
                    user.setPhone(rs.getString("telefono"));

                    // Añadimos el objeto User a la LinkedList
                    players.add(user);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Devolvemos la LinkedList de objetos User
        return players;
    }



}
