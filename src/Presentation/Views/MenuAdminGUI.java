package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuAdminGUI extends JPanel{

    private final JButton createNewTeam;
    private JButton deleteTeam;
    private JButton newLeague;
    private JButton deleteLeague;
    private JButton viewLeagues;
    private final JButton back;

    private Color backgroundColor;

    public static final String CREATE_NEW_TEAM = "CREATE_ACCOUNT";
    public static final String DELETE_TEAM = "DELETE_TEAM";
    public static final String NEW_LEAGUE = "NEW_LEAGUE";
    public static final String DELETE_LEAGUE = "DELETE_LEAGUE";
    public static final String VIEW_LEAGUES = "VIEW_LEAGUES";
    private static final String BACK_BUTTON = "BACK";

    public MenuAdminGUI() {

        backgroundColor = Color.lightGray;
        setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(backgroundColor);
        imagePanel.setOpaque(true);
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        panel.add(Box.createVerticalGlue());

        //Title
        JLabel title = new JLabel("Admin Menu");
        title.setFont(new Font("Apple Casual", Font.PLAIN, 25));
        title.setForeground(Color.black);
        title.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        //Create account
        createNewTeam = new JButton("Create account");
        createNewTeam.setActionCommand(CREATE_NEW_TEAM);
        createNewTeam.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createNewTeam);

        //Delete team
        deleteTeam = new JButton("Delete Team");
        deleteTeam.setActionCommand(DELETE_TEAM);
        deleteTeam.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(deleteTeam);

        //Create new league
        newLeague = new JButton("Create New League");
        newLeague.setActionCommand(NEW_LEAGUE);
        newLeague.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newLeague);

        //Delete League
        deleteLeague = new JButton("Delete League");
        deleteLeague.setActionCommand(DELETE_LEAGUE);
        deleteLeague.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newLeague);

        //View Leagues
        viewLeagues = new JButton("View Leagues");
        viewLeagues.setActionCommand(VIEW_LEAGUES);
        viewLeagues.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newLeague);

        //Back button
        back = new JButton("Back");
        back.setActionCommand(BACK_BUTTON);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(back);
        this.add(panel);
    }

    public void registerListener(ActionListener listener) {
        createNewTeam.addActionListener(listener);
        back.addActionListener(listener);
    }
}
