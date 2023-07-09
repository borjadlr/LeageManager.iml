package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import static java.awt.Font.PLAIN;

/**
 * The NewLeagueGUI class represents a panel for creating a new league.
 */
public class NewLeagueGUI extends JPanel {

    private final JTextField leagueName;
    private final JTextField hora;
    private final JTextField data;
    private final JTextField numeroEquipos;
    private final JButton ok;
    private static final String OK_BUTTON = "OK_BUTTON";
    private static final String TitleLetterType = "Apple Casual";
    private static final String TITLE = "New League Creation";
    private static final String LetterType = "Inter";
    private static final String BackGroundColor = "#D9D9D9";
    private static final String DLEAGUE_NAME = "League name: ";
    private static final String DHOUR = "Hour: ";
    private static final String DDATE = "Date: ";
    private static final String DTEAM_NUMBER = "Team Number: ";
    private static final String NEW_LEAGUE = "New League";
    private static final String HOUR = "Hour";
    private static final String DATE = "Date";
    private static final String TEAM_NUMBER = "Team Number";
    private static final String OK = "    OK    ";
    private static final String BLANK = "";

    /**
     * Constructs a NewLeagueGUI object.
     */
    public NewLeagueGUI() {

        Color backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        JLabel title = new JLabel(TITLE);
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setFont(new Font(TitleLetterType, PLAIN, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        leagueName = new JTextField(DLEAGUE_NAME);
        leagueName.setFont(new Font(LetterType, PLAIN, 20));
        leagueName.setBackground(Color.decode(BackGroundColor));
        leagueName.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(leagueName);

        panel.add(Box.createVerticalStrut(15));

        hora = new JTextField(DHOUR);
        hora.setFont(new Font(LetterType, PLAIN, 20));
        hora.setBackground(Color.decode(BackGroundColor));
        hora.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(hora);

        panel.add(Box.createVerticalStrut(15));

        data = new JTextField(DDATE);
        data.setFont(new Font(LetterType, PLAIN, 20));
        data.setBackground(Color.decode(BackGroundColor));
        data.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(data);

        panel.add(Box.createVerticalStrut(15));

        numeroEquipos = new JTextField(DTEAM_NUMBER);
        numeroEquipos.setFont(new Font(LetterType, PLAIN, 20));
        numeroEquipos.setBackground(Color.decode(BackGroundColor));
        numeroEquipos.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(numeroEquipos);

        panel.add(Box.createVerticalStrut(15));

        ok = new JButton(OK);
        ok.setActionCommand(OK_BUTTON);
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ok.setBackground(Color.decode(BackGroundColor));
        panel.add(ok);

        this.add(panel);
    }

    /**
     * Returns the league name entered by the user.
     * @return the league name
     */
    public String getLeagueName() {
        return leagueName.getText();
    }

    /**
     * Returns the hour entered by the user.
     * @return the hour
     */
    public String getHora() {
        return hora.getText();
    }

    /**
     * Returns the date entered by the user.
     * @return the date
     */
    public String getData() {
        return data.getText();
    }

    /**
     * Returns the number of teams entered by the user.
     * @return the number of teams
     */
    public String getNumeroEquipos() {
        return numeroEquipos.getText();
    }

    /**
     * Registers an ActionListener to the OK button.
     * @param actionListener the ActionListener to register
     */
    public void registerListener(ActionListener actionListener) {
        ok.addActionListener(actionListener);
    }

    /**
     * Displays an exception message dialog with the given message.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    /**
     * Registers a FocusListener to the input fields for handling focus events.
     * @param focusListener the FocusListener to register
     */
    public void newLeagueFocusListener(FocusListener focusListener) {
        leagueName.addFocusListener(focusListener);
        leagueName.setName(NEW_LEAGUE);
        data.addFocusListener(focusListener);
        data.setName(DATE);
        numeroEquipos.addFocusListener(focusListener);
        numeroEquipos.setName(TEAM_NUMBER);
        hora.addFocusListener(focusListener);
        hora.setName(HOUR);
    }

    /**
     * Clears the text fields in the panel.
     */
    public void clearTextFields() {
        leagueName.setText(BLANK);
        hora.setText(BLANK);
        data.setText(BLANK);
        numeroEquipos.setText(BLANK);
    }
}