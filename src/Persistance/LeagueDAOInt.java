package Persistance;

public interface LeagueDAOInt {

    public void InsertDataLeague(String name, String date, String hour, int day, int teams, boolean state);

    public void DeleteDataLeague(String name);

    public void UpdateDataLeague(String name1,String date, String hour, int day, int teams, boolean state, String name2);
}
