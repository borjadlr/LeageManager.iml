package Persistance;

import Persistance.dao.ConfigJsonDAO;

import java.io.IOException;

public interface ConfigJsonDAOInt {

    ConfigJsonDAO leerConfiguracionJson(String nombreArchivo) throws IOException;

}

