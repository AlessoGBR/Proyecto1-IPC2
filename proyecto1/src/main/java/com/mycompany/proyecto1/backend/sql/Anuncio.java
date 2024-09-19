/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alesso
 */
public class Anuncio {

    private Connection connection;
    private int idAnuncio;
    private String texto;
    private String urlVideo;
    private String pathImagen;
    private boolean activo;
    private Date fechaInicio;
    private Date fechaFinal;
    private double pago;
    private String nombreAnunciante;
    private String tipo;

    public Anuncio(Connection connection) {
        this.connection = connection;
    }

    Anuncio(int idAnuncio, String texto, String urlVideo, String pathImagen, boolean activo, Date fechaInicio, Date fechaFinal, double pago, String nombreAnunciante, String tipo) {
        this.idAnuncio = idAnuncio;
        this.texto = texto;
        this.urlVideo = urlVideo;
        this.pathImagen = pathImagen;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.pago = pago;
        this.nombreAnunciante = nombreAnunciante;
        this.tipo = tipo;
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
