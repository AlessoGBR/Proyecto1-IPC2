/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.sql.ConsultaEtiquetas;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author alesso
 */
public class ConsultaEtiquetasService {

    private Connection connection;

    public ConsultaEtiquetasService(Connection connection) {
        this.connection = connection;
    }

    public List<String> obtenerEtiquetas() throws UserDataInvalid {
        ConsultaEtiquetas etiquetaDB = new ConsultaEtiquetas(connection);
        return etiquetaDB.obtenerEtiquetas();
    }

}
