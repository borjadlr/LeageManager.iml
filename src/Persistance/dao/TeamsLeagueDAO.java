package Persistance.dao;

import Persistance.TeamsLeagueDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsLeagueDAO implements TeamsLeagueDAOInt {
    private Connection connection;

    public TeamsLeagueDAO() {
        // Inicializar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/league_manager_data";
        String username = "dreamteam";
        String password = "dreamteam";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo registro en la tabla equipo_liga
    public void insertarEquipoLiga(String nombreEquipo, String nombreLiga) {
        String query = "INSERT INTO equipo_liga (nombre_equipo, nombre_liga) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreEquipo);
            statement.setString(2, nombreLiga);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerEquiposPorLiga(String nombreLiga) {
        List<String> equipos = new ArrayList<>();
        String query = "SELECT nombre_equipo FROM equipo_liga";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreEquipo = resultSet.getString("nombre_equipo");
                equipos.add(nombreEquipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipos;
    }
}