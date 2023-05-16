package Persistance;

import java.util.List;

public interface TeamsLeagueUserDAOInt {

    void insertarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga);
    void actualizarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga,
                                     String nuevoDniJugador, String nuevoNombreEquipo, String nuevoNombreLiga);
    void eliminarJugadorEquipoLiga(String dniJugador, String nombreEquipo, String nombreLiga);
    List<String> obtenerEquiposPorJugador(String dniJugador);
    List<String> obtenerJugadoresPorEquipo(String nombreEquipo, String nombreLiga);
}
