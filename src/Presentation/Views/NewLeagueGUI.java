package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class NewLeagueGUI extends JFrame {

    private JTextField leagueName;
    private JTextField date;
    private String leagueNameText;
    private String dateText;



    public NewLeagueGUI() {
        //Panel on s'ha de fer CardLayout.
        JPanel restSection = new JPanel(new BorderLayout());
        restSection.setVisible(true);
        restSection.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        add(restSection, BorderLayout.CENTER);
        restSection.setBackground(Color.white);

        JLabel Titol = new JLabel("Create a new league");
        Titol.setHorizontalAlignment(JLabel.CENTER);
        Titol.setVerticalAlignment(JLabel.NORTH);
        Titol.setFont(new Font("Inter", PLAIN, 60));
        restSection.add(Titol, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        restSection.add(inputPanel, BorderLayout.CENTER);
        inputPanel.setBackground(Color.white);

        JLabel leagueNameLabel = new JLabel("league name:");
        leagueNameLabel.setFont(new Font("Inter", PLAIN, 20));
        leagueNameLabel.setBounds(448, 30, 150, 25);
        inputPanel.add(leagueNameLabel);
        leagueName = new JTextField(20);
        leagueName.setFont(new Font("Inter", PLAIN, 20));
        leagueName.setBounds(590, 30, 200, 30);
        leagueName.setBackground(Color.decode("#D9D9D9"));
        leagueName.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(leagueName);

        JLabel dateLabel = new JLabel("date:");
        dateLabel.setFont(new Font("Inter", PLAIN, 20));
        dateLabel.setBounds(522, 90, 150, 25);
        inputPanel.add(dateLabel);
        date = new JTextField(20);
        date.setFont(new Font("Inter", PLAIN, 20));
        date.setBounds(590, 90, 200, 30);
        date.setBackground(Color.decode("#D9D9D9"));
        date.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        inputPanel.add(date);

        JButton ok = new JButton("OK");
        ok.setBounds(628, 140, 40, 30);
        ok.setBorder(BorderFactory.createLineBorder(Color.black,2));
        inputPanel.add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leagueNameText = leagueName.getText();
                dateText = date.getText();

                // Add code here to save the values to a file or database
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1000, 800);
        setTitle("New League");
    }
}
