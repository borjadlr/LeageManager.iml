package Persistance;

import Business.Entities.League;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * This method interface caonfigurates de LeagueDAO
 */
public interface LeagueDAOInt {
    void insertDataLeague(String name, java.sql.Date date, Time hour, int day, int teams, boolean state);
    void UpdateDataLeague(String name1, java.sql.Date date, Time hour, int day, int teams, boolean state, String name2);
    void DeleteDataLeague(String name);
    List<League> getAllLeagues() throws SQLException;
}








