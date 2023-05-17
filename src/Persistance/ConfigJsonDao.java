package Persistance;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Business.Entities.Config;



/*public class ConfigJsonDao<Gson> {
    private static final String route = "files/config.json";
    private static final Path path = Path.of(route);

    /**
     * Constructor de la clase
     */
    /*public ConfigJsonDao() {
    }

    /**
     * Metodo que obtiene la configuracion de la base de datos
     */
    /*public Config readConfig(){
        Config config = new Config();
        try{
            Gson gson = new Gson();

            String text= Files.readString(path);
            config=gson.fromJson(text, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }


}*/
