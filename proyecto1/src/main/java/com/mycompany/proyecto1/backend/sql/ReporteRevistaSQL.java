/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import com.mycompany.proyecto1.backend.ComentarioRevista;
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
public class ReporteRevistaSQL {

    private final Conexion conexion;

    public ReporteRevistaSQL(Conexion conexion) {
        this.conexion = conexion;
    }

    public List<ComentarioRevista> obtenerRevistasPorUsuario(String nombreUsuarioCreador) {
        List<ComentarioRevista> comentariosRevistas = new ArrayList<>();

        String query = "SELECT r.idRevista, r.titulo, r.fecha, r.no_version, c.idComentario, c.comentario, cr.fecha, cr.nombre_usuario "
                + "FROM Comentario c "
                + "JOIN Comentario_Revista cr ON c.idComentario = cr.idComentario "
                + "JOIN Revista r ON r.idRevista = cr.idRevista "
                + "WHERE r.nombre_usuario = ? AND r.aprobada = TRUE";  // Filtrar por revistas aprobadas

        try (Connection conn = Conexion.connection; PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuarioCreador);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idRevista = rs.getInt("idRevista");
                    String tituloRevista = rs.getString("titulo");
                    Date fecha = rs.getDate("fecha");
                    String version = rs.getString("no_version");
                    int idComentario = rs.getInt("idComentario");
                    String comentario = rs.getString("comentario");
                    String nombreUsuarioComentario = rs.getString("nombre_usuario");
                    Date fechaComentario = rs.getDate("fecha");

                    // Crea un objeto ComentarioRevista para cada registro
                    ComentarioRevista comentarioRevista = new ComentarioRevista(
                            idRevista,
                            tituloRevista,
                            fecha,
                            version,
                            idComentario,
                            fechaComentario,
                            comentario,
                            nombreUsuarioComentario
                    );

                    comentariosRevistas.add(comentarioRevista);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentariosRevistas;
    }
}
