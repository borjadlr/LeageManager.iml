package Persistance.dao;

import Persistance.MatchDAOInt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements MatchDAOInt {
    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;



    public void insertMatch(String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga) {
        String query = "INSERT INTO partido (equipo_local, equipo_visitante, resultado_local, resultado_visitante, jornada, partido_finalizado, nombre_liga) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, equipoLocal);
            statement.setString(2, equipoVisitante);
            statement.setInt(3, resultadoLocal);
            statement.setInt(4, resultadoVisitante);
            statement.setInt(5, jornada);
            statement.setBoolean(6, partidoFinalizado);
            statement.setString(7, nombreLiga);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String> getAllMatches() {
        String query = "SELECT * FROM partido";
        List<String> matches = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String equipoLocal = resultSet.getString("equipo_local");
                String equipoVisitante = resultSet.getString("equipo_visitante");
                int resultadoLocal = resultSet.getInt("resultado_local");
                int resultadoVisitante = resultSet.getInt("resultado_visitante");
                int jornada = resultSet.getInt("jornada");
                boolean partidoFinalizado = resultSet.getBoolean("partido_finalizado");
                String nombreLiga = resultSet.getString("nombre_liga");

                String matchInfo = "Equipo Local: " + equipoLocal +
                        ", Equipo Visitante: " + equipoVisitante +
                        ", Resultado Local: " + resultadoLocal +
                        ", Resultado Visitante: " + resultadoVisitante +
                        ", Jornada: " + jornada +
                        ", Partido Finalizado: " + partidoFinalizado +
                        ", Nombre Liga: " + nombreLiga;

                matches.add(matchInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }


    public List<String> getMatchesByLeague(String nombreLiga) {
        String query = "SELECT * FROM partido WHERE nombre_liga = ?";
        List<String> matches = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreLiga);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String equipoLocal = resultSet.getString("equipo_local");
                String equipoVisitante = resultSet.getString("equipo_visitante");
                int resultadoLocal = resultSet.getInt("resultado_local");
                int resultadoVisitante = resultSet.getInt("resultado_visitante");
                int jornada = resultSet.getInt("jornada");
                boolean partidoFinalizado = resultSet.getBoolean("partido_finalizado");
                String matchInfo = "Equipo Local: " + equipoLocal +
                        ", Equipo Visitante: " + equipoVisitante +
                        ", Resultado Local: " + resultadoLocal +
                        ", Resultado Visitante: " + resultadoVisitante +
                        ", Jornada: " + jornada +
                        ", Partido Finalizado: " + partidoFinalizado +
                        ", Nombre Liga: " + nombreLiga;

                matches.add(matchInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }


    public void updateMatch(int matchId, String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga) {
        String query = "UPDATE partido SET equipo_local = ?, equipo_visitante = ?, resultado_local = ?, resultado_visitante = ?, jornada = ?, partido_finalizado = ?, nombre_liga = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, equipoLocal);
            statement.setString(2, equipoVisitante);
            statement.setInt(3, resultadoLocal);
            statement.setInt(4, resultadoVisitante);
            statement.setInt(5, jornada);
            statement.setBoolean(6, partidoFinalizado);
            statement.setString(7, nombreLiga);
            statement.setInt(8, matchId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteMatch(int matchId) {
        String query = "DELETE FROM partido WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMatchByLocalTeam(String localTeamName, int resultadoLocal, int resultadoVisitante) {
        String query = "UPDATE partido SET resultado_local = ?, resultado_visitante = ? WHERE equipo_local = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, resultadoLocal);
            statement.setInt(2, resultadoVisitante);
            statement.setString(3, localTeamName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMatchesByLocalTeam(String localTeamName) {
        String query = "DELETE FROM partido WHERE equipo_local = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, localTeamName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMatchesInLeague(String leagueName) {
        List<String> matches = new ArrayList<>();
        String query = "SELECT id FROM partido WHERE nombre_liga = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, leagueName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                matches.add(rs.getString("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }



    // ...
}
