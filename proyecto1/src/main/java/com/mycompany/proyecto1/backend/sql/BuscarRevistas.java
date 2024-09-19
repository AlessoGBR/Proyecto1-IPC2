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
public class BuscarRevistas {

    private Connection connection;

    public BuscarRevistas(Connection connection) {
        this.connection = connection;
    }

    public List<Revista> buscarRevistasPorEtiquetas(List<String> etiquetas) throws SQLException {
        List<Revista> revistas = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT DISTINCT r.idRevista, r.revista, r.titulo, r.descripcion, r.no_version ")
                .append("FROM Revista r ")
                .append("JOIN Etiqueta_Revista er ON r.idRevista = er.idRevista ")
                .append("JOIN Etiqueta e ON e.nombre_etiqueta = er.nombre_etiqueta ")
                .append("WHERE e.nombre_etiqueta IN (");

        for (int i = 0; i < etiquetas.size(); i++) {
            query.append("?");
            if (i < etiquetas.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");

        try (Connection conn = Conexion.connection; PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < etiquetas.size(); i++) {
                stmt.setString(i + 1, etiquetas.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Revista revista = new Revista();
                    revista.setIdRevista(rs.getInt("idRevista"));
                    revista.setPathArchivo(rs.getString("revista"));
                    revista.setDescripcion(rs.getString("descripcion"));
                    revista.setTitulo(rs.getString("titulo"));
                    revista.setVersion(rs.getString("no_version"));

                    revistas.add(revista);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistas;
    }
}
