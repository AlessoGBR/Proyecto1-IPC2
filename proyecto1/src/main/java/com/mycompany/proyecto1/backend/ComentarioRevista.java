/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend; 
import java.sql.Date;

/**
 *
 * @author alesso
 */
public class ComentarioRevista {

    private Integer idRevista;
    private String tituloRevista;
    private Date fechaPublicacion;
    private String version;
    private Integer idComentario;
    private Date fechaComentario;
    private String textoComentario;
    private String nombreUsuario;

    public ComentarioRevista(Integer idRevista, String tituloRevista, Date fechaPublicacion, String version, Integer idComentario, 
            Date fechaComentario, String textoComentario, String nombreUsuario) {
        this.idRevista = idRevista;
        this.tituloRevista = tituloRevista;
        this.fechaPublicacion = fechaPublicacion;
        this.version = version;
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.textoComentario = textoComentario;
        this.nombreUsuario = nombreUsuario;
    }    

    public Integer getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Integer idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

 
}
