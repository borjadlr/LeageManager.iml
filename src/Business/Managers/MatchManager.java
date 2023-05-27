package Business.Managers;
import java.util.LinkedList;
import java.util.Random;
import Business.Entities.Match;
import Persistance.MatchDAOInt;

public class MatchManager {

    private MatchDAOInt matchDAO;

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

        for (int segundo = 1; segundo <= duracionPartidoEnSegundos; segundo++) {
            int minuto = segundo / 60;
            int milisegundos = (segundo % 60) * duracionMinutoEnMilisegundos;

            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (segundo % 60 == 0) {
                simularGoles(random, matches);
            }
        }
    }

    private void simularGoles(Random random, Match match) {
        if (random.nextInt(100) < 5) {
            if (random.nextBoolean()) {
                matchDAO.sumaGol(match.getLocal(), match.getJornada());
            } else {
                matchDAO.sumaGol(match.getVisitante(), match.getJornada());
            }
        }
    }

}
