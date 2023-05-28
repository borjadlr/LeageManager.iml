package Persistance.dao;
import Business.Entities.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigJsonDAO {

    /**
     * Lee el archivo JSON y devuelve los valores de configuración como cadenas de texto.
     *
     * @param nombreArchivo el nombre del archivo JSON a leer.
     * @return un arreglo de cadenas de texto con los valores de configuración.
     * @throws IOException si ocurre un error durante la lectura del archivo.
     */
    public Config leerConfiguracionJson(String nombreArchivo) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject configuracion = null;
        try (FileReader fileReader = new FileReader(nombreArchivo)) {
            configuracion = (JsonObject) parser.parse(fileReader);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        int portConexionBD = configuracion.get("portConexionBD").getAsInt();
        String ipServidorBD = configuracion.get("ipServidorBD").getAsString();
        String nombreBD = configuracion.get("nombreBD").getAsString();
        String usuarioBD = configuracion.get("usuarioBD").getAsString();
        String contrasenaBD = configuracion.get("contrasenaBD").getAsString();
        String contrasenaAdministrador = configuracion.get("contrasenaAdministrador").getAsString();
        int duracionPartidoMinutos = configuracion.get("duracionPartidoMinutos").getAsInt();

        // Imprimir el contenido del archivo JSON
        System.out.println("Contenido del archivo JSON:");
        System.out.println(configuracion.toString());
        System.out.println();

        return new Config(portConexionBD, ipServidorBD, nombreBD, usuarioBD, contrasenaBD, contrasenaAdministrador, duracionPartidoMinutos);
    }


}
