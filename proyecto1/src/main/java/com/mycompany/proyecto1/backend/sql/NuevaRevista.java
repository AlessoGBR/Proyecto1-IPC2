/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import com.mycompany.proyecto1.backend.Exceptions.RevistaDataInvalid;
import com.mycompany.proyecto1.backend.Revista;
import static com.mycompany.proyecto1.backend.sql.Conexion.connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author alesso
 */
public class NuevaRevista {

    public NuevaRevista() {

    }

    public boolean insertarRevista(Revista revista, String nombreUsuario) throws RevistaDataInvalid, SQLException, ParseException {
        if (revista == null || nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new RevistaDataInvalid("Datos de la revista o el nombre de usuario son inválidos.");
        }

        String insertSQL = "INSERT INTO Revista (revista, titulo, descripcion, no_version, aprobada, suscripciones, comentarios, reacciones, fecha, nombre_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        try {
            connection = Conexion.connection;
            connection.setAutoCommit(false);

            try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
                String fechaLocal = revista.getFecha();
                Date fechaSql = Date.valueOf(fechaLocal);
                System.out.println(revista.getPathArchivo());
                stmt.setString(1, revista.getPathArchivo());
                stmt.setString(2, revista.getTitulo());
                stmt.setString(3, revista.getDescripcion());
                stmt.setString(4, revista.getVersion());
                stmt.setBoolean(5, false);
                stmt.setBoolean(6, revista.isSubscriptores());
                stmt.setBoolean(7, revista.isComentarios());
                stmt.setBoolean(8, revista.isMeGusta());
                stmt.setDate(9, fechaSql);
                stmt.setString(10, nombreUsuario);

                stmt.executeUpdate();
            }

            int idRevista = obtenerIdRevista(connection, revista.getTitulo());
            ingresarEtiquetas(connection, idRevista, revista.getEtiquetas());

            connection.commit(); 
            return true;

        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de error
                } catch (SQLException rollbackEx) {
                    throw new SQLException("Error al deshacer la transacción: " + rollbackEx.getMessage(), rollbackEx);
                }
            }
            throw new SQLException("Error al insertar la revista: " + ex.getMessage(), ex);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restablecer el auto-commit
                } catch (SQLException e) {
                    // Manejar excepción si ocurre un error al restablecer el auto-commit
                }
            }
        }
    }

    public void ingresarEtiquetas(Connection connection, int idRevista, List<String> etiquetas) throws SQLException {
        String sqlInsertRel = "INSERT INTO Etiqueta_Revista (nombre_etiqueta,idRevista) VALUES (?, ?)";

        try (PreparedStatement psInsertRel = connection.prepareStatement(sqlInsertRel)) {
            for (String etiqueta : etiquetas) {
                psInsertRel.setString(1, etiqueta);
                psInsertRel.setInt(2, idRevista);

                psInsertRel.addBatch();
            }
            psInsertRel.executeBatch();
        }
    }

    public boolean verificarUser(String username) {

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

    public int obtenerIdRevista(Connection connection, String nombreRevista) throws SQLException {
        String sql = "SELECT idRevista FROM Revista WHERE titulo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreRevista);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idRevista");
                } else {
                    throw new SQLException("No se encontró el perfil para el nombre de usuario proporcionado.");
                }
            }
        }
    }

    public Date convertStringToSqlDate(String fechaLocal) throws RevistaDataInvalid {
        if (fechaLocal == null || fechaLocal.isEmpty()) {
            throw new RevistaDataInvalid("La fecha de la revista no puede ser nula o vacía.");
        }

        try {
            // Asegúrate de que la fecha está en formato yyyy-MM-dd
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            java.util.Date parsedDate = formatter.parse(fechaLocal);
            return new Date(parsedDate.getTime()); // Convertir java.util.Date a java.sql.Date
        } catch (ParseException e) {
            throw new RevistaDataInvalid("El formato de la fecha es incorrecto. Debe ser yyyy-MM-dd.");
        }
    }

}
