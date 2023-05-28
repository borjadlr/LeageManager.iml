package Persistance.dao;

import Persistance.TeamsLeagueDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsLeagueDAO implements TeamsLeagueDAOInt {
    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private Connection conn;

    public TeamsLeagueDAO() {
        try {
            // Corregir la asignación de la variable conn
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para insertar un nuevo registro en la tabla equipo_liga
    public void insertarEquipoLiga(String nombreEquipo, String nombreLiga) {
        String query = "INSERT INTO equipo_liga (nombre_equipo, nombre_liga) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreEquipo);
            statement.setString(2, nombreLiga);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un registro de la tabla equipo_liga
    public void actualizarEquipoLiga(String nombreEquipo, String nombreLiga, String nuevoNombreEquipo, String nuevoNombreLiga) {
        String query = "UPDATE equipo_liga SET nombre_equipo = ?, nombre_liga = ? WHERE nombre_equipo = ? AND nombre_liga = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nuevoNombreEquipo);
            statement.setString(2, nuevoNombreLiga);
            statement.setString(3, nombreEquipo);
            statement.setString(4, nombreLiga);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro de la tabla equipo_liga por su nombre de equipo y nombre de liga
    public void eliminarEquipoLiga(String nombreEquipo, String nombreLiga) {
        String query = "DELETE FROM equipo_liga WHERE nombre_equipo = ? AND nombre_liga = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreEquipo);
            statement.setString(2, nombreLiga);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerEquiposPorLiga(String nombreLiga) {
        List<String> equipos = new ArrayList<>();
        String query = "SELECT nombre_equipo FROM equipo_liga WHERE nombre_liga = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreLiga);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreEquipo = resultSet.getString("nombre_equipo");
                equipos.add(nombreEquipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(equipos);
        return equipos;
    }


}