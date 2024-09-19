/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import static com.mycompany.proyecto1.backend.sql.Conexion.connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alesso
 */
public class AnunciosSQL {

    private Connection connection;

    public AnunciosSQL(Connection connection) {
        this.connection = connection;
    }

    public List<Anuncio> obtenerAnunciosPendientes() {
        List<Anuncio> anuncios = new ArrayList<>();
        String query = "SELECT idAnuncio, texto, url_video, path_imagen, activo, fecha_inicio, fecha_final, pago, nombre_anunciante, tipo "
                + "FROM Anuncio "
                + "WHERE activo = false";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Anuncio anuncio = new Anuncio(
                        rs.getInt("idAnuncio"),
                        rs.getString("texto"),
                        rs.getString("url_video"),
                        rs.getString("path_imagen"),
                        rs.getBoolean("activo"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_final"),
                        rs.getDouble("pago"),
                        rs.getString("nombre_anunciante"),
                        rs.getString("tipo")
                );

                anuncios.add(anuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anuncios;
    }

    public void aprobarAnuncio(int idAnuncio, String fechaInicio, String fechaFinal, double pago) {
        String query = "UPDATE Anuncios SET activo = true, fecha_inicio = ?, fecha_final = ?, pago = ? WHERE idAnuncio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(fechaInicio));
            stmt.setDate(2, Date.valueOf(fechaFinal));
            stmt.setDouble(3, pago);
            stmt.setInt(4, idAnuncio);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
