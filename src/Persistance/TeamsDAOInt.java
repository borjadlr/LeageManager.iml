package Persistance;

import Business.Entities.User;

import java.util.LinkedList;

public interface TeamsDAOInt {
    public void InsertDataTeams(String name, int nplayers, int wins, int ties, int losses, int points);

    public void DeleteDataTeams(String name);

    public void UpdateDataTeams(String name1,int nplayers, int wins, int ties, int losses, int points, String name2);


}
