/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alesso
 */
public class MeGustaDB {

    private Connection connection;

    public MeGustaDB(Connection connection) {
        this.connection = connection;
    }

    public boolean existeReaccion(String nombreUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Reaccion WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public int insertarReaccion(String nombreUsuario) throws SQLException {
        String sql = "INSERT INTO Reaccion (reacciones, fecha, nombre_usuario) VALUES (?, CURRENT_DATE, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, true); // Asumimos que la reacción es positiva
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se pudo obtener el ID de la reacción.");
    }
    
    

}
