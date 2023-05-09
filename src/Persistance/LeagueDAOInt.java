package Persistance;

import Business.Entities.League;
import Business.Entities.Team;

import java.sql.SQLException;
import java.util.List;

public interface LeagueDAOInt {

    void InsertDataLeague(String name, String date, String hour, int day, int teams);

    void UpdateDataLeague(String name1, String date, String hour, int day, int teams, boolean state, String name2);

    void DeleteDataLeague(String name);

    League selectLeague(String name) throws SQLException;

    List<League> getAllLeagues() throws SQLException;
}