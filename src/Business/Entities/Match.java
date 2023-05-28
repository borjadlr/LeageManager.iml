package Business.Entities;

import java.util.Random;

/**
 * Representa un partido de fútbol entre dos equipos.
 */
public class Match implements Runnable {

    private String local;
    private String visitante;
    private final String nombreLiga;
    private int golesLocal;
    private int golesVisitante;
    private int jornada;
    private boolean status;

    /**
     * Constructor de la clase Match.
     *
     * @param local           el nombre del equipo local
     * @param visitante       el nombre del equipo visitante
     * @param golesLocal      la cantidad de goles del equipo local
     * @param golesVisitante  la cantidad de goles del equipo visitante
     * @param jornada         el número de jornada del partido
     * @param status          el estado del partido (en progreso o finalizado)
     * @param nombreLiga      el nombre de la liga a la que pertenece el partido
     */
    public Match(String local, String visitante, int golesLocal, int golesVisitante, int jornada, boolean status, String nombreLiga) {
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.jornada = jornada;
        this.status = status;
        this.nombreLiga = nombreLiga;
    }

    // Getters y setters

    /**
     * Obtiene el nombre del equipo local.
     *
     * @return el nombre del equipo local
     */
    public String getLocal() {
        return local;
    }

    /**
     * Establece el nombre del equipo local.
     *
     * @param local el nombre del equipo local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Obtiene el nombre del equipo visitante.
     *
     * @return el nombre del equipo visitante
     */
    public String getVisitante() {
        return visitante;
    }

    /**
     * Establece el nombre del equipo visitante.
     *
     * @param visitante el nombre del equipo visitante
     */
    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    /**
     * Obtiene la cantidad de goles del equipo local.
     *
     * @return la cantidad de goles del equipo local
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * Establece la cantidad de goles del equipo local.
     *
     * @param golesLocal la cantidad de goles del equipo local
     */
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    /**
     * Obtiene la cantidad de goles del equipo visitante.
     *
     * @return la cantidad de goles del equipo visitante
     */
    public int getGolesVisitante() {
        return golesVisitante;
    }

    /**
     * Establece la cantidad de goles del equipo visitante.
     *
     * @param golesVisitante la cantidad de goles del equipo visitante
     */
    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    /**
     * Obtiene el número de jornada del partido.
     *
     * @return el número de jornada del partido
     */
    public int getJornada() {
        return jornada;
    }

    /**
     * Establece el número de jornada del partido.
     *
     * @param jornada el número de jornada del partido
     */
    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    /**
     * Verifica el estado del partido.
     *
     * @return true si el partido está en progreso, false si ha finalizado
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Establece el estado del partido.
     *
     * @param status el estado del partido (true si está en progreso, false si ha finalizado)
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Obtiene el nombre de la liga a la que pertenece el partido.
     *
     * @return el nombre de la liga
     */
    public String getNombreLiga() {
        return nombreLiga;
    }

    /**
     * Establece el nombre de la liga a la que pertenece el partido.
     *
     * @param nombreLiga el nombre de la liga
     */
    public void setNombreLiga(String nombreLiga) {
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo del partido.
     * Realiza la simulación del partido y registra los goles marcados.
     */
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
                auxiliar = simularGoles(random);
                if (auxiliar == 1) {
                    golesLocal = getGolesLocal();
                    golesLocal++;
                    setGolesLocal(golesLocal);

                } else {
                    if (auxiliar == 2) {
                        golesVisitante = getGolesVisitante();
                        golesVisitante++;
                        setGolesVisitante(golesVisitante);
                    }
                }
            }
        }
    }

    /**
     * Simula los goles marcados en un minuto del partido.
     *
     * @param random un objeto de la clase Random para generar valores aleatorios
     * @return 1 si se marca un gol del equipo local, 2 si se marca un gol del equipo visitante, o 0 si no se marca gol
     */
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
