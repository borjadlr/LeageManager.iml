package Persistance.dao;

import Persistance.TeamsDAOInt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Sql connector LEAGUE.
 */
public class TeamsDAO implements TeamsDAOInt {

    private static String dbURL = "jdbc:mariadb://localhost:3306/leage_manager";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;


    /**
     * Metodo que añade informacion dentro de la tabla teams
     * @param name
     * @param nplayers
     * @param wins
     * @param ties
     * @param losses
     * @param points
     */
    public void InsertDataTeams(String name, int nplayers, int wins, int ties, int losses, int points) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para inertar datos en la tabla league
            String sql = "INSERT INTO teams (TEAM_NAME,TEAM_NPLAYERS,TEAM_WINS,TEAM_TIES,TEAM_LOSSES,TEAM_POINTS) VALUES (?, ? ,? ,? ,? ,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, nplayers);
            statement.setInt(3, wins);
            statement.setInt(4,ties);
            statement.setInt(5,losses);
            statement.setInt(6,points);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para actualizar la informacion de un esquipo
     * @param name1 nombre que al que se quiere actualizar
     * @param nplayers parametro sobre el nombre de jugadores que se quiere actualizar
     * @param wins parametro sobre el nombre de victorias que se quiere actualizar
     * @param ties parametro sobre el nombre de empates que se quiere actualizar
     * @param losses parametro sobre el nombre de derrotas que se quiere actualizar
     * @param points parametro sobre el nombre de puntos que se quiere actualizar
     * @param name2 nombre en el que se quiere cambiar los parametros o parametro anterior
     */
    public void UpdateDataTeams(String name1,int nplayers, int wins, int ties, int losses, int points, String name2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para actualizar la tabla liga.
            String sql = "UPDATE teams SET TEAM_NAME = ?,TEAM_NPLAYERS = ?,TEAM_WINS = ?,TEAM_TIES = ?,TEAM_LOSSES = ?,TEAM_POINTS = ? WHERE TEAM_NAME= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setInt(2, nplayers);
            statement.setInt(3, wins);
            statement.setInt(4,ties);
            statement.setInt(5,losses);
            statement.setInt(6,points);
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

    public void DeleteDataTeams(String name){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement SQL que elimina datos de la tabla team_user dependiendo del nombre del team.
            String sqlDeleteRelationships = "DELETE FROM teams_user WHERE TU_TEAM = ?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1,name);
            stDelRelationship.executeUpdate();

            //Generacion de un statement SQL que elimina datos de la tabla league_teams dependiendo del nombre del team.
            String sqlDeleteRelationships2 = "DELETE FROM league_teams WHERE LTEAMS_NAME = ?";
            PreparedStatement stDelRelationship2 = conn.prepareStatement(sqlDeleteRelationships2);
            stDelRelationship2.setString(1,name);
            stDelRelationship2.executeUpdate();

            //Generacion de un statement sql para eliminar datos de la tabla teams dependeindeo del nombre del team.
            String sql = "DELETE FROM teams WHERE TEAM_NAME=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A team was deleted successfully!");
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


