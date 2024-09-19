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
public class Cartera {

    private final Connection connection;

    public Cartera(Connection connection) {
        this.connection = connection;
    }

    public double obtenerCartera(Connection connection, String nombreAnunciante) throws SQLException {
        String sql = "SELECT cartera FROM Anunciante WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreAnunciante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cartera");
                } else {
                    throw new SQLException("Anunciante no encontrado.");
                }
            }
        }
    }

    public void actualizarSaldo(String nombreUsuario, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE Anunciante SET cartera = ? WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoSaldo);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        }
    }

}
