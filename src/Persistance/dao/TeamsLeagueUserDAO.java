package Persistance.dao;

import Persistance.TeamsLeagueUserDAOInt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamsLeagueUserDAO implements TeamsLeagueUserDAOInt {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/league_manager_data";
        String username = "tu_usuario";
        String password = "tu_contraseña";
        return DriverManager.getConnection(url, username, password);
    }

    public void insertarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO jugador_equipo_liga (dni_jugador, nombre_equipo, nombre_liga) VALUES (?, ?, ?)")) {
            stmt.setString(1, dniJugador);
            stmt.setString(2, nombreEquipo);
            stmt.setString(3, nombreLiga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga,
                                            String nuevoDniJugador, String nuevoNombreEquipo, String nuevoNombreLiga) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE jugador_equipo_liga SET dni_jugador = ?, nombre_equipo = ?, nombre_liga = ? " +
                             "WHERE dni_jugador = ? AND nombre_equipo = ? AND nombre_liga = ?")) {
            stmt.setString(1, nuevoDniJugador);
            stmt.setString(2, nuevoNombreEquipo);
            stmt.setString(3, nuevoNombreLiga);
            stmt.setString(4, dniJugador);
            stmt.setString(5, nombreEquipo);
            stmt.setString(6, nombreLiga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM jugador_equipo_liga WHERE dni_jugador = ? AND nombre_equipo = ? AND nombre_liga = ?")) {
            stmt.setString(1, dniJugador);
            stmt.setString(2, nombreEquipo);
            stmt.setString(3, nombreLiga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerEquiposPorJugador(String dniJugador) {
        List<String> equipos = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT nombre_equipo FROM jugador_equipo_liga WHERE dni_jugador = ?")) {
            stmt.setString(1, dniJugador);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombreEquipo = rs.getString("nombre_equipo");
                equipos.add(nombreEquipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public List<String> obtenerJugadoresPorEquipo(String nombreEquipo, String nombreLiga) {
        List<String> jugadores = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT dni_jugador FROM jugador_equipo_liga WHERE nombre_equipo = ? AND nombre_liga = ?")) {
            stmt.setString(
                    1, nombreEquipo);
            stmt.setString(2, nombreLiga);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dniJugador = rs.getString("dni_jugador");
                jugadores.add(dniJugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
