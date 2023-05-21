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
    private JTextField hora;
    private JTextField data;
    private JTextField numeroEquipos;
    private JButton ok;
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    private Color backgroundColor;

    public static final String OK_BUTTON = "OK_BUTTON";
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
        JLabel title = new JLabel("LaegueManager Registration");
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

    public JTextField getLeagueName() {
        return leagueName;
    }

    public JTextField getHora() {
        return hora;
    }

    public JTextField getData() {
        return data;
    }

    public JTextField getNumeroEquipos() {
        return numeroEquipos;
    }

    public void registerListener(ActionListener actionListener){
        ok.addActionListener(actionListener);

    }

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
}