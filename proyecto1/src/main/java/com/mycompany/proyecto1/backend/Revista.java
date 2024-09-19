/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import java.util.List;

/**
 *
 * @author alesso
 */
public class Revista {

    private int idRevista;
    private int likes;
    private String titulo;
    private String version;
    private String fecha;
    private String pathArchivo;
    private String descripcion;
    private boolean aprobada;
    private boolean esGratuita;
    private double precio;
    private boolean comentarios;
    private boolean meGusta;
    private boolean subscriptores;
    private boolean denegada;
    private List<String> etiquetas;

    public Revista(String titulo, String version, String fecha, String pathArchivo, String descripcion, boolean esGratuita, double precio,
            boolean comentarios, boolean meGusta, boolean subscriptores, List<String> etiquetas) {
        this.titulo = titulo;
        this.version = version;
        this.fecha = fecha;
        this.pathArchivo = pathArchivo;
        this.descripcion = descripcion;
        this.esGratuita = esGratuita;
        this.precio = precio;
        this.comentarios = comentarios;
        this.meGusta = meGusta;
        this.subscriptores = subscriptores;
        this.etiquetas = etiquetas;
    }

    public Revista() {

    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

    public boolean isEsGratuita() {
        return esGratuita;
    }

    public void setEsGratuita(boolean esGratuita) {
        this.esGratuita = esGratuita;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isComentarios() {
        return comentarios;
    }

    public void setComentarios(boolean comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public boolean isSubscriptores() {
        return subscriptores;
    }

    public void setSubscriptores(boolean subscriptores) {
        this.subscriptores = subscriptores;
    }

    public boolean isDenegada() {
        return denegada;
    }

    public void setDenegada(boolean denegada) {
        this.denegada = denegada;
    }    
    
    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

}
