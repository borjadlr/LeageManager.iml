package Business.Entities;

/**
 *Esta clase representa los datos de la configuracion de la base de datos
 */
public class Config {

    private int dataBasePort;
    private String dataBaseIP;
    private String dataBaseName;
    private String userDataBase;
    private String passwordDataBase;
    private String password;
    private int gameTimeLeft;

    public String getDataBaseIP() {
        return dataBaseIP;
    }

    public int getDataBasePort() {
        return dataBasePort;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public String getUserDataBase() {
        return userDataBase;
    }

    public String getPasswordDataBase() {
        return passwordDataBase;
    }

    public int getGameTimeLeft() {
        return gameTimeLeft;
    }

    public String getUserName() {
        return userDataBase;
    }

    public String getPassword() {
        return password;
    }
}
