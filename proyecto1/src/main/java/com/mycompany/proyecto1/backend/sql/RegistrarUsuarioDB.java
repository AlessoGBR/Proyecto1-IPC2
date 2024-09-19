/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import com.mycompany.proyecto1.backend.TipoUsuarioEnum;
import com.mycompany.proyecto1.backend.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author alesso
 */
public class RegistrarUsuarioDB {

    public RegistrarUsuarioDB() {

    }

    private boolean usuarioExiste(Connection connection, String nombreUsuario) throws SQLException {
        String sql = "SELECT 1 FROM Usuario WHERE nombre_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void insertarUsuario(Connection connection, Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre_usuario, password, idTipoUsuario) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, encriptarPass(usuario.getPassword()));
            ps.setInt(3, convertirTipoUsuarioEnum(usuario.getUserType()));
            ps.executeUpdate();
        }
    }

    public boolean registrarUsuarioYPerfil(Usuario usuario) {
        Connection connection = null;
        PreparedStatement psPerfil = null;

        try {
            connection = Conexion.connection;
            connection.setAutoCommit(false);
            boolean existe = usuarioExiste(connection, usuario.getUsername());
            if (!existe) {
                insertarUsuario(connection, usuario);
                if (usuario.getUserType() == TipoUsuarioEnum.ANUNCIANTE) {
                    ingresoAnunciante(connection, usuario.getUsername());
                }
            }

            String sqlPerfil = "INSERT INTO Perfil (fotoPerfil, descripcion, nombre_usuario) VALUES (?, ?, ?)";
            psPerfil = connection.prepareStatement(sqlPerfil);
            psPerfil.setString(1, usuario.getFoto());
            psPerfil.setString(2, usuario.getDescripcion());
            psPerfil.setString(3, usuario.getUsername());

            psPerfil.executeUpdate();

            ingresarEtiquetas(connection, obtenerIdPerfil(connection, usuario.getUsername()), usuario.getUsername(), usuario.getTags());

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (psPerfil != null) {
                    psPerfil.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return false;
    }

    private int convertirTipoUsuarioEnum(TipoUsuarioEnum tipoUsuario) throws SQLException {
        switch (tipoUsuario) {
            case ADMINISTRADOR:
                return 1;
            case LECTOR:
                return 2;
            case EDITOR:
                return 3;
            case ANUNCIANTE:
                return 4;
            default:
                throw new SQLException("Tipo de usuario desconocido.");
        }
    }

    public String encriptarPass(String password) {
        String nuevoPass = "";
        String clave = "ipc2";
        try {
            MessageDigest tipoEncriptacion = MessageDigest.getInstance("MD5");
            byte[] llaveContra = tipoEncriptacion.digest(clave.getBytes("utf-8"));
            byte[] llaveByte = Arrays.copyOf(llaveContra, 24);
            SecretKey llave = new SecretKeySpec(llaveByte, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, llave);
            byte[] textoPlano = password.getBytes("utf-8");
            byte[] buffer = cifrado.doFinal(textoPlano);
            byte[] base64Bytes = Base64.getEncoder().encode(buffer);
            nuevoPass = new String(base64Bytes);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return nuevoPass;
    }

    private int obtenerIdPerfil(Connection connection, String nombreUsuario) throws SQLException {
        String sql = "SELECT idPerfil FROM Perfil WHERE nombre_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idPerfil");
                } else {
                    throw new SQLException("No se encontró el perfil para el nombre de usuario proporcionado.");
                }
            }
        }
    }

    private void ingresarEtiquetas(Connection connection, int idPerfil, String nombreUsuario, List<String> etiquetas) throws SQLException {
        String sqlInsertRel = "INSERT INTO Perfil_Etiquetas (nombre_etiqueta,idPerfil,nombre_usuario ) VALUES (?, ?, ?)";

        try (PreparedStatement psInsertRel = connection.prepareStatement(sqlInsertRel)) {
            for (String etiqueta : etiquetas) {
                psInsertRel.setString(1, etiqueta);
                psInsertRel.setInt(2, idPerfil);
                psInsertRel.setString(3, nombreUsuario);

                psInsertRel.addBatch();
            }
            psInsertRel.executeBatch();
        }
    }

    private void ingresoAnunciante(Connection connection, String nombreUsuario) throws SQLException {
        String sql = "INSERT INTO Anunciante (cartera, nombre_usuario) VALUES (0, ?)";
        PreparedStatement psPerfil = null;

        try {
            // Inicializa el PreparedStatement
            psPerfil = connection.prepareStatement(sql);

            // Establece el parámetro
            psPerfil.setString(1, nombreUsuario); // nombre_usuario

            // Ejecuta la inserción
            psPerfil.executeUpdate();

        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();  // Puedes reemplazar esto con un logger
            throw new SQLException("Error al insertar el anunciante", e);

        } finally {
            // Cierra el PreparedStatement
            if (psPerfil != null) {
                try {
                    psPerfil.close();
                } catch (SQLException e) {
                    e.printStackTrace();  // Manejo de cierre de recursos
                }
            }
        }
    }

}
