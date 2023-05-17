package Persistance;

import Business.Entities.Match;

public interface MatchDAOInt {
    void createPartido(Match partido);
    Match readPartido(String equipoLocal, String equipoVisitante);
    void updatePartido(Match partido, String nuevoResultado);
    void deletePartido(Match partido);
}
