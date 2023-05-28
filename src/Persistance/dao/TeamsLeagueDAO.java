package Persistance.dao;

import Business.Entities.Config;
import Business.Entities.Team;
import Persistance.TeamsLeagueDAOInt;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsLeagueDAO implements TeamsLeagueDAOInt {
    private String dbURL;
    private String username;
    private String password;
    private Connection conn;

    public TeamsLeagueDAO() {
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

    public List<Team> obtenerEquiposPorLigaTeam(String nombreLiga) {
        List<Team> equipos = new ArrayList<>();
        String query = "SELECT nombre_equipo, num_jugadores, victorias, empates, derrotas, puntos FROM equipo_liga WHERE nombre_liga = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreLiga);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreEquipo = resultSet.getString("nombre");
                int numJugadores = resultSet.getInt("num_jugadores");
                int victorias = resultSet.getInt("num_victorias");
                int empates = resultSet.getInt("num_empates");
                int derrotas = resultSet.getInt("num_derrotas");
                int puntos = resultSet.getInt("puntos_acumulados");

                Team equipo = new Team(nombreEquipo, numJugadores, victorias, empates, derrotas, puntos);
                equipos.add(equipo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(equipos);
        return equipos;
    }



}