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
public class RevistaLector {

    private Connection connection;

    public RevistaLector(Connection connection) {
        this.connection = connection;
    }

    public List<Revista> obtenerRevistasAprobadas() throws SQLException {
        List<Revista> revistasAprobadas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE aprobada = 1";

        if (connection == null || connection.isClosed()) {
            Conexion conexion = new Conexion();
            conexion.conectar();
            connection = Conexion.connection; 
        }

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(rs.getInt("idRevista"));
                revista.setPathArchivo(rs.getString("revista"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setDescripcion(rs.getString("descripcion"));
                revista.setVersion(rs.getString("no_version"));
                revista.setFecha(rs.getString("fecha"));

                revistasAprobadas.add(revista);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las revistas aprobadas: " + e.getMessage());
        }

        return revistasAprobadas;
    }

}
