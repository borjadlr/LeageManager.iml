package Persistance;

import Business.Entities.League;
import Business.Entities.Team;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface LeagueDAOInt {

    void insertDataLeague(String name, Date date, Time hour, int day, int teams, int state);

    void UpdateDataLeague(String name1, Date date, Time hour, int day, int teams, int state, String name2);

    void DeleteDataLeague(String name);

    League selectLeague(String name) throws SQLException;

    List<League> getAllLeagues() throws SQLException;


}