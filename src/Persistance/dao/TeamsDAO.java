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


    public List<Team> getAllTeams() throws SQLException {
        List<Team> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                int numJugadores = rs.getInt("num_jugadores");
                int numVictorias = rs.getInt("num_victorias");
                int numDerrotas = rs.getInt("num_derrotas");
                int numEmpates = rs.getInt("num_empates");
                int puntosAcumulados = rs.getInt("puntos_acumulados");

                Team equipo = new Team( nombre, numJugadores, numVictorias, numDerrotas, numEmpates, puntosAcumulados);
                equipos.add(equipo);
            }
        }

        return equipos;
    }

    @Override


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


