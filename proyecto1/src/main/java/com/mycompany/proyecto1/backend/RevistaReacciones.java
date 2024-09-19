/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

/**
 *
 * @author alesso
 */
public class RevistaReacciones {

    private int idRevista;
    private String tituloRevista;
    private int cantidadReacciones;

    public RevistaReacciones(int idRevista, String tituloRevista, int cantidadReacciones) {
        this.idRevista = idRevista;
        this.tituloRevista = tituloRevista;
        this.cantidadReacciones = cantidadReacciones;
    }

    // Getters y setters
    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public int getCantidadReacciones() {
        return cantidadReacciones;
    }

    public void setCantidadReacciones(int cantidadReacciones) {
        this.cantidadReacciones = cantidadReacciones;
    }
}
