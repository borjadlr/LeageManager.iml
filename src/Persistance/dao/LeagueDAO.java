package Persistance.dao;

import Persistance.LeagueDAOInt;

import java.sql.*;

/**
 * The type Sql connector LEAGUE.
 */
public class LeagueDAO implements LeagueDAOInt {

    private static String dbURL = "jdbc:mariadb://localhost:3306/leage_manager";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;


    /**
     *
     * @param name nombre de la liga
     * @param date fecha de inicio de la liga
     * @param hour hora de inicio de la liga
     * @param day jornada de la liga
     * @param teams numero de equipos que hay en la liga
     * @param state estat en el que se encuentra la liga (activa o inactiva)
     */
    public void InsertDataLeague(String name, String date, String hour, int day, int teams, boolean state) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para inertar datos en la tabla league
            String sql = "INSERT INTO league (LEAGE_NAME,LEAGUE_DATE,LEAGUE_HOUR,LEAGUE_DAY,LEAGUE_NTEAMS,LEAGUE_STATE) VALUES (?, ? ,? ,? ,? ,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, hour);
            statement.setInt(4,day);
            statement.setInt(5,teams);
            statement.setBoolean(6,state);

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
    public void UpdateDataLeague(String name1,String date, String hour, int day, int teams, boolean state, String name2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para actualizar la tabla liga.
            String sql = "UPDATE league SET LEAGE_NAME = ?,LEAGUE_DATE = ?,LEAGUE_HOUR = ?,LEAGUE_DAY = ?,LEAGUE_NTEAMS = ?,LEAGUE_STATE = ? WHERE TEAM_NAME= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setString(2, date);
            statement.setString(3, hour);
            statement.setInt(4,day);
            statement.setInt(5,teams);
            statement.setBoolean(6,state);
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
            String sqlDeleteRelationships = "DELETE FROM league_teams WHERE LEAGUET_NAME =?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1,name);
            stDelRelationship.executeUpdate();
            //Generacion de un statement sql para eliminar datos de la tabla league dependeindeo del nombre de la liga.
            String sql = "DELETE FROM league WHERE LEAGUE_NAME=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A playlist was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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

