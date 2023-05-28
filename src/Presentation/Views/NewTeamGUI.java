package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewTeamGUI extends JPanel {
    private final JButton searchButton;
    private static final String SEARCH_BUTTON = "SEARCH_BUTTON";

    public NewTeamGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Crear el campo de texto para mostrar la ruta del archivo seleccionado
        JTextField filePathField = new JTextField();
        filePathField.setEditable(false);
        add(filePathField, BorderLayout.NORTH);

        // Crear el panel central con el botón "Buscar archivo" y el texto "Create new team"
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        searchButton = new JButton("Buscar archivo");
        searchButton.setActionCommand(SEARCH_BUTTON);

        JLabel createTeamLabel = new JLabel("Create new team");
        createTeamLabel.setFont(new Font("Arial", Font.BOLD, 60)); // Ajusta la fuente y el tamaño del texto

        createTeamLabel.add(Box.createVerticalStrut(90));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Agrega un espacio en la parte inferior del componente
        centerPanel.add(createTeamLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(searchButton, gbc);
    }

    public void actionListener(ActionListener actionListener){
        searchButton.addActionListener(actionListener);
    }
}
