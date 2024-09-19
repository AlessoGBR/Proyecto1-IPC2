/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alesso
 */
public class MeGustaRevista {

    private Connection connection;

    public MeGustaRevista(Connection connection) {
        this.connection = connection;
    }

    public void insertarReaccionRevista(int idReaccion, String idRevista, String nombreUsuario) throws SQLException {
        String sql = "INSERT INTO Reaccion_Revista (idReaccion, idRevista, nombre_usuario, fecha) VALUES (?, ?, ?, CURRENT_DATE)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idReaccion);
            stmt.setString(2, idRevista);
            stmt.setString(3, nombreUsuario);

            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar la reacción en Reaccion_Revista: " + e.getMessage());
        }
    }

    public int cantidadLikes(int idRevista) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Reaccion_Revista WHERE idRevista = ?";
        if (connection == null || connection.isClosed()) {
            Conexion conexion = new Conexion();
            conexion.conectar();
            connection = Conexion.connection;
        }
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idRevista);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int likes = rs.getInt(1);
                return likes;
            } else {
                return 0;
            }
        }
    }
}
