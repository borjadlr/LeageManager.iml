package Persistance;


import Business.Entities.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing and manipulating teams data in a database
 */
public interface TeamsDAOInt {
    void jsonToDatabase(String jsonString);

    void deleteDataTeams(String name);

    Team selectTeam(String name) throws SQLException;

    List<Team> getAllTeams();


}
