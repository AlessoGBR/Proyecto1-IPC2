/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import com.mycompany.proyecto1.backend.Exceptions.RevistaDataInvalid;
import java.util.List;

/**
 *
 * @author alesso
 */
public class CrearRevista {

    private final String titulo;
    private final String version;
    private final String fecha;
    private final String pathArchivo;
    private final String descripcion;
    private final boolean esGratuita;
    private final double precio;
    private final boolean comentarios;
    private final boolean meGusta;
    private final boolean subscriptores;
    private final List<String> etiquetas;

    public CrearRevista(String titulo, String version, String fecha, String pathArchivo, String descripcion, boolean esGratuita, double precio, boolean comentarios, boolean meGusta, boolean subscriptores, List<String> etiquetas) {
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

    public Revista crearRevista() throws RevistaDataInvalid {
        validarRevista();
        return new Revista(titulo, version, fecha, pathArchivo, descripcion, esGratuita, precio, comentarios, meGusta, subscriptores, etiquetas);
    }

    public void validarRevista() throws RevistaDataInvalid {
        if (titulo == null || titulo.isEmpty()) {
            throw new RevistaDataInvalid("El titulo no puede estar vacio");
        }
        if (version == null || version.isEmpty()) {
            throw new RevistaDataInvalid("La version no puede estar vacia");
        }
        if (fecha == null) {
            throw new RevistaDataInvalid("La fecha no puede estar vacia");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new RevistaDataInvalid("La descripcion no puede estar vacia");
        }
        if (!esGratuita) {
            if (precio <= 0) {
                throw new RevistaDataInvalid("No puedes tener una revista con precio negativo o cero");
            }
        }
        if (etiquetas == null || etiquetas.isEmpty() || etiquetas.stream().allMatch(String::isEmpty)) {
            throw new RevistaDataInvalid("Debes de seleccionar al menos una etiqueta");
        }
    }
}
