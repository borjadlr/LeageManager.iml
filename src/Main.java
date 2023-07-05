import Business.Entities.League;
import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.*;
import Persistance.dao.TeamsDAO;
import Persistance.dao.TeamsLeagueDAO;
import Persistance.dao.*;
import Persistance.dao.UserTeamsDAO;
import Presentation.Controllers.*;
import Presentation.Views.*;

public class Main {
    public static void main(String[] args) {

        //Entities
        Team team = new Team();
        League league = new League();

        //Dao
        UserDAO userDAO = new UserDAO();
        TeamsDAO teamsDAO = new TeamsDAO();
        UserTeamsDAO userTeamsDAO = new UserTeamsDAO();
        TeamsLeagueDAO teamsLeagueDAO = new TeamsLeagueDAO();
        MatchDAO matchDAO = new MatchDAO();
        LeagueDAO leagueDAO = new LeagueDAO();

        //Managers
        User user = new User();
        TeamManager teamManager = new TeamManager(teamsDAO, teamsLeagueDAO, userTeamsDAO, team);
        LeagueManager leagueManager = new LeagueManager(teamManager, leagueDAO, teamsDAO, matchDAO, teamsLeagueDAO);
        AdminManager adminManaguer = new AdminManager(userDAO, leagueManager, teamManager);
        UserManager userManager = new UserManager(userDAO, leagueManager, teamManager, userTeamsDAO, teamsLeagueDAO, user, adminManaguer);
        MatchManager matchManager = new MatchManager(matchDAO, leagueDAO);

        //Vistas
        LoginGUI loginGUI = new LoginGUI();
        StatisticsGUI statisticsGUI = new StatisticsGUI();
        SimulationGameGUI simulationGameGUI = new SimulationGameGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MainPanelGUI mainPanelGUI = new MainPanelGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        NewLeagueGUI newLeaguesGUI = new NewLeagueGUI();
        RegistrationGUI registrationGUI = new RegistrationGUI();
        TopPanelGUI topPanelGUI = new TopPanelGUI();
        BottomPanelGUI bottomPanelGUI = new BottomPanelGUI();
        DeleteGUI deleteGUI = new DeleteGUI();
        ListLeagueUserGUI listLeagueUserGUI = new ListLeagueUserGUI();
        ListTeamUserGUI listTeamUserGUI = new ListTeamUserGUI();
        NewTeamGUI newTeamGUI = new NewTeamGUI();
        ListLeagueAdminGUI listLeagueAdminGUI = new ListLeagueAdminGUI();
        ListTeamAdminGUI listTeamAdminGUI = new ListTeamAdminGUI();
        TeamListCreateLeague teamListCreateLeague = new TeamListCreateLeague();
        SimulationLeagueGUI simulationLeagueGUI = new SimulationLeagueGUI();
        ListPlayerGUI listPlayerGUI = new ListPlayerGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, newLeaguesGUI, registrationGUI, mainPanelGUI, deleteGUI, listPlayerGUI, statisticsGUI,simulationGameGUI, topPanelGUI, bottomPanelGUI, newTeamGUI, listLeagueAdminGUI, teamListCreateLeague, simulationLeagueGUI, listTeamAdminGUI);

        //Controllers
        MainPanelController mainPanelController = new MainPanelController(mainFrame, topPanelGUI);
        RegistrationController registrationController =  new RegistrationController(mainFrame, registrationGUI, userManager, topPanelGUI);
        LoginController loginController = new LoginController(mainFrame, loginGUI, userManager);
        ChangePasswordController changePasswordController = new ChangePasswordController(mainFrame, changePasswordGUI, userManager);
        MenuAdminController menuAdminController = new MenuAdminController(mainFrame, leagueManager, menuAdminGUI, listLeagueAdminGUI, matchManager, simulationLeagueGUI);
        TopPanelController topPanelController =  new TopPanelController(mainFrame, topPanelGUI);
        DeleteController deleteController = new DeleteController(mainFrame, deleteGUI, userManager);
        NewLeagueController newLeagueController = new NewLeagueController(mainFrame, newLeaguesGUI, leagueManager, teamManager, teamListCreateLeague, topPanelGUI);
        ListLeagueUserController listLeagueUserController = new ListLeagueUserController(listLeagueUserGUI, mainFrame, listTeamUserGUI, leagueManager);
        BottomPanelController bottomPanelController = new BottomPanelController(mainFrame);
        NewTeamController newTeamController = new NewTeamController(mainFrame, newTeamGUI, teamManager);
        MenuUserController menuUserController = new MenuUserController(mainFrame, leagueManager, listLeagueUserGUI, menuUserGUI);
        ListLeagueAdminController listLeagueAdminController = new ListLeagueAdminController(listLeagueAdminGUI, leagueManager, listTeamAdminGUI, mainFrame, teamManager);
        ListTeamUserController listTeamUserController = new ListTeamUserController(listTeamUserGUI, mainFrame, teamManager);
        ListTeamAdminController listTeamAdminController = new ListTeamAdminController(listTeamAdminGUI, listPlayerGUI, mainFrame, teamManager, listLeagueAdminController);
        TeamListCreateLeagueController teamListCreateLeagueController = new TeamListCreateLeagueController(teamListCreateLeague, teamManager, mainFrame, leagueManager, newLeagueController);

        //Buttons
        loginGUI.actionListener(loginController);
        loginGUI.focusListener(loginController);
        mainPanelGUI.registerListener(mainPanelController);
        registrationGUI.foscusListener(registrationController);
        registrationGUI.registerRegistration(registrationController);
        changePasswordGUI.registerChangePassword(changePasswordController);
        changePasswordGUI.actionListenerPassword(changePasswordController);
        deleteGUI.actionListener(deleteController);
        deleteGUI.focusListener(deleteController);
        menuAdminGUI.menuAdminListener(menuAdminController);
        newLeaguesGUI.newLeagueFocusListener(newLeagueController);
        newLeaguesGUI.registerListener(newLeagueController);
        listLeagueUserGUI.setController(listLeagueUserController);
        bottomPanelGUI.changePasswordListener(bottomPanelController);
        topPanelGUI.actionListener(topPanelController);
        newTeamGUI.actionListener(newTeamController);
        menuUserGUI.registerListener(menuUserController);
        listLeagueAdminGUI.setController(listLeagueAdminController);
        listTeamUserGUI.setController(listTeamUserController);
        teamListCreateLeague.setController(teamListCreateLeagueController);
        listTeamAdminGUI.setController(listTeamAdminController);
    }
}
