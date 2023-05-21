package Persistance.dao;

import Business.Entities.Team;
import Persistance.TeamsDAOInt;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sql connector LEAGUE.
 */
public class TeamsDAO implements TeamsDAOInt {

    private static String dbURL = "jdbc:mysql://localhost:3306/league_manager_data";
    private static String username = "dreamteam";
    private static String password = "dreamteam";
    private static Connection conn;


    /**
     * Metodo que añade informacion dentro de la tabla teams
     * @param name
     * @param nplayers
     * @param wins
     * @param ties
     * @param losses
     * @param points
     */
    public void insertDataTeams(String name, int nplayers, int wins, int ties, int losses, int points) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para inertar datos en la tabla league
            String sql = "INSERT INTO equipo (nombre,num_jugadores,num_jugadores,num_empates,num_derrotas,puntos_acumulados) VALUES (?, ? ,? ,? ,? ,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, nplayers);
            statement.setInt(3, wins);
            statement.setInt(4,ties);
            statement.setInt(5,losses);
            statement.setInt(6,points);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Este método inserta un nuevo equipo en la base de datos.
     *
     * @param team  La instancia de Team que contiene los detalles del equipo que se va a insertar.
     *
     *
     * @throws SQLException Si hay un error al ejecutar la sentencia SQL para insertar el equipo.
     */
    public void insertDataTeamsByTeam(Team team) {
        // Conexión con la base de datos y control de excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            // Imprime un mensaje para confirmar la conexión exitosa.
            System.out.println("Conexion ok");

            // Generación de un statement SQL para insertar datos en la tabla equipo.
            String sql = "INSERT INTO equipo (nombre, num_jugadores, num_victorias, num_empates, num_derrotas, puntos_acumulados) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparación del statement SQL.
            PreparedStatement statement = conn.prepareStatement(sql);

            // Configuración de los parámetros del statement con los valores del objeto Team.
            statement.setString(1, team.getName());
            statement.setInt(2, team.getNPlayers());
            statement.setInt(3, team.getWins());
            statement.setInt(4, team.getTies());
            statement.setInt(5, team.getLosses());
            statement.setInt(6, team.getPoints());

            // Ejecución del statement y recogida del número de filas insertadas.
            int rowsInserted = statement.executeUpdate();

            // Imprime un mensaje si el equipo fue insertado exitosamente.
            if (rowsInserted > 0) {
                System.out.println("A new team was inserted successfully!");
            }

        } catch (SQLException ex) {
            // Imprime el stack trace de la excepción en caso de error.
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para actualizar la informacion de un esquipo
     * @param name1 nombre que al que se quiere actualizar
     * @param nplayers parametro sobre el nombre de jugadores que se quiere actualizar
     * @param wins parametro sobre el nombre de victorias que se quiere actualizar
     * @param ties parametro sobre el nombre de empates que se quiere actualizar
     * @param losses parametro sobre el nombre de derrotas que se quiere actualizar
     * @param points parametro sobre el nombre de puntos que se quiere actualizar
     * @param name2 nombre en el que se quiere cambiar los parametros o parametro anterior
     */
    public void updateDataTeams(String name1,int nplayers, int wins, int ties, int losses, int points, String name2){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para actualizar la tabla liga.
            String sql = "UPDATE equipo SET nombre = ?,num_jugadores = ?,num_victorias = ?,num_empates = ?,num_derrotas = ?,puntos_acumulados = ? WHERE nombre= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setInt(2, nplayers);
            statement.setInt(3, wins);
            statement.setInt(4,ties);
            statement.setInt(5,losses);
            statement.setInt(6,points);
            statement.setString(7, name2);


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing league was updated successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void deleteDataTeams(String teamName) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Conexión exitosa");

            // Eliminar la relación del equipo con la liga
            String deleteEquipoLigaQuery = "DELETE FROM equipo_liga WHERE nombre_equipo = ?";
            PreparedStatement deleteEquipoLigaStmt = conn.prepareStatement(deleteEquipoLigaQuery);
            deleteEquipoLigaStmt.setString(1, teamName);
            deleteEquipoLigaStmt.executeUpdate();
            deleteEquipoLigaStmt.close();

            // Eliminar la relación del equipo con los jugadores
            String deleteJugadorEquipoQuery = "DELETE FROM jugador_equipo WHERE nombre_equipo = ?";
            PreparedStatement deleteJugadorEquipoStmt = conn.prepareStatement(deleteJugadorEquipoQuery);
            deleteJugadorEquipoStmt.setString(1, teamName);
            deleteJugadorEquipoStmt.executeUpdate();
            deleteJugadorEquipoStmt.close();

            // Eliminar el equipo
            String deleteEquipoQuery = "DELETE FROM equipo WHERE nombre = ?";
            PreparedStatement deleteEquipoStmt = conn.prepareStatement(deleteEquipoQuery);
            deleteEquipoStmt.setString(1, teamName);
            deleteEquipoStmt.executeUpdate();
            deleteEquipoStmt.close();

            System.out.println("Eliminación exitosa del equipo y sus relaciones.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





    public Team selectTeam(String nombreEquipo) throws SQLException {
        String query = "SELECT * FROM equipo WHERE nombre = ?";
        Team equipo = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nombreEquipo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    int numJugadores = resultSet.getInt("num_jugadores");
                    int numVictorias = resultSet.getInt("num_victorias");
                    int numDerrotas = resultSet.getInt("num_derrotas");
                    int numEmpates = resultSet.getInt("num_empates");
                    int puntosAcumulados = resultSet.getInt("puntos_acumulados");
                    equipo = new Team( nombreEquipo, numJugadores, numVictorias, numDerrotas, numEmpates, puntosAcumulados);
                }
            }
        }
        return equipo;
    }
    public String readJsonFile(String fileName) {
        StringBuilder jsonString = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString.toString();
    }

        public void jsonToDatabase(String jsonFileName) {
            //Llamamos al metodo que abre y lee el archivo json
            String jsonString = readJsonFile(jsonFileName);


            // crea un objeto Gson para parsear el String JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);


            // extrae el nombre del equipo de el objeto JSON.
            String teamName = jsonObject.get("team_name").getAsString();


            // extrae el array de jugadores de el JSON
            JsonArray playersArray = jsonObject.getAsJsonArray("players");

            try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

                // introduce la informacion del elquipo en la tabla equipo
                PreparedStatement equipoStmt = conn.prepareStatement(
                        "INSERT INTO equipo (nombre, num_jugadores, num_victorias, num_derrotas, num_empates, puntos_acumulados) VALUES (?, ?, ?, ?, ?, ?)");
                equipoStmt.setString(1, teamName);
                equipoStmt.setInt(2, playersArray.size());
                equipoStmt.setInt(3, 0);
                equipoStmt.setInt(4, 0);
                equipoStmt.setInt(5, 0);
                equipoStmt.setInt(6, 0);
                equipoStmt.executeUpdate();


                // introduce la informacion del jugador en la tabla jugador
                PreparedStatement jugadorStmt = conn.prepareStatement(
                        "INSERT INTO jugador (dni, email, contrasena, dorsal, telefono) VALUES (?, ?, ?, ?, ?)");
                for (JsonElement playerElement : playersArray) {
                    JsonObject player = playerElement.getAsJsonObject();
                    String name = player.get("name").getAsString();
                    String email = player.get("email").getAsString();
                    int number = player.get("number").getAsInt();
                    String dni = player.get("dni").getAsString();
                    String phone = player.get("phone").getAsString();

                    jugadorStmt.setString(1, dni);
                    jugadorStmt.setString(2, email);
                    jugadorStmt.setString(3, "");
                    jugadorStmt.setInt(4, number);
                    jugadorStmt.setString(5, phone);
                    jugadorStmt.executeUpdate();

                    // introduce el jugador en la tabla 'jugador_equipo_liga' para su posterior tratamiento y relacion
                    PreparedStatement jugadorEquipoLigaStmt = conn.prepareStatement(
                            "INSERT INTO jugador_equipo (dni_jugador, nombre_equipo) VALUES (?, ?)");
                    jugadorEquipoLigaStmt.setString(1, dni);
                    jugadorEquipoLigaStmt.setString(2, teamName);
                    jugadorEquipoLigaStmt.executeUpdate();
                }

                // cierra la conexion
                equipoStmt.close();
                jugadorStmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     * Método para obtener la lista de todos los equipos en la base de datos.
     *
     * @return List de objetos Team que representan a los equipos en la base de datos.
     */
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "SELECT * FROM equipo";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()){
                String name = result.getString("nombre");
                int nPlayers = result.getInt("num_jugadores");
                int wins = result.getInt("num_victorias");
                int losses = result.getInt("num_derrotas");
                int ties = result.getInt("num_empates");
                int points = result.getInt("puntos_acumulados");

                teams.add(new Team(name, nPlayers, wins, ties, losses, points));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return teams;
    }




    /**
     * Metodo que cierra la conexion con la base de datos
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: "+e.getSQLState()+"("+e.getMessage() + ")");
        }
    }
}


