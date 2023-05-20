package Persistance.dao;

import Persistance.MatchDAOInt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MatchDAOInt para realizar operaciones sobre la tabla "partido".
 */
public abstract class MatchDAO implements MatchDAOInt {
    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;

    /**
     * Método para insertar un partido en la base de datos.
     * @param equipoLocal El nombre del equipo local.
     * @param equipoVisitante El nombre del equipo visitante.
     * @param resultadoLocal El resultado del equipo local.
     * @param resultadoVisitante El resultado del equipo visitante.
     * @param jornada La jornada a la que pertenece el partido.
     * @param partidoFinalizado Indica si el partido ha finalizado.
     * @param nombreLiga El nombre de la liga a la que pertenece el partido.
     */
    public void insertMatch(String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga, Timestamp fechaInicio) {
        String query = "INSERT INTO partido (equipo_local, equipo_visitante, resultado_local, resultado_visitante, jornada, partido_finalizado, nombre_liga, fecha_inicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, equipoLocal);
            statement.setString(2, equipoVisitante);
            statement.setInt(3, resultadoLocal);
            statement.setInt(4, resultadoVisitante);
            statement.setInt(5, jornada);
            statement.setBoolean(6, partidoFinalizado);
            statement.setString(7, nombreLiga);
            statement.setTimestamp(8, fechaInicio);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener todos los partidos de la base de datos.
     * @return Una lista de partidos.
     */
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
                Timestamp fechaInicio = resultSet.getTimestamp("fecha_inicio");

                String matchInfo = "..., Fecha Inicio: " + fechaInicio;
                matches.add(matchInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    /**
     * Método para obtener los partidos de una liga específica.
     * @param nombreLiga El nombre de la liga.
     * @return Una lista de partidos de la liga especificada.
     */
    public List<String> getMatchesByLeague(String nombreLiga) {
        // Preparar la consulta SQL
        String query = "SELECT * FROM partido WHERE nombre_liga = ?";
        List<String> matches = new ArrayList<>();

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setString(1, nombreLiga);

            ResultSet resultSet = statement.executeQuery();

            // Procesar el ResultSet
            while (resultSet.next()) {
                // Obtener los detalles del partido
                String equipoLocal = resultSet.getString("equipo_local");
                String equipoVisitante = resultSet.getString("equipo_visitante");
                int resultadoLocal = resultSet.getInt("resultado_local");
                int resultadoVisitante = resultSet.getInt("resultado_visitante");
                int jornada = resultSet.getInt("jornada");
                boolean partidoFinalizado = resultSet.getBoolean("partido_finalizado");

                // Formatear la información del partido
                String matchInfo = "Equipo Local: " + equipoLocal +
                        ", Equipo Visitante: " + equipoVisitante +
                        ", Resultado Local: " + resultadoLocal +
                        ", Resultado Visitante: " + resultadoVisitante +
                        ", Jornada: " + jornada +
                        ", Partido Finalizado: " + partidoFinalizado +
                        ", Nombre Liga: " + nombreLiga;

                // Agregar la información del partido a la lista
                matches.add(matchInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolver la lista de partidos
        return matches;
    }

    /**
     * Método para actualizar un partido en la base de datos.
     * @param matchId El ID del partido a actualizar.
     * @param equipoLocal El nombre actualizado del equipo local.
     * @param equipoVisitante El nombre actualizado del equipo visitante.
     * @param resultadoLocal El resultado actualizado del equipo local.
     * @param resultadoVisitante El resultado actualizado del equipo visitante.
     * @param jornada La jornada actualizada.
     * @param partidoFinalizado El estado actualizado de si el partido ha finalizado.
     * @param nombreLiga El nombre actualizado de la liga.
     */
    public void updateMatch(int matchId, String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga) {
        // Preparar la consulta SQL
        String query = "UPDATE partido SET equipo_local = ?, equipo_visitante = ?, resultado_local = ?, resultado_visitante = ?, jornada = ?, partido_finalizado = ?, nombre_liga = ? WHERE id = ?";

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setString(1, equipoLocal);
            statement.setString(2, equipoVisitante);
            statement.setInt(3, resultadoLocal);
            statement.setInt(4, resultadoVisitante);
            statement.setInt(5, jornada);
            statement.setBoolean(6, partidoFinalizado);
            statement.setString(7, nombreLiga);
            statement.setInt(8, matchId);

            // Ejecutar la consulta de actualización
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar un partido de la base de datos.
     * @param matchId El ID del partido a eliminar.
     */
    public void deleteMatch(int matchId) {
        // Preparar la consulta SQL
        String query = "DELETE FROM partido WHERE id = ?";

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setInt(1, matchId);

            // Ejecutar la consulta de actualización
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para actualizar el resultado de un partido por el nombre del equipo local.
     * @param localTeamName El nombre del equipo local.
     * @param resultadoLocal El resultado actualizado del equipo local.
     * @param resultadoVisitante El resultado actualizado del equipo visitante.
     */
    public void updateMatchByLocalTeam(String localTeamName, int resultadoLocal, int resultadoVisitante) {
        // Preparar la consulta SQL
        String query = "UPDATE partido SET resultado_local = ?, resultado_visitante = ? WHERE equipo_local = ?";

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setInt(1, resultadoLocal);
            statement.setInt(2, resultadoVisitante);
            statement.setString(3, localTeamName);

            // Ejecutar la consulta de actualización
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar los partidos por el nombre del equipo local.
     * @param localTeamName El nombre del equipo local.
     */
    public void deleteMatchesByLocalTeam(String localTeamName) {
        // Preparar la consulta SQL
        String query = "DELETE FROM partido WHERE equipo_local = ?";

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setString(1, localTeamName);

            // Ejecutar la consulta de actualización
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener los ID de los partidos en una liga específica.
     * @param leagueName El nombre de la liga.
     * @return Una lista de IDs de partidos en la liga especificada.
     */
    public List<String> getMatchesInLeague(String leagueName) {
        // Crear una lista para almacenar los IDs de los partidos
        List<String> matches = new ArrayList<>();
        // Preparar la consulta SQL
        String query = "SELECT id FROM partido WHERE nombre_liga = ?";

        // Ejecutar la consulta
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setString(1, leagueName);

            // Obtener los resultados de la consulta
            ResultSet rs = statement.executeQuery();

            // Procesar el ResultSet
            while (rs.next()) {
                // Agregar cada ID a la lista de IDs de partidos
                matches.add(rs.getString("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolver la lista de IDs de partidos
        return matches;
    }

    /**
     * El método getMatchDetails obtiene los detalles del partido en el que un jugador está participando
     * para una jornada específica de la liga.
     *
     * Primero consulta la base de datos para encontrar el equipo del jugador. Luego, recupera los detalles del partido
     * para el equipo en la jornada especificada.
     *
     * Si el jugador no está asignado a ningún equipo o no hay partido en la jornada especificada para el equipo,
     * no mostrará ningún detalle del partido.
     *
     * @param dni el identificador único del jugador
     * @param jornada la jornada de la liga
     */
    public void getMatchDetails(String dni, int jornada) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Obtener el equipo del jugador
            String sql = "SELECT nombre_equipo FROM jugador_equipo WHERE dni_jugador = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String teamName = rs.getString("nombre_equipo");

                // Obtener el partido del equipo en la jornada específica
                sql = "SELECT * FROM partido WHERE jornada = ? AND (equipo_local = ? OR equipo_visitante = ?)";
                pstmt = conn.prepareStatement(sql);  // Creamos una nueva instancia de PreparedStatement
                pstmt.setInt(1, jornada);
               // pstmt.setString(2, teamName);
               // pstmt.setString(3, teamName);

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    String localTeam = rs.getString("equipo_local");
                    String visitingTeam = rs.getString("equipo_visitante");
                    int localScore = rs.getInt("resultado_local");
                    int visitorScore = rs.getInt("resultado_visitante");
                    // Mostrar los detalles del partido
                    System.out.println(localTeam + " vs " + visitingTeam + ": " + localScore + " - " + visitorScore);
                }
            } else {
                System.out.println("El jugador no está asignado a ningún equipo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}