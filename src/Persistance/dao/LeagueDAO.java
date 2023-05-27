package Persistance.dao;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Persistance.LeagueDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase LeagueDAO que implementa la interfaz LeagueDAOInt. Se encarga de la persistencia de los datos de las ligas en una base de datos SQL.
 */
public class LeagueDAO implements LeagueDAOInt {

    private final String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private final String username = "dreamteam";
    private final String password = "dreamteam";
    private Connection conn;

    public LeagueDAO() {
        try {
            // Establecer la conexión aquí
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para insertar una nueva liga en la base de datos.
     *
     * @param name  nombre de la liga
     * @param date  fecha de inicio de la liga
     * @param hour  hora de inicio de la liga
     * @param day   jornada de la liga
     * @param teams numero de equipos que hay en la liga
     * @param state estado en el que se encuentra la liga (activa o inactiva)
     */
    public void insertDataLeague(String name, java.sql.Date date,Time hour, int day, int teams, boolean state) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "INSERT INTO liga (nombre,fecha,hora,jornada,num_equipos,estado) VALUES (?, ? ,? ,? ,? ,?)";
            System.out.println("okis");

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, date);
            statement.setTime(3, hour);
            statement.setInt(4, day);
            statement.setInt(5, teams);
            statement.setBoolean(6, state);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new league was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para actualizar los datos de una liga existente en la base de datos.
     *
     * @param name1 nuevo nombre que se le quiere dar a la liga
     * @param date  nueva fecha que se le quiere dar a la liga
     * @param hour  nueva hora de inicio que se le quiere dar a la liga
     * @param day   nueva jornada que se le quiere dar a la liga
     * @param teams numero de equipos nuevos que se quiere actualizar en la liga
     * @param state nuevo estado que se le quiere dar a la liga
     * @param name2 nombre de la liga que se quiere actualizar
     */

    public void UpdateDataLeague(String name1, java.sql.Date date, Time hour, int day, int teams, boolean state, String name2) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "UPDATE liga SET nombre = ?,fecha = ?,hora = ?,jornada = ?,num_equipos = ?,estado = ? WHERE nombre= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setDate(2, date);
            statement.setTime(3, hour);
            statement.setInt(4, day);
            statement.setInt(5, teams);
            statement.setBoolean(6, state);
            statement.setString(7, name2);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing league was updated successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    /**
     * Método para eliminar una liga existente de la base de datos.
     *
     * @param name nombre de la liga a eliminar
     */
    public void DeleteDataLeague(String name) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sqlDeleteRelationships = "DELETE FROM equipo_liga WHERE nombre_liga =?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1, name);
            stDelRelationship.executeUpdate();

            String sql = "DELETE FROM liga WHERE nombre=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A league was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para seleccionar una liga de la base de datos.
     *
     * @param name nombre de la liga a seleccionar
     * @return retorna la liga seleccionada o null si no existe
     * @throws SQLException excepción en caso de error de SQL
     */

    /*
    public League selectLeague(String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "SELECT * FROM liga WHERE nombre = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String leagueName = rs.getString("nombre");
                Date dayLeague = rs.getDate("fecha");
                int matchdayLeague = rs.getInt("jornada");
                int numero = rs.getInt("num_equipos");
                boolean estado = rs.getBoolean("estado");

                TeamsLeagueDAO teamsLeagueDAO = new TeamsLeagueDAO();
                List<String> teams = teamsLeagueDAO.obtenerEquiposPorLiga(name);
                List<Match> matches = new ArrayList<>();

                // return new League(leagueName,dayLeague,matchdayLeague,numero,teams,matches,estado);
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
    */
    /**
     * Método para seleccionar todas las ligas de la base de datos.
     *
     * @return retorna la lista de todas las ligas existentes
     * @throws SQLException excepción en caso de error de SQL
     */

    public List<League> getAllLeagues() throws SQLException {
        List<League> ligas = new ArrayList<>();
        String sql = "SELECT * FROM liga";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                java.sql.Date fecha = rs.getDate("fecha");
                Time time = rs.getTime("time");
                int jornada = rs.getInt("jornada");
                int numEquipos = rs.getInt("num_equipos");
                boolean estado = rs.getBoolean("estado");

                List<Team> teams = new ArrayList<>();
                List<Match> matches = new ArrayList<>();

                League liga = new League(nombre, fecha, time, jornada, numEquipos, teams, matches, estado);
                ligas.add(liga);
            }
        }

        return ligas;
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getSQLState() + "(" + e.getMessage() + ")");
        }
    }
}
