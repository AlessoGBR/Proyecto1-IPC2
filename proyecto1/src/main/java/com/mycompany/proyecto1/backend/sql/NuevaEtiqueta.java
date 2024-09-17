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
public class NuevaEtiqueta {

    private Connection connection;

    public NuevaEtiqueta(Connection connection) {
        this.connection = connection;
    }

    public boolean verificarEtiqueta(String etiqueta) throws SQLException {
        String sql = "SELECT 1 FROM Etiqueta WHERE nombre_etiqueta = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, etiqueta);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void ingresarEtiqueta(String etiqueta) throws SQLException {
        String sql = "INSERT INTO Etiqueta (nombre_etiqueta) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, etiqueta);
            ps.executeUpdate();
        }
    }

}
