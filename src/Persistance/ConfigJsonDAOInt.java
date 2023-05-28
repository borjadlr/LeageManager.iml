package Persistance;

import Business.Entities.Config;
import Persistance.dao.ConfigJsonDAO;

import java.io.IOException;

/**
 * This method interface caonfigurates de JSONDAO
 */
public interface ConfigJsonDAOInt {

     Config leerConfiguracionJson(String nombreArchivo) throws IOException;

}

