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
public class Anuncio {

    private Connection connection;

    public Anuncio(Connection connection) {
        this.connection = connection;
    }

    public void crearAnuncioTexto(String texto, int id) throws SQLException {
        String sql = "INSERT INTO Anuncio (tipo, texto, nombre_anunciante) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "TEXTO");
            ps.setString(2, texto);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void crearAnuncioImagenYTexto(String texto, String imagen, int id) throws SQLException {
        String sql = "INSERT INTO Anuncio (tipo, texto, path_imagen, nombre_anunciante) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "IMAGEN Y TEXTO");
            ps.setString(2, texto);
            ps.setString(3, imagen);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public void crearAnuncioVideo(String url, int id) throws SQLException {
        String sql = "INSERT INTO Anuncio (tipo, url_video, nombre_anunciante) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "VIDEO");
            ps.setString(2, url);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public int obtenerIdAnunciantePorNombre(Connection connection, String nombreAnunciante) throws SQLException {
        String sql = "SELECT idAnunciante FROM Anunciante WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreAnunciante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idAnunciante");
                } else {
                    throw new SQLException("Anunciante no encontrado.");
                }
            }
        }
    }

}
