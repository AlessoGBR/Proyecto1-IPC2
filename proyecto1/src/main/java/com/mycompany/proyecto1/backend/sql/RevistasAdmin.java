/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import com.mycompany.proyecto1.backend.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alesso
 */
public class RevistasAdmin {

    private Connection connection;

    public RevistasAdmin(Connection connection) {
        this.connection = connection;
    }

    public List<Revista> obtenerRevistasSinAprobar() throws SQLException {
        String sql = "SELECT * FROM Revista WHERE aprobada = false AND denegada = false";
        List<Revista> revistas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(rs.getInt("idRevista"));
                revista.setPathArchivo(rs.getString("revista"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setDescripcion(rs.getString("descripcion"));
                revista.setVersion(rs.getString("no_version"));
                revista.setAprobada(rs.getBoolean("aprobada"));
                revista.setComentarios(rs.getBoolean("comentarios"));
                revista.setMeGusta(rs.getBoolean("reacciones"));
                revista.setSubscriptores(rs.getBoolean("suscripciones"));
                revistas.add(revista);
            }
        } catch (SQLException e) {

            e.printStackTrace(); // Para desarrollo, considera usar un logger en producción
            throw new SQLException("Error al obtener las revistas no aprobadas", e);
        }

        return revistas;
    }

    public List<Revista> obtenerRevistasDenegadas() throws SQLException {
        String sql = "SELECT * FROM Revista WHERE denegada = true AND aprobada = false";
        List<Revista> revistas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(rs.getInt("idRevista"));
                revista.setPathArchivo(rs.getString("revista"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setDescripcion(rs.getString("descripcion"));
                revista.setVersion(rs.getString("no_version"));
                revista.setAprobada(rs.getBoolean("aprobada"));
                revista.setComentarios(rs.getBoolean("comentarios"));
                revista.setMeGusta(rs.getBoolean("reacciones"));
                revista.setDenegada(rs.getBoolean("denegada"));
                revista.setSubscriptores(rs.getBoolean("suscripciones"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            // Manejo de excepción en caso de errores en la consulta o conexión
            e.printStackTrace(); // Para desarrollo, considera usar un logger en producción
            throw new SQLException("Error al obtener las revistas denegadas", e);
        }

        return revistas;
    }

    public void aprobarRevista(int idRevista) throws SQLException {
        String sql = "UPDATE Revista SET aprobada = true, denegada = false WHERE idRevista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idRevista);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepción en caso de errores en la actualización
            e.printStackTrace(); // Para desarrollo, considera usar un logger en producción
            throw new SQLException("Error al aprobar la revista", e);
        }
    }

    public void denegarRevista(int idRevista) throws SQLException {
        String sql = "UPDATE Revista SET aprobada = false, denegada = true WHERE idRevista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idRevista);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepción en caso de errores en la actualización
            e.printStackTrace(); // Para desarrollo, considera usar un logger en producción
            throw new SQLException("Error al denegar la revista", e);
        }
    }

}
