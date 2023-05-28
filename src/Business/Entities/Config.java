package Business.Entities;

/**
 *Esta clase representa los datos de la configuracion de la base de datos
 */
public class Config {
    private int portConexionBD;
    private String ipServidorBD;
    private String nombreBD;
    private String usuarioBD;
    private String contrasenaBD;
    private String contrasenaAdministrador;
    private int duracionPartidoMinutos;

    public Config() {
        // Constructor vacío necesario para la deserialización JSON
    }

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

    public int getPortConexionBD() {
        return portConexionBD;
    }

    public void setPortConexionBD(int portConexionBD) {
        this.portConexionBD = portConexionBD;
    }

    public String getIpServidorBD() {
        return ipServidorBD;
    }

    public void setIpServidorBD(String ipServidorBD) {
        this.ipServidorBD = ipServidorBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getContrasenaBD() {
        return contrasenaBD;
    }

    public void setContrasenaBD(String contrasenaBD) {
        this.contrasenaBD = contrasenaBD;
    }

    public String getContrasenaAdministrador() {
        return contrasenaAdministrador;
    }

    public void setContrasenaAdministrador(String contrasenaAdministrador) {
        this.contrasenaAdministrador = contrasenaAdministrador;
    }

    public int getDuracionPartidoMinutos() {
        return duracionPartidoMinutos;
    }

    public void setDuracionPartidoMinutos(int duracionPartidoMinutos) {
        this.duracionPartidoMinutos = duracionPartidoMinutos;
    }
}

