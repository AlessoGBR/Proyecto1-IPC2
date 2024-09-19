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
public class ComentarioRevista {

    private Connection connection;

    public ComentarioRevista(Connection connection) {
        this.connection = connection;
    }

    public void insertarComentario(String comentario, String nombreUsuario, int idRevista) throws SQLException {
        String sqlComentario = "INSERT INTO Comentario (comentario, nombre_usuario, fecha) VALUES (?, ?, CURRENT_DATE)";
        String sqlComentarioRevista = "INSERT INTO Comentario_Revista (idComentario, nombre_usuario, idRevista, fecha) VALUES (?, ?, ?, CURRENT_DATE)";

        try (PreparedStatement stmtComentario = connection.prepareStatement(sqlComentario, Statement.RETURN_GENERATED_KEYS); PreparedStatement stmtComentarioRevista = connection.prepareStatement(sqlComentarioRevista)) {

            // Insertar comentario
            stmtComentario.setString(1, comentario);
            stmtComentario.setString(2, nombreUsuario);
            int rowsAffected = stmtComentario.executeUpdate();

            // Obtener el ID del comentario insertado
            ResultSet generatedKeys = stmtComentario.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idComentario = generatedKeys.getInt(1);

                // Insertar en Comentario_Revista
                stmtComentarioRevista.setInt(1, idComentario);
                stmtComentarioRevista.setString(2, nombreUsuario);
                stmtComentarioRevista.setInt(3, idRevista);
                stmtComentarioRevista.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el comentario en Comentario_Revista: " + e.getMessage());
            throw e; // Lanza la excepción para que el servlet pueda manejarla
        }
    }
}
