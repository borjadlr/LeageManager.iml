package Persistance.dao;


import Business.Entities.Config;
import Business.Entities.Match;
import Business.Entities.Team;
import Persistance.MatchDAOInt;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementación de la interfaz MatchDAOInt para realizar operaciones sobre la tabla "partido".
 */
public class MatchDAO implements MatchDAOInt {
    private String dbURL;
    private String username;
    private String password;
    private Connection conn;

    public MatchDAO() {
        try {

            // Leer la configuración JSON y obtener los valores correspondientes

            ConfigJsonDAO configJsonDAO = new ConfigJsonDAO();

            Config config = configJsonDAO.leerConfiguracionJson("C:\\Users\\borja\\LeageManager\\Files\\configs.json");

            // Asignar los valores obtenidos a las variables dbURL, username y password
            dbURL = "jdbc:mysql://" + config.getIpServidorBD() + ":" + config.getPortConexionBD() + "/" + config.getNombreBD();
            username = config.getUsuarioBD();
            password = config.getContrasenaBD();

            // Establecer la conexión aquí
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para insertar un partido en la base de datos.
     *
     * @param equipoLocal         El nombre del equipo local.
     * @param equipoVisitante     El nombre del equipo visitante.
     * @param resultadoLocal      El resultado del equipo local.
     * @param resultadoVisitante  El resultado del equipo visitante.
     * @param jornada             La jornada a la que pertenece el partido.
     * @param partidoFinalizado   Indica si el partido ha finalizado.
     * @param nombreLiga          El nombre de la liga a la que pertenece el partido.
     */
    public void insertMatch(String equipoLocal, String equipoVisitante, int resultadoLocal, int resultadoVisitante, int jornada, boolean partidoFinalizado, String nombreLiga) {
        String query = "INSERT INTO partido (equipo_local, equipo_visitante, resultado_local, resultado_visitante, jornada, partido_finalizado, nombre_liga, fecha_inicio) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    /**
     * Método para insertar un partido en la base de datos utilizando un objeto Match.
     *
     * @param partido El objeto Match que contiene la información del partido.
     * @throws SQLException si ocurre algún error al interactuar con la base de datos.
     */
    public void insertMatchBeta(Match partido) throws SQLException {
        String query = "INSERT INTO partido (equipo_local, equipo_visitante, resultado_local, resultado_visitante, jornada, partido_finalizado, nombre_liga) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, partido.getLocal());
            statement.setString(2, partido.getVisitante());
            statement.setInt(3, partido.getGolesLocal());
            statement.setInt(4, partido.getGolesVisitante());
            statement.setInt(5, partido.getJornada());
            statement.setBoolean(6, partido.isStatus());
            statement.setString(7, partido.getNombreLiga());
            statement.executeUpdate();
        }
    }

    /**
     * Método para obtener todos los partidos de la base de datos.
     *
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
     *
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
     *
     * @param matchId            El ID del partido a actualizar.
     * @param equipoLocal        El nombre actualizado del equipo local.
     * @param equipoVisitante    El nombre actualizado del equipo visitante.
     * @param resultadoLocal     El resultado actualizado del equipo local.
     * @param resultadoVisitante El resultado actualizado del equipo visitante.
     * @param jornada            La jornada actualizada.
     * @param partidoFinalizado  El estado actualizado de si el partido ha finalizado.
     * @param nombreLiga         El nombre actualizado de la liga.
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
     *
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
     *
     * @param localTeamName       El nombre del equipo local.
     * @param resultadoLocal      El resultado actualizado del equipo local.
     * @param resultadoVisitante  El resultado actualizado del equipo visitante.
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
     * Método para obtener los ID de los partidos en una liga específica.
     *
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
     * @param dni     el identificador único del jugador
     * @param jornada la jornada de la liga
     * @return el objeto Match que contiene los detalles del partido en el que el jugador está participando, o null si no se encontró ningún partido.
     */
    public Match getMatchDetails(String dni, int jornada) {
        Match match = null;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Obtener el equipo del jugador
            String sql = "SELECT nombre_equipo FROM jugador_equipo WHERE dni_jugador = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                String teamName = rs.getString("nombre_equipo");
                match = null;
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

        return match;
    }

    /**
     * Elimina los partidos en función del nombre del equipo introducido.
     *
     * @param teamName nombre del equipo cuyos partidos se eliminarán
     * @throws SQLException si ocurre algún error al interactuar con la base de datos
     */
    public void deleteMatchesByTeamName(String teamName) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Preparamos la consulta SQL para eliminar los partidos del equipo en la tabla "partido"
            PreparedStatement deleteStmt = conn.prepareStatement(
                    "DELETE FROM partido WHERE equipo_local = ? OR equipo_visitante = ?");
            deleteStmt.setString(1, teamName);
            deleteStmt.setString(2, teamName);
            deleteStmt.executeUpdate();
            deleteStmt.close();
        }
    }

    /**
     * Obtiene los IDs de los partidos ordenados por jornada y hora.
     */
    public void getMatchIdsByDayAndTime() {
        String url = "jdbc:mysql://localhost/league_manager_data";
        String username = "dreamteam";
        String password = "dreamteam";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT id FROM partido ORDER BY jornada, hora ASC";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int matchId = resultSet.getInt("id");
                System.out.println("ID del partido: " + matchId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un calendario de una liga con partidos de ida y vuelta a partir de una lista de equipos.
     *
     * @param equipos     la lista de equipos de la liga
     * @return una lista enlazada con el orden de los partidos
     */
    public LinkedList<Match> crearCalendarioIdaVuelta(List<Team> equipos, String nombreLiga) {
        LinkedList<Match> calendario = new LinkedList<>();
        int numEquipos = equipos.size();
        int numJornadas = numEquipos - 1;
        boolean omitirEquipo = numEquipos % 2 != 0;

        LinkedList<Team> equiposCopia = new LinkedList<>(equipos);

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Conexión a la base de datos

            int jornadaIda = 1;
            int jornadaVuelta = numJornadas + 1;

            for (int jornada = 0; jornada < numJornadas * 2; jornada++) {
                int jornadaActual;

                if (jornada < numJornadas) {
                    jornadaActual = jornadaIda;
                    jornadaIda++;
                } else {
                    jornadaActual = jornadaVuelta;
                    jornadaVuelta++;
                }

                System.out.println("Jornada " + jornadaActual + ":");

                // Verificar si hay que descansar un equipo en esta jornada
                boolean descansaEquipo = omitirEquipo && jornada % 2 == 0 && jornada < numJornadas * 2 - 1;
                Team equipoDescansa = null;

                for (int i = 0; i < numEquipos / 2; i++) {
                    Team equipoLocal = equiposCopia.get(i);
                    Team equipoVisitante = equiposCopia.get(numEquipos - 1 - i);

                    // Verificar si el equipo local es igual al equipo visitante
                    if (equipoLocal.getName().equals(equipoVisitante.getName())) {
                        continue; // Saltar a la siguiente iteración si es el mismo equipo
                    }

                    Match partido;

                    if (jornada < numJornadas) {
                        partido = new Match(equipoLocal.getName(), equipoVisitante.getName(), 0, 0, jornadaActual, false, nombreLiga);
                        System.out.println("Partido de ida: " + partido.getLocal() + " vs " + partido.getVisitante());
                    } else {
                        partido = new Match(equipoVisitante.getName(), equipoLocal.getName(), 0, 0, jornadaActual, false, nombreLiga);
                        System.out.println("Partido de vuelta: " + partido.getLocal() + " vs " + partido.getVisitante());
                    }

                    calendario.add(partido);
                    insertMatchBeta(partido);

                    // Verificar si se debe asignar un equipo que descansa en esta jornada
                    if (descansaEquipo) {
                        equipoDescansa = equipoLocal;
                    }
                }

                // Imprimir el equipo que descansa en esta jornada
                if (descansaEquipo) {
                    System.out.println("Descansa: " + equipoDescansa.getName());
                }

                // Rotar los equipos en sentido horario, omitiendo un equipo si es necesario
                equiposCopia.add(1, equiposCopia.removeLast());
            }

            // Cerrar la conexión a la base de datos
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calendario;
    }

    /**
     * Método que obtiene todos los partidos de la base de datos para una jornada específica
     * y los retorna en forma de LinkedList.
     *
     * @param jornada La jornada para la cual se desean obtener los partidos.
     * @return Una LinkedList que contiene los partidos de la jornada especificada.
     */
    public LinkedList<Match> obtenerPartidosPorJornada(int jornada) {
        LinkedList<Match> partidos = new LinkedList<>();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Conexión a la base de datos

            String query = "SELECT * FROM partido WHERE jornada = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, jornada);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los datos del partido desde el ResultSet
                String equipoLocal = resultSet.getString("equipo_local");
                String equipoVisitante = resultSet.getString("equipo_visitante");
                int golesLocal = resultSet.getInt("resultado_local");
                int golesVisitante = resultSet.getInt("resultado_visitante");
                boolean esVuelta = resultSet.getBoolean("partido_finalizado");
                String nombreLiga = resultSet.getString("nombre_liga");

                // Crear un objeto Match con los datos obtenidos y agregarlo a la LinkedList de partidos
                Match partido = new Match(equipoLocal, equipoVisitante, golesLocal, golesVisitante, jornada, esVuelta, nombreLiga);
                partidos.add(partido);
                System.out.println(equipoLocal);
                System.out.println(equipoVisitante);
            }

            // Cerrar el ResultSet, PreparedStatement y la conexión a la base de datos
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }


    /**
     * Método que obtiene todos los partidos de una liga y una jornada específica desde la base de datos.
     *
     * @param nombreLiga El nombre de la liga para la cual se desean obtener los partidos.
     * @param jornada    La jornada para la cual se desean obtener los partidos.
     * @return Una LinkedList que contiene los partidos de la liga y jornada especificadas.
     */
    public List<Match> obtenerPartidosPorLigaYJornada(String nombreLiga, int jornada) {
        List<Match> partidos = new LinkedList<>();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            // Conexión a la base de datos

            String query = "SELECT * FROM partido WHERE nombre_liga = ? AND jornada = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nombreLiga);
            statement.setInt(2, jornada);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los datos del partido desde el ResultSet
                String equipoLocal = resultSet.getString("equipo_local");
                String equipoVisitante = resultSet.getString("equipo_visitante");
                int golesLocal = resultSet.getInt("resultado_local");
                int golesVisitante = resultSet.getInt("resultado_visitante");
                boolean esVuelta = resultSet.getBoolean("partido_finalizado");

                // Crear un objeto Match con los datos obtenidos y agregarlo a la LinkedList de partidos
                Match partido = new Match(equipoLocal, equipoVisitante, golesLocal, golesVisitante, jornada, esVuelta, nombreLiga);
                partidos.add(partido);
                System.out.println(equipoLocal);
                System.out.println(equipoVisitante);
                System.out.println("-----------");

            }

            // Cerrar el ResultSet, PreparedStatement y la conexión a la base de datos
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }



















    /**
     * Incrementa el resultado de un equipo en un partido de la liga en una jornada específica.
     *
     * @param nombreEquipo el nombre del equipo al que se le incrementará el resultado
     * @param jornada      la jornada del partido
     */
    public void sumaGol(String nombreEquipo, int jornada) {
        // Conexión a la base de datos y control de excepciones
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Successful connection...");

            // Sentencia SQL para actualizar el resultado del equipo en la tabla "partido"
            String sql = "UPDATE partido SET resultado_local = resultado_local + 1 WHERE (equipo_local = ? OR equipo_visitante = ?) AND jornada = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombreEquipo);
            statement.setString(2, nombreEquipo);
            statement.setInt(3, jornada);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("A goal has been added to the team: " + nombreEquipo + " in matchday: " + jornada);
            } else {
                System.out.println("No team found with the name: " + nombreEquipo + " or matchday: " + jornada);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
