package Persistance;

import Business.Entities.Match;
import Business.Entities.Team;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public interface MatchDAOInt {
    void insertMatch(String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga);
    List<String> getAllMatches();
    void insertMatchBeta(Match match) throws SQLException;
    List<String> getMatchesByLeague(String nombreLiga);
    void updateMatch(int matchId, String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga);
    void deleteMatch(int matchId);
    void updateMatchByLocalTeam(String localTeamName, int resultadoLocal, int resultadoVisitante);
    List<String> getMatchesInLeague(String leagueName);
    Match getMatchDetails(String dni, int jornada);
    void deleteMatchesByTeamName(String teamName) throws SQLException;
    void getMatchIdsByDayAndTime();
    List<Match> crearCalendarioIdaVuelta(List<Team> equipos,String nombreLiga);
    void sumaGol(String equipo, int jornada);
    LinkedList<Match> obtenerPartidosPorJornada(int jornada);
    LinkedList<Match> obtenerPartidosPorLigaYJornada(String nombreLiga, int jornada);
}

