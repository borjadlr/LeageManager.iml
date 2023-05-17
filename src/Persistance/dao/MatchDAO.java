package Persistance.dao;

import Business.Entities.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchDAO {
    private Connection conn; // Variable de conexión a la base de datos

    // Constructor que recibe la conexión a la base de datos
    public MatchDAO(Connection conn) {
        this.conn = conn;
    }

    // Método para crear un registro en la tabla partido
    public void createPartido(Match partido) {
        try {
            // Preparar la consulta SQL
            String query = "INSERT INTO partido (equipo_local, equipo_visitante, resultado) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, partido.getEquipoLocal());
            statement.setString(2, partido.getEquipoVisitante());
            statement.setString(3, partido.getResultado());

            // Ejecutar la consulta
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un registro de la tabla partido
    public Match readPartido(String equipoLocal, String equipoVisitante) {
        Match partido = null;
        try {
            // Preparar la consulta SQL
            String query = "SELECT * FROM partido WHERE equipo_local = ? AND equipo_visitante = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, equipoLocal);
            statement.setString(2, equipoVisitante);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados
            if (resultSet.next()) {
                // Leer los datos de la fila
                String local = resultSet.getString("equipo_local");
                String visitante = resultSet.getString("equipo_visitante");
                String resultado = resultSet.getString("resultado");

                // Crear objeto Match con los datos
                partido = new Match(local, visitante, resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partido;
    }

    // Método para actualizar un registro en la tabla partido
    public void updatePartido(Match partido, String nuevoResultado) {
        try {
            // Preparar la consulta SQL
            String query = "UPDATE partido SET resultado = ? WHERE equipo_local = ? AND equipo_visitante = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoResultado);
            statement.setString(2, partido.getEquipoLocal());
            statement.setString(3, partido.getEquipoVisitante());

            // Ejecutar la consulta
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro de la tabla partido
    public void deletePartido(Match partido) {
        try {
            // Preparar la consulta SQL
            String query = "DELETE FROM partido WHERE equipo_local = ? AND equipo_visitante = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, partido.getEquipoLocal());
            statement.setString(2, partido.getEquipoVisitante());

            // Ejecutar la consulta
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

