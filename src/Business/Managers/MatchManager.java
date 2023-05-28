package Business.Managers;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import Business.Entities.Match;
import Business.Entities.Team;
import Persistance.MatchDAOInt;
import Persistance.dao.MatchDAO;

public class MatchManager {

    private MatchDAOInt matchDAO;



    private final List<Match> partidosSimulados;


    public MatchManager(MatchDAOInt matchDAO) {
        partidosSimulados = new ArrayList<>();
        this.matchDAO = matchDAO;
    }

    public void simularPartidos(List<Match> matches) {
        for (Match match : matches) {
            Thread thread = new Thread(() -> {
                System.out.println("Comenzando simulación para el partido: " + match.getLocal() + " vs " + match.getVisitante());
                simularPartido(match);
                System.out.println("Finalizando simulación para el partido: " + match.getLocal() + " vs " + match.getVisitante());
            });
            thread.start();
        }
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

}
