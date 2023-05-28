package Business.Entities;

import java.util.Random;

public class Match implements Runnable {

    private String local;
    private String visitante;
    private final String nombreLiga;
    private int golesLocal;
    private int golesVisitante;
    private int jornada;
    private boolean status;

    public Match(String local, String visitante, int golesLocal, int golesVisitante, int jornada, boolean status, String nombreLiga) {
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.jornada = jornada;
        this.status = status;
        this.nombreLiga = nombreLiga;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
    }

    @Override
    public void run() {
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
                System.out.println("Minuto " + minuto + " de la simulación del partido: " + getLocal() + " vs " + getVisitante());
                auxiliar = simularGoles(random);
                if (auxiliar == 1) {
                    golesLocal = getGolesLocal();
                    golesLocal++;
                    setGolesLocal(golesLocal);
                    System.out.println("Minuto " + minuto + ": Gol del equipo local (" + getLocal() + "). Marcador: " + golesLocal + "-" + golesVisitante);
                } else {
                    if (auxiliar == 2) {
                        golesVisitante = getGolesVisitante();
                        golesVisitante++;
                        setGolesVisitante(golesVisitante);
                        System.out.println("Minuto " + minuto + ": Gol del equipo visitante (" + getVisitante() + "). Marcador: " + golesLocal + "-" + golesVisitante);
                    }
                }
            }
        }
    }
    private int simularGoles(Random random) {
        int auxiliar = 0;
        if (random.nextInt(100) < 5) {
            if (random.nextBoolean()) {
                auxiliar = 1;

            } else {
                auxiliar = 2;
            }
        }
        return auxiliar;
    }
}
