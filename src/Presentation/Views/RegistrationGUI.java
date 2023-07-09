package Presentation.Views;

import javax.swing.*;;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import static java.awt.Font.PLAIN;

/**
 * A JPanel class representing the GUI for user registration.
 */
public class RegistrationGUI extends JPanel {
    private final JTextField dni;
    private final JTextField email;
    private final JTextField phoneNumber;
    private final JTextField dorsal;
    private final JButton ok;
    private static final String OK_BUTTON = "OK_BUTTON";
    private static final String BLANK = "";
    private static final String dDniText = "Dni: ";
    private static final String dDorsalText = "Dorsal: ";
    private static final String dEmailText = "Email: ";
    private static final String defaultPhoneNumberText = "Phone number: ";
    private static final String DNI = "DNI";
    private static final String DORSAL = "Dorsal";
    private static final String EMAIL = "Email";
    private static final String PHONE_NUMBER = "Phone Number";
    private static final String LetterType = "Inter";
    private static final String BackGroundColor = "#D9D9D9";
    private static final String TITLE = "LaegueManager Registration";
    private static final String TitleLetterType = "Apple Casual";
    private static final String NUMBER_ERROR = "This is not even a number!";
    private static final String OK = "    OK    ";

    /**
     * Constructs a RegistrationGUI object.
     * Initializes the GUI components and sets up the layout.
     */

    public RegistrationGUI() {
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

        phoneNumber = new JTextField(defaultPhoneNumberText);
        phoneNumber.setFont(new Font(LetterType, PLAIN, 20));
        phoneNumber.setBackground(Color.decode(BackGroundColor));
        phoneNumber.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(phoneNumber);

        panel.add(Box.createVerticalStrut(15));

        email = new JTextField(dEmailText);
        email.setFont(new Font(LetterType, PLAIN, 20));
        email.setBackground(Color.decode(BackGroundColor));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(email);
        panel.add(Box.createVerticalStrut(15));

        dni = new JTextField(dDniText);
        dni.setFont(new Font(LetterType, PLAIN, 20));
        dni.setBackground(Color.decode(BackGroundColor));
        dni.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dni);
        panel.add(Box.createVerticalStrut(15));

        dorsal = new JTextField(dDorsalText);
        dorsal.setFont(new Font(LetterType, PLAIN, 20));
        dorsal.setBackground(Color.decode(BackGroundColor));
        dorsal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(dorsal);
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
     * Registers an ActionListener for the OK button.
     *
     * @param actionListener The ActionListener to register.
     */
    public void registerRegistration(ActionListener actionListener) {
        ok.addActionListener(actionListener);
    }

    /**
     * Registers a FocusListener for the input fields.
     *
     * @param focusListener The FocusListener to register.
     */
    public void foscusListener(FocusListener focusListener) {
        dni.addFocusListener(focusListener);
        dni.setName(DNI);
        email.addFocusListener(focusListener);
        email.setName(EMAIL);
        dorsal.addFocusListener(focusListener);
        dorsal.setName(DORSAL);
        phoneNumber.addFocusListener(focusListener);
        phoneNumber.setName(PHONE_NUMBER);
    }

    /**
     * Returns the entered DNI.
     *
     * @return The DNI.
     */
    public String getDniText() {
        return dni.getText();
    }

    /**
     * Returns the entered email.
     *
     * @return The email.
     */
    public String getEmailText() {
        return email.getText();
    }

    /**
     * Returns the entered dorsal.
     *
     * @return The dorsal.
     */
    public String getDorsalText() {
        return dorsal.getText();
    }
    /**
     * Returns the entered phone number.
     *
     * @return The phone number.
     */
    public String getPhoneNumberText() {
        return phoneNumber.getText();
    }
    /**
     * Displays an exception message in a JOptionPane.
     *
     * @param message The exception message to display.
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    /**
     * Displays a number format error message in a JOptionPane.
     */
    public void numberFormatMessage(){
        JOptionPane.showMessageDialog(null, NUMBER_ERROR);
    }

    /**
     * Clean the variables in JTextFields.
     */
    public void clear(){
        email.setText(BLANK);
        dni.setText(BLANK);
        phoneNumber.setText(BLANK);
        dorsal.setText(BLANK);
    }
}
