package Persistance;



import Business.Entities.League;
import Business.Entities.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing and manipulating teams data in a database
 */
public interface TeamsDAOInt {

    void insertDataTeams(String name, int nplayers, int wins, int ties, int losses, int points) throws SQLException;


    void updateDataTeams(String name1, int nplayers, int wins, int ties, int losses, int points, String name2) throws SQLException;

    void jsonToDatabase(String jsonString);

    void deleteDataTeams(String name);

    Team selectTeam(String name) throws SQLException;

    List<Team> getAllTeams() throws SQLException;

}
