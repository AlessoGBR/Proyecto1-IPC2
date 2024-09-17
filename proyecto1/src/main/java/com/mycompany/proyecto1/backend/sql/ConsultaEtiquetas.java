/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

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
public class ConsultaEtiquetas {

    private final Connection connection;

    public ConsultaEtiquetas(Connection connection) {
        this.connection = connection;
    }

    public List<String> obtenerEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        String query = "SELECT nombre_etiqueta FROM Etiqueta";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                etiquetas.add(rs.getString("nombre_etiqueta"));
            }
        } catch (SQLException e) {
            System.out.println("error a la hora de consultar en base");
        }

        return etiquetas;
    }

}
