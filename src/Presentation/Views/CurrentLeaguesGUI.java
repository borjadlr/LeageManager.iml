package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CurrentLeaguesGUI extends JFrame{

        JTable tabla;
        DefaultTableModel modelo;

        public CurrentLeaguesGUI() {
            super("Tabla de Equipos");

            // Crear modelo de tabla
            String[] columnas = {"Equipo", "Puntos", "Partidos Ganados", "Partidos Perdidos", "Partidos Empatados", "Rondas"};
            modelo = new DefaultTableModel(columnas, 0);

            // Añadir datos a la tabla
            Object[] equipo1 = {"Equipo 1", 10, 3, 1, 0, 4};
            Object[] equipo2 = {"Equipo 2", 5, 1, 3, 1, 4};
            Object[] equipo3 = {"Equipo 3", 7, 2, 2, 1, 4};
            Object[] equipo4 = {"Equipo 4", 2, 0, 4, 2, 4};
            modelo.addRow(equipo1);
            modelo.addRow(equipo2);
            modelo.addRow(equipo3);
            modelo.addRow(equipo4);

            // Crear tabla y añadir modelo
            tabla = new JTable(modelo);

            // Crear scroll pane y añadir tabla
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Añadir scroll pane al JFrame

            Container contentPane = getContentPane();
            contentPane.add(scrollPane);

            // Ajustar tamaño y hacer visible
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
            pack();
        }
}
