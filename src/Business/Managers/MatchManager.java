package Business.Managers;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import Business.Entities.League;
import Business.Entities.Match;
import Persistance.LeagueDAOInt;
import Persistance.MatchDAOInt;

public class MatchManager {
    private final MatchDAOInt matchDAO;
    private final LeagueDAOInt leagueDAO;
    private final List<Match> partidosSimulados;

    public MatchManager(MatchDAOInt matchDAO, LeagueDAOInt leagueDAO) {
        this.leagueDAO = leagueDAO;
        partidosSimulados = new ArrayList<>();
        this.matchDAO = matchDAO;
    }

    public void simularPartidos(List<Match> matches) {
        int i = 0;
        for (Match match : matches) {
            Match partido = new Match(matches.get(i).getLocal(), matches.get(i).getVisitante(), matches.get(i).getGolesLocal(), matches.get(i).getGolesVisitante(), matches.get(i).getJornada(), matches.get(i).isStatus(), matches.get(i).getNombreLiga());
            Thread thread = new Thread(partido);
            thread.start();
            i++;
        }
    }

    public List<Match> getMatchesByJornada(int jornada){
        return  matchDAO.getMatchesByJornada(jornada);
    }

    public void simularPartido(Match match) {
        Random random = new Random();
        // Simulación de goles
        int duracionPartidoEnSegundos = 90 * 60;
        int duracionMinutoEnMilisegundos = 100 / 101; // Haciendo que la simulación sea 5 veces más rápida
        int golesLocal = 0;
        int golesVisitante = 0;

        for (int segundo = 1; segundo <= duracionPartidoEnSegundos; segundo++) {
            int auxiliar = 0;
            int minuto = segundo / 60;
            int milisegundos = ((segundo % 60) * duracionMinutoEnMilisegundos);

            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (segundo % 60 == 0) {
                System.out.println("Minuto " + minuto + " de la simulación del partido: " + match.getLocal() + " vs " + match.getVisitante());
                auxiliar = simularGoles(random, match);
                if (auxiliar == 1) {
                    golesLocal = match.getGolesLocal();
                    golesLocal++;
                    match.setGolesLocal(golesLocal);
                    System.out.println("Minuto " + minuto + ": Gol del equipo local (" + match.getLocal() + "). Marcador: " + golesLocal + "-" + golesVisitante);
                } else {
                    if (auxiliar == 2) {
                        golesVisitante = match.getGolesVisitante();
                        golesVisitante++;
                        match.setGolesVisitante(golesVisitante);
                        System.out.println("Minuto " + minuto + ": Gol del equipo visitante (" + match.getVisitante() + "). Marcador: " + golesLocal + "-" + golesVisitante);
                    }
                }
                partidosSimulados.add(match);
            }
        }
    }

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


    public void simularPartidosConAumentoJornada(String liga, int jornadaInicial) {
        int jornada = jornadaInicial;
        while (true) {
            System.out.println("Esta es la jornada"+ jornada);
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


    public List<Match> getPartidosSimulados() {
        return partidosSimulados;
    }

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

}
