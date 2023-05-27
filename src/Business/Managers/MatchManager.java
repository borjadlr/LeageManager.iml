package Business.Managers;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import Business.Entities.Match;
import Business.Entities.Team;
import Persistance.MatchDAOInt;

public class MatchManager {

    private MatchDAOInt matchDAO;
    private List<Match> partidosSimulados;

    public MatchManager() {
        partidosSimulados = new ArrayList<>();
    }



    public void simularPartidos(LinkedList<Match> matches) {
        for (Match match : matches) {
            Thread thread = new Thread(() -> simularPartido(match));
            thread.start();
        }
    }

    public void simularPartido(Match matches) {
        Random random = new Random();
        // Simulación de goles
        int duracionPartidoEnSegundos = 90 * 60;
        int duracionMinutoEnMilisegundos = 100;
        int golesLocal = 0;
        int golesVisitante = 0;

        for (int segundo = 1; segundo <= duracionPartidoEnSegundos; segundo++) {
            int auxiliar = 0;
            int minuto = segundo / 60;
            int milisegundos = (segundo % 60) * duracionMinutoEnMilisegundos;

            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (segundo % 60 == 0) {
                auxiliar = simularGoles(random, matches);
                if (auxiliar == 1) {
                    golesLocal = matches.getGolesLocal();
                    golesLocal++;
                    matches.setGolesLocal(golesLocal);
                } else {
                    if (auxiliar == 2) {
                        golesVisitante = matches.getGolesVisitante();
                        golesVisitante++;
                        matches.setGolesVisitante(golesVisitante);
                    }
                }
                partidosSimulados.add(matches);
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

    public List<Match> getPartidosSimulados() {
        return partidosSimulados;
    }


}
