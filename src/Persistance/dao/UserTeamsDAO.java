package Persistance.dao;

import Business.Entities.User;
import Persistance.UserTeamsDAOInt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTeamsDAO implements UserTeamsDAOInt {

    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;
    private static User user;

    // Constructor que recibe la conexión a la base de datos
    public UserTeamsDAO() {
        this.conn = conn;
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
}
