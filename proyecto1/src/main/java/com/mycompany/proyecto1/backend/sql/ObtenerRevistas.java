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
public class ObtenerRevistas {
    
    public ObtenerRevistas() {
        
    }
    
    public List<Revista> obtenerRevistasPorUsuario(String nombreUsuario) {
        List<Revista> revistas = new ArrayList<>();
        Conexion conexion = new Conexion();
        conexion.conectar();
        String query = "SELECT idRevista, revista, titulo, descripcion, no_version, aprobada, comentarios, reacciones, suscripciones FROM Revista "
                + "WHERE nombre_usuario = ?";
        
        try (Connection connection = Conexion.connection; PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Revista revista = new Revista();
                    revista.setIdRevista(rs.getInt("idRevista"));
                    revista.setPathArchivo(rs.getString("revista"));
                    revista.setTitulo(rs.getString("titulo"));
                    revista.setDescripcion(rs.getString("descripcion"));
                    revista.setVersion(rs.getString("no_version"));
                    revista.setAprobada(rs.getBoolean("aprobada"));
                    revista.setComentarios(rs.getBoolean("comentarios"));
                    revista.setMeGusta(rs.getBoolean("reacciones"));
                    revista.setSubscriptores(rs.getBoolean("suscripciones"));
                    
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }
}
