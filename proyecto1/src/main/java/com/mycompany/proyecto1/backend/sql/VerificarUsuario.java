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
public class VerificarUsuario {

    private Connection connection;

    public VerificarUsuario(Connection connection) {
        this.connection = connection;
    }

    public boolean verificarUser(String username) {
        // Obtén la conexión a la base de datos

        if (connection == null) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return false;
        }

        String sql = "SELECT COUNT(*) FROM Usuario WHERE nombre_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el usuario existe, retornará true
            } 
        } catch (SQLException e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
        }

        return false; // Si ocurre una excepción o el usuario no existe, retorna false
    }
}
