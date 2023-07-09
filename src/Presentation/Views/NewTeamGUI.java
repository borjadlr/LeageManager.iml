package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The NewTeamGUI class represents a panel for creating a new team.
 */
public class NewTeamGUI extends JPanel {
    private final JButton searchButton;
    private static final String SEARCH_BUTTON = "SEARCH_BUTTON";
    private static final String SEARCHBUTTON = "SEARCH BUTTON";
    private static final String LetterType = "Inter";
    private static final String NEW_TEAM = "Create new team";
    private static final String TEAM_ADDED = "New teams have been added correctly";


    /**
     * Constructs a NewTeamGUI object.
     */
    public NewTeamGUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JTextField filePathField = new JTextField();
        filePathField.setEditable(false);
        add(filePathField, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        searchButton = new JButton(SEARCHBUTTON);
        searchButton.setActionCommand(SEARCH_BUTTON);

        JLabel createTeamLabel = new JLabel(NEW_TEAM);
        createTeamLabel.setFont(new Font(LetterType, Font.BOLD, 60));

        createTeamLabel.add(Box.createVerticalStrut(90));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        centerPanel.add(createTeamLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(searchButton, gbc);
    }

    /**
     * Registers an ActionListener to the search button.
     * @param actionListener the ActionListener to register
     */
    public void actionListener(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }

    /**
     * Displays message dialog with a message of success.
     */
    public void teamsAddedSuccessfullyMessage(){
        JOptionPane.showMessageDialog(null, TEAM_ADDED);
    }

    /**
     * Displays an exception message dialog with the specified message.
     * @param message The exception message to display.
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}