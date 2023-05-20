package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CurrentLeaguesGUI extends JPanel{

        JTable table;
        DefaultTableModel model;
        Color backgroundColor;
        String[] colums;

        public CurrentLeaguesGUI() {

            this.backgroundColor = Color.WHITE;
            this.setLayout(new GridBagLayout());
            this.setBackground(backgroundColor);
            //this.setOpaque(false);
            this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // Crear datos de la tabla
            colums = new String[]{"Equipo", "Puntos", "Ganados", "Perdidos", "Empatados", "Rondas"};

            //TODO: AQUI PASARIAMOS UNA LISTA DE TEAM Y PONDRIAMOS LOS DATOS DE CADA TEAM EN VEZ DEL OBJECT
            Object[][] datos = {
                    {"Equipo 1", 15, 5, 2, 1, 8},
                    {"Equipo 2", 12, 4, 3, 0, 7},
                    {"Equipo 3", 8, 2, 4, 2, 8},
                    {"Equipo 4", 7, 2, 5, 1, 8},
                    {"Equipo 5", 6, 1, 4, 3, 8},
            };

            DefaultTableModel model = new DefaultTableModel(datos, colums);
            JTable tabla = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Añadir scroll pane al panel
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
        }
}
