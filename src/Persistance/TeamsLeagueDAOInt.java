package Persistance;

import java.util.List;

public interface TeamsLeagueDAOInt {

    void insertarEquipoLiga(String nombreEquipo, String nombreLiga);
    void actualizarEquipoLiga(String nombreEquipo, String nombreLiga, String nuevoNombreEquipo, String nuevoNombreLiga);
    void eliminarEquipoLiga(String nombreEquipo, String nombreLiga);
    List<String> obtenerEquiposPorLiga(String nombreLiga);
}
