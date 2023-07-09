package Business.Managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Business.Entities.League;
import Business.Entities.Match;
import Persistance.LeagueDAOInt;
import Persistance.MatchDAOInt;

/**
 * El administrador de partidos se encarga de simular partidos de fútbol y gestionar los resultados.
 */
public class MatchManager {
    private final MatchDAOInt matchDAO;
    private final LeagueDAOInt leagueDAO;
    private final List<Match> partidosSimulados;

    /**
     * Constructor de la clase MatchManager.
     *
     * @param matchDAO   DAO de partidos para acceder a los datos de los partidos.
     * @param leagueDAO  DAO de ligas para acceder a los datos de las ligas.
     */
    public MatchManager(MatchDAOInt matchDAO, LeagueDAOInt leagueDAO) {
        this.leagueDAO = leagueDAO;
        partidosSimulados = new ArrayList<>();
        this.matchDAO = matchDAO;
    }

    /**
     * Simula una lista de partidos de fútbol en hilos separados.
     *
     * @param matches  Lista de partidos a simular.
     */
    public void simularPartidos(List<Match> matches) {
        int i = 0;
        for (Match ignored : matches) {
            Match partido = new Match(matches.get(i).getLocal(), matches.get(i).getVisitante(),
                    matches.get(i).getGolesLocal(), matches.get(i).getGolesVisitante(), matches.get(i).getJornada(),
                    matches.get(i).isStatus(), matches.get(i).getNombreLiga());
            Thread thread = new Thread(partido);
            thread.start();
            i++;
        }
    }

    /**
     * Obtiene una lista de partidos por jornada.
     *
     * @param jornada  Número de jornada.
     * @return         Lista de partidos correspondientes a la jornada.
     */
    public List<Match> getMatchesByJornada(int jornada) {
        return matchDAO.getMatchesByJornada(jornada);
    }


    /**
     * Simula la secuencia de goles en un partido.
     *
     * @param random  Objeto Random para generar números aleatorios.
     * @param match   Partido en el que se simulan los goles.
     * @return        Número que indica el tipo de gol simulado (1: gol local, 2: gol visitante, 0: sin gol).
     */
    private int simularGoles(Random random, Match match) {
        int auxiliar = 0;
        if (random.nextInt(100) < 5) {
            if (random.nextBoolean()) {
                auxiliar = 1;
                matchDAO.sumaGol(match.getLocal(), match.getJornada());
            } else {
                auxiliar = 2;
                matchDAO.sumaGol(match.getVisitante(), match.getJornada());
            }
        }
        return auxiliar;
    }

    /**
     * Simula una serie de partidos con aumento de jornada.
     *
     * @param liga           Nombre de la liga en la que se simulan los partidos.
     * @param jornadaInicial Número de jornada inicial.
     */
    public void simularPartidosConAumentoJornada(String liga, int jornadaInicial) {
        int jornada = jornadaInicial;
        while (true) {
            List<Match> partidos = matchDAO.obtenerPartidosPorLigaYJornada(liga, jornada);
            if (partidos.isEmpty()) {
                break; // Salir del bucle si no hay más partidos para la jornada actual
            }

            simularPartidos(partidos);

            try {
                Thread.sleep(10000); // Esperar 10 segundos (10,000 milisegundos) antes de pasar a la siguiente jornada
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            jornada++; // Incrementar el número de jornada para la siguiente iteración
        }
    }

    /**
     * Obtiene todos los partidos de una jornada en todas las ligas.
     *
     * @param jornada  Número de jornada.
     * @return         Lista de partidos de la jornada en todas las ligas.
     * @throws SQLException si ocurre un error de SQL al acceder a los datos.
     */
    public List<Match> getAllMatchesByJornada(int jornada) throws SQLException {
        int i = 0;
        List<Match> matches = new ArrayList<>();
        List<League> leagues = leagueDAO.getAllLeagues();

        while (leagues.size() > i) {
            matches.addAll(matchDAO.obtenerPartidosPorLigaYJornada(leagues.get(i).getName(), jornada));
            i++;
        }

        return matches;
    }

    /**
     * Obtiene la lista de partidos simulados.
     *
     * @return Lista de partidos simulados.
     */
    public List<Match> getPartidosSimulados() {
        return partidosSimulados;
    }
}
