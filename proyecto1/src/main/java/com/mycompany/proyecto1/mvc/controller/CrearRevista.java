/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.ConsultaEtiquetasService;
import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "CrearRevista", urlPatterns = {"/CrearRevista"})
public class CrearRevista extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion conexion = new Conexion();
        conexion.conectar();
        ConsultaEtiquetasService consultaEtiquetasService = new ConsultaEtiquetasService(Conexion.connection);
        List<String> etiquetas;
        try {
            etiquetas = consultaEtiquetasService.obtenerEtiquetas();
            request.setAttribute("etiquetas", etiquetas);
            request.getRequestDispatcher("jsp/CrearRevistas.jsp").forward(request, response);
        } catch (UserDataInvalid ex) {
            Logger.getLogger(CrearRevista.class.getName()).log(Level.SEVERE, null, ex);

        }
        Conexion.closeConnection();

    }

}
