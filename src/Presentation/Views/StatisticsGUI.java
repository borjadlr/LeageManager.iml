package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Statistics GUI class
 */
public class StatisticsGUI extends JPanel {
    private static final int MARGEN_IZQUIERDO = 50;
    private static final int MARGEN_DERECHO = 50;
    private static final int MARGEN_SUPERIOR = 50;
    private static final int MARGEN_INFERIOR = 80;
    private static final int GROSOR_LINEA = 3;

    private String[] equipos = {"Equipo1 ", "Equipo 2", "Equipo 3", "Equipo 4", "Equipo 5", "Equipo 6"}; // Ejemplo: Nombres de los equipos
    private int numJornadas = 10; // Ejemplo: Número de jornadas
    private int[][] puntosEquipos; // Puntos de los equipos por jornada

    public StatisticsGUI() {
        puntosEquipos = new int[equipos.length][numJornadas];

        // Jornada inicial donde todos los equipos comienzan con 0 puntos
        for (int i = 0; i < equipos.length; i++) {
            puntosEquipos[i][0] = 0;
        }

        // Generar puntos aleatorios para las jornadas restantes
        Random random = new Random();

        for (int j = 1; j < numJornadas; j++) {
            for (int i = 0; i < equipos.length; i++) {
                int puntos = (random.nextInt(2) == 0) ? 0 : 3;
                puntosEquipos[i][j] = puntosEquipos[i][j - 1] + puntos;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Establecer colores
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        // Calcular dimensiones del gráfico
        int numEquipos = equipos.length;
        int xIncrement = (getWidth() - MARGEN_IZQUIERDO - MARGEN_DERECHO) / (numJornadas - 1);
        int maxValue = numJornadas * 3; // Ejemplo: Valor máximo es el número de jornadas multiplicado por 3
        int graphHeight = getHeight() - MARGEN_SUPERIOR - MARGEN_INFERIOR;

        // Dibujar ejes
        g.drawLine(MARGEN_IZQUIERDO, getHeight() - MARGEN_INFERIOR, MARGEN_IZQUIERDO, MARGEN_SUPERIOR);
        g.drawLine(MARGEN_IZQUIERDO, getHeight() - MARGEN_INFERIOR, getWidth() - MARGEN_DERECHO, getHeight() - MARGEN_INFERIOR);

        // Dibujar líneas de recorrido de los equipos
        for (int i = 0; i < numEquipos; i++) {
            int xPrev = MARGEN_IZQUIERDO;
            int yPrev = getHeight() - MARGEN_INFERIOR - (int) ((double) puntosEquipos[i][0] / maxValue * graphHeight);

            g.setColor(getColor(i));
            g.setFont(new Font("Calibri", Font.PLAIN, 12));
            ((Graphics2D) g).setStroke(new BasicStroke(GROSOR_LINEA));

            for (int j = 1; j < numJornadas; j++) {
                int x = MARGEN_IZQUIERDO + j * xIncrement;
                int y = getHeight() - MARGEN_INFERIOR - (int) ((double) puntosEquipos[i][j] / maxValue * graphHeight);

                g.drawLine(xPrev, yPrev, x, y);

                xPrev = x;
                yPrev = y;
            }

            // Rellenar puntos de la jornada con números
            for (int j = 0; j < numJornadas; j++) {
                int x = MARGEN_IZQUIERDO + j * xIncrement - 2;
                int y = getHeight() - MARGEN_INFERIOR - (int) ((double) puntosEquipos[i][j] / maxValue * graphHeight) - 2;
                g.setColor(getColor(i));
                g.fillOval(x, y, 4, 4);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(puntosEquipos[i][j]), x - 4, y - 6);
            }
        }

        // Etiquetas de ejes
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.PLAIN, 12));

        // Etiquetas del eje x (jornadas)
        for (int i = 0; i < numJornadas; i++) {
            int x = MARGEN_IZQUIERDO + i * xIncrement - 10;
            int y = getHeight() - MARGEN_INFERIOR + 20;
            g.drawString("J" + i, x, y);
        }

        // Etiquetas del eje y (puntos)
        for (int i = 0; i <= maxValue; i += 2) {
            int x = MARGEN_IZQUIERDO - 30;
            int y = getHeight() - MARGEN_INFERIOR - (int) ((double) i / maxValue * graphHeight) + 5;
            g.drawString(Integer.toString(i), x, y);
        }

        // Leyenda de equipos
        g.setFont(new Font("Calibri", Font.BOLD, 14));
        int leyendaWidth = (getWidth() - MARGEN_IZQUIERDO - MARGEN_DERECHO) / numEquipos;
        int leyendaHeight = 30;

        for (int i = 0; i < numEquipos; i++) {
            int x = MARGEN_IZQUIERDO + i * leyendaWidth; // Corrección aquí: usa leyendaWidth en lugar de xIncrement

            int y = getHeight() - MARGEN_INFERIOR + 50;

            // Dibujar línea de equipo
            g.setColor(getColor(i));
            ((Graphics2D) g).setStroke(new BasicStroke(GROSOR_LINEA));
            g.drawLine(x + leyendaWidth / 2, y, x + leyendaWidth / 2, y + leyendaHeight / 2);

            // Escribir nombre de equipo
            g.setColor(getColor(i));
            g.setFont(new Font("Calibri", Font.BOLD, 12));

            // Obtener el ancho del nombre del equipo
            int nombreWidth = g.getFontMetrics().stringWidth(equipos[i]);
            int nombreX = x + leyendaWidth / 2 + 5; // Corrección aquí: calcula nombreX después de dibujar la línea

            g.drawString(equipos[i], nombreX + 15, getHeight() - 20);

        }
    }

    /**
     * This method returns a color depending the index
     * @param index number of which color you want
     * @return the color
     */
    private Color getColor(int index) {
        Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.MAGENTA};
        return colors[index % colors.length];
    }
}