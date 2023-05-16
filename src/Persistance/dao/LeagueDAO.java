package Persistance.dao;

import Business.Entities.League;
import Persistance.LeagueDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sql connector LEAGUE.
 */
public abstract class LeagueDAO implements LeagueDAOInt {

    private  String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private  String username = "dreamteam";
    private  String password = "dreamteam";
    private  Connection conn;


    /**
     *
     * @param name nombre de la liga
     * @param date fecha de inicio de la liga
     * @param hour hora de inicio de la liga
     * @param day jornada de la liga
     * @param teams numero de equipos que hay en la liga
     * @param state estat en el que se encuentra la liga (activa o inactiva)
     */


    public void insertDataLeague(String name, Date date, Time hour, int day, int teams, int state) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para inertar datos en la tabla league
            String sql = "INSERT INTO liga (nombre,fecha,hora,jornada,num_equipos,estado) VALUES (?, ? ,? ,? ,? ,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, date);
            statement.setTime(3, hour);
            statement.setInt(4,day);
            statement.setInt(5,teams);
            statement.setInt(6,state);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que actializa los datos de una liga existente.
     * @param name1 nombre nuevo que se le quiere dar a la liga
     * @param date fecha nuevo que se le quiere dar a la liga
     * @param hour hora de inicio nuevo que se le quiere dar a la liga
     * @param day jornada nuevo que se le quiere dar a la liga
     * @param teams numero de equipos nuevos que se quiere actializar en la liga
     * @param state estado nuevo que se le quiere dar a la liga
     * @param name2 nombre de la liga que se quiere actualizar
     */

    public void UpdateDataLeague(String name1,Date date, Time hour, int day, int teams, int state, String name2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para actualizar la tabla liga.
            String sql = "UPDATE liga SET nombre = ?,fecha = ?,hora = ?,jornada = ?,num_equipos = ?,estado = ? WHERE nombre= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setDate(2, date);
            statement.setTime(3, hour);
            statement.setInt(4,day);
            statement.setInt(5,teams);
            statement.setInt(6,state);
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
     * Metodo que se encarga de insertar en la base de datos los datos de una liga.
     * @param name nombre de la liga
     */

    public void DeleteDataLeague(String name){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement SQL que elimina datos de la tabla league_teams dependiendo del nombre de la liga.
            String sqlDeleteRelationships = "DELETE FROM equipo_liga WHERE nombre_liga =?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1,name);
            stDelRelationship.executeUpdate();
            //Generacion de un statement sql para eliminar datos de la tabla league dependeindeo del nombre de la liga.
            String sql = "DELETE FROM liga WHERE nombre=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public League selectLeague(String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Conexion ok");

            // Generamos statement SQL para seleccionar los datos de la tabla jugadores por el DNI
            String sql = "SELECT * FROM liga WHERE nombre = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            // Si el jugador existe, obtenemos la información y lo retornamos
            if (rs.next()) {

                String leagueName = rs.getString("nombre");
                Date dayLeague = rs.getDate("fecha");
                Time hourLeague = rs.getTime("hora");
                int matchdayLeague = rs.getInt("jornada");
                int numero = rs.getInt("num_equipos");
                int estado = rs.getInt("estado");



                return new League(leagueName,dayLeague,hourLeague,matchdayLeague,numero,estado);
            }

            // Si no existe un jugador con ese DNI, retornamos null
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public List<League> getAllLeagues() throws SQLException {
        List<League> ligas = new ArrayList<>();
        String sql = "SELECT * FROM liga";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                Date fecha = rs.getDate("fecha");
                Time hora = rs.getTime("hora");
                int jornada = rs.getInt("jornada");
                int numEquipos = rs.getInt("num_equipos");
                int estado = rs.getInt("estado");

                League liga = new League(nombre, fecha, hora, jornada, numEquipos,estado);
                ligas.add(liga);
            }
        }

        return ligas;
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

