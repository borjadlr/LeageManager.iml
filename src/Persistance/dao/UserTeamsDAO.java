package Persistance.dao;

import Business.Entities.Config;
import Business.Entities.User;
import Persistance.UserTeamsDAOInt;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserTeamsDAO implements UserTeamsDAOInt {

    private String dbURL;
    private String username;
    private String password;
    private Connection conn;

    public UserTeamsDAO() {
        try {

            // Leer la configuración JSON y obtener los valores correspondientes

            ConfigJsonDAO configJsonDAO = new ConfigJsonDAO();

            Config config = configJsonDAO.leerConfiguracionJson("C:\\Users\\borja\\LeageManager\\Files\\configs.json");

            // Asignar los valores obtenidos a las variables dbURL, username y password
            dbURL = "jdbc:mysql://" + config.getIpServidorBD() + ":" + config.getPortConexionBD() + "/" + config.getNombreBD();
            username = config.getUsuarioBD();
            password = config.getContrasenaBD();

            // Establecer la conexión aquí
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para crear un registro en la tabla jugador_equipo
    public void createUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Preparar la consulta SQL
            String query = "INSERT INTO jugador_equipo (dni_jugador, nombre_equipo) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Ejecutar la consulta
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un registro de la tabla jugador_equipo
    public void readUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Preparar la consulta SQL
            String query = "SELECT * FROM jugador_equipo WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados
            while (resultSet.next()) {
                // Leer los datos de la fila
                String dni = resultSet.getString("dni_jugador");
                String equipo = resultSet.getString("nombre_equipo");

                // Hacer algo con los datos...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un registro en la tabla jugador_equipo
    public void updateUserTeam(String dniJugador, String nombreEquipo, String nuevoEquipo) {
        try {
            // Preparar la consulta SQL
            String query = "UPDATE jugador_equipo SET nombre_equipo = ? WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoEquipo);
            statement.setString(2, dniJugador);
            statement.setString(3, nombreEquipo);

            // Ejecutar la consulta
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro de la tabla jugador_equipo
    public void deleteUserTeam(String dniJugador, String nombreEquipo) {
        try {
            // Preparar la consulta SQL
            String query = "DELETE FROM jugador_equipo WHERE dni_jugador = ? AND nombre_equipo = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dniJugador);
            statement.setString(2, nombreEquipo);

            // Ejecutar la consulta
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
