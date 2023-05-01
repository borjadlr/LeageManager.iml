package Presentation.Views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Font.PLAIN;

public class NewLeagueGUI extends JPanel {

    private JTextField leagueName;
    private JTextField password;
    private JButton ok;
    private JButton createAccount;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    private Color backgroundColor;

    public static final String OK_BUTTON = "OK_BUTTON";
    public static final String BACK_BUTTON = "BACK_BUTTON";

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

        //Title
        JLabel title = new JLabel("Create a new league");
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        // Dni/Email
        String defaultDniText = "League name: ";
        leagueName = new JTextField(defaultDniText);
        leagueName.setFont(new Font("Inter", PLAIN, 20));
        leagueName.setBackground(Color.decode("#D9D9D9"));
        leagueName.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        leagueName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (leagueName.getText().equals(defaultDniText)) {
                    leagueName.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (leagueName.getText().isEmpty()) {
                    leagueName.setText(defaultDniText);
                }
            }
        });
        panel.add(leagueName);

        panel.add(Box.createVerticalStrut(15));

        JPanel date = new JPanel(new BorderLayout());
        date.setBackground(backgroundColor);

        panel.add(date);

        String defaultDateText = "Date: ";
        password = new JTextField(defaultDateText);
        password.setFont(new Font("Inter", PLAIN, 20));
        password.setBackground(Color.decode("#D9D9D9"));
        password.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        password.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (password.getText().equals(defaultDateText)) {
                    password.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (password.getText().isEmpty()) {
                    password.setText(defaultDateText);
                }
            }
        });
        password.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                password.setText("");
            }
        });
        date.add(password, BorderLayout.CENTER);

        panel.add(Box.createVerticalStrut(10));

        ok = new JButton("OK");
        ok.setActionCommand(OK_BUTTON);
        ok.setPreferredSize(new Dimension(600, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ok.setBackground(Color.gray);
        panel.add(ok);

        this.add(panel);
    }
}