/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import static com.mycompany.proyecto1.backend.sql.Conexion.connection;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
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
public class InicioSesionDB {

    private String username;
    private String password;

    public InicioSesionDB(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String desencriptarPass(String password, String clave) {
        String pass = "";
        try {
            byte[] entrada = Base64.getDecoder().decode(password.getBytes("utf-8"));
            MessageDigest tipoEncriptacion = MessageDigest.getInstance("MD5");
            byte[] claveProcesada = tipoEncriptacion.digest(clave.getBytes("utf-8"));
            byte[] llaveByte = Arrays.copyOf(claveProcesada, 24);
            SecretKey llave = new SecretKeySpec(llaveByte, "DESede");
            Cipher descifrar = Cipher.getInstance("DESede");
            descifrar.init(Cipher.DECRYPT_MODE, llave);
            byte[] textoPlano = descifrar.doFinal(entrada);
            pass = new String(textoPlano, "UTF-8");

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return pass;
    }

    public String verificarUser(String username) {
        if (connection == null) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return null;
        }

        String sql = "SELECT password FROM Usuario WHERE nombre_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password"); // Retorna la contraseña si el usuario existe
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
        }

        return null; // Retorna null si el usuario no existe o ocurre una excepción
    }

    public Integer verificarTipoUser(String username) {
        if (connection == null) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return null;
        }

        String sql = "SELECT idTipoUsuario FROM Usuario WHERE nombre_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idTipoUsuario"); // Retorna el tipo de usuario como un entero
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el tipo de usuario: " + e.getMessage());
        }

        return null; // Retorna null si el usuario no existe o ocurre una excepción
    }

}
