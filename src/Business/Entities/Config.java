package Business.Entities;

/**
 * The Config class represents the database configuration data.
 */
public class Config {
    private int portConexionBD;
    private String ipServidorBD;
    private String nombreBD;
    private String usuarioBD;
    private String contrasenaBD;
    private String contrasenaAdministrador;
    private int duracionPartidoMinutos;

    /**
     * Constructs a new empty Config object.
     */
    public Config() {
    }

    /**
     * Constructs a new Config object with the specified configuration data.
     * @param portConexionBD           The database connection port.
     * @param ipServidorBD             The IP address of the database server.
     * @param nombreBD                 The name of the database.
     * @param usuarioBD                The username for the database connection.
     * @param contrasenaBD             The password for the database connection.
     * @param contrasenaAdministrador  The password for the administrator.
     * @param duracionPartidoMinutos   The duration of a match in minutes.
     */
    public Config(int portConexionBD, String ipServidorBD, String nombreBD, String usuarioBD,
                  String contrasenaBD, String contrasenaAdministrador, int duracionPartidoMinutos) {
        this.portConexionBD = portConexionBD;
        this.ipServidorBD = ipServidorBD;
        this.nombreBD = nombreBD;
        this.usuarioBD = usuarioBD;
        this.contrasenaBD = contrasenaBD;
        this.contrasenaAdministrador = contrasenaAdministrador;
        this.duracionPartidoMinutos = duracionPartidoMinutos;
    }

    /**
     * Returns the database connection port.
     * @return The database connection port.
     */
    public int getPortConexionBD() {
        return portConexionBD;
    }

    /**
     * Sets the database connection port.
     * @param portConexionBD The database connection port.
     */
    public void setPortConexionBD(int portConexionBD) {
        this.portConexionBD = portConexionBD;
    }

    /**
     * Returns the IP address of the database server.
     * @return The IP address of the database server.
     */
    public String getIpServidorBD() {
        return ipServidorBD;
    }

    /**
     * Sets the IP address of the database server.
     * @param ipServidorBD The IP address of the database server.
     */
    public void setIpServidorBD(String ipServidorBD) {
        this.ipServidorBD = ipServidorBD;
    }

    /**
     * Returns the name of the database.
     * @return The name of the database.
     */
    public String getNombreBD() {
        return nombreBD;
    }

    /**
     * Sets the name of the database.
     * @param nombreBD The name of the database.
     */
    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    /**
     * Returns the username for the database connection.
     * @return The username for the database connection.
     */
    public String getUsuarioBD() {
        return usuarioBD;
    }

    /**
     * Sets the username for the database connection.
     * @param usuarioBD The username for the database connection.
     */
    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    /**
     * Returns the password for the database connection.
     * @return The password for the database connection.
     */
    public String getContrasenaBD() {
        return contrasenaBD;
    }

    /**
     * Sets the password for the database connection.
     * @param contrasenaBD The password for the database connection.
     */
    public void setContrasenaBD(String contrasenaBD) {
        this.contrasenaBD = contrasenaBD;
    }

    /**
     * Returns the password for the administrator.
     * @return The password for the administrator.
     */
    public String getContrasenaAdministrador() {
        return contrasenaAdministrador;
    }

    /**
     * Sets the password for the administrator.
     * @param contrasenaAdministrador The password for the administrator.
     */
    public void setContrasenaAdministrador(String contrasenaAdministrador) {
        this.contrasenaAdministrador = contrasenaAdministrador;
    }

    /**
     * Returns the duration of a match in minutes.
     * @return The duration of a match in minutes.
     */
    public int getDuracionPartidoMinutos() {
        return duracionPartidoMinutos;
    }

    /**
     * Sets the duration of a match in minutes.
     * @param duracionPartidoMinutos The duration of a match in minutes.
     */
    public void setDuracionPartidoMinutos(int duracionPartidoMinutos) {
        this.duracionPartidoMinutos = duracionPartidoMinutos;
    }
}
