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
public class SuscriptorNuevo {

    private final Connection connection;

    public SuscriptorNuevo(Connection connection) {
        this.connection = connection;
    }

    public void ingresoSuscriptor(String username, int idRevista) throws SQLException {
        PreparedStatement stmtComentario = null;

        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO Suscripcion (fecha, nombre_usuario, idRevista) VALUES (CURRENT_DATE, ?, ?)";
            stmtComentario = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmtComentario.setString(1, username);
            stmtComentario.setInt(2, idRevista);
            int rowsAffected = stmtComentario.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit();
            } else {
                connection.rollback();
                throw new SQLException("No se pudo insertar la suscripción.");
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Error al insertar la suscripción: " + e.getMessage());
            throw e;

        } finally {
            if (stmtComentario != null) {
                try {
                    stmtComentario.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public boolean verificarSuscripcion(String username, int idRevista) {
        String sql = "SELECT COUNT(*) FROM Suscripcion WHERE nombre_usuario = ? AND idRevista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, idRevista);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la suscripción: " + e.getMessage());
        }
        return false;
    }

}
