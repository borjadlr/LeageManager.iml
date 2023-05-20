package Persistance;

import java.util.List;

public interface MatchDAOInt {
    void insertMatch(String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga);
    List<String> getAllMatches();
    List<String> getMatchesByLeague(String nombreLiga);
    void updateMatch(int matchId, String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga);
    void deleteMatch(int matchId);
}
