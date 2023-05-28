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
    private final Color backgroundColor;

    private static final String OK_BUTTON = "OK_BUTTON";

    /**
     * Constructs a NewLeagueGUI object.
     */
    public NewLeagueGUI() {

        this.backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        // Title
        JLabel title = new JLabel("New League Creation");
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setFont(new Font("Apple Casual", PLAIN, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        String defaultPhoneText = "League name: ";
        leagueName = new JTextField(defaultPhoneText);
        leagueName.setFont(new Font("Inter", PLAIN, 20));
        leagueName.setBackground(Color.decode("#D9D9D9"));
        leagueName.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(leagueName);

        panel.add(Box.createVerticalStrut(15));

        String defaultEmailText = "Hour: ";
        hora = new JTextField(defaultEmailText);
        hora.setFont(new Font("Inter", PLAIN, 20));
        hora.setBackground(Color.decode("#D9D9D9"));
        hora.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(hora);

        panel.add(Box.createVerticalStrut(15));

        String defaultDniText = "Date: ";
        data = new JTextField(defaultDniText);
        data.setFont(new Font("Inter", PLAIN, 20));
        data.setBackground(Color.decode("#D9D9D9"));
        data.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(data);

        panel.add(Box.createVerticalStrut(15));

        String defaultDorsalText = "Número equips: ";
        numeroEquipos = new JTextField(defaultDorsalText);
        numeroEquipos.setFont(new Font("Inter", PLAIN, 20));
        numeroEquipos.setBackground(Color.decode("#D9D9D9"));
        numeroEquipos.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(numeroEquipos);

        panel.add(Box.createVerticalStrut(15));

        ok = new JButton("    OK    ");
        ok.setActionCommand(OK_BUTTON);
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ok.setBackground(Color.decode("#D9D9D9"));
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
     * Displays a parse message dialog.
     */
    public void parseMessage() {
        JOptionPane.showMessageDialog(null, "Call 666445481 if this error occurs.");
    }

    /**
     * Registers a FocusListener to the input fields for handling focus events.
     * @param focusListener the FocusListener to register
     */
    public void newLeagueFocusListener(FocusListener focusListener) {
        leagueName.addFocusListener(focusListener);
        leagueName.setName("New League");
        data.addFocusListener(focusListener);
        data.setName("Date");
        numeroEquipos.addFocusListener(focusListener);
        numeroEquipos.setName("Número equips");
        hora.addFocusListener(focusListener);
        hora.setName("Hora");
    }

    /**
     * Clears the text fields in the panel.
     */
    public void clearTextFields() {
        leagueName.setText("");
        hora.setText("");
        data.setText("");
        numeroEquipos.setText("");
    }
}