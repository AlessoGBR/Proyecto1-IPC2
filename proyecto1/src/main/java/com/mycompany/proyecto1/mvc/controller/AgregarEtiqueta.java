/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.NuevaEtiqueta;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "AgregarEtiqueta", urlPatterns = {"/Etiqueta"})
public class AgregarEtiqueta extends HttpServlet {

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
        String etiqueta = request.getParameter("nuevaEtiqueta");
        Conexion conexion = new Conexion();
        conexion.conectar();
        NuevaEtiqueta nuevaEtiqueta = new NuevaEtiqueta(Conexion.connection);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            if (!nuevaEtiqueta.verificarEtiqueta(etiqueta)) {
                nuevaEtiqueta.ingresarEtiqueta(etiqueta);
                out.write("{\"status\":\"success\", \"message\":\"Etiqueta agregada correctamente.\"}");
            } else {
                out.write("{\"status\":\"error\", \"message\":\"La etiqueta ya existe.\"}");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarEtiqueta.class.getName()).log(Level.SEVERE, null, ex);
            out.write("{\"status\":\"error\", \"message\":\"Error al agregar la etiqueta.\"}");
        }
        out.flush();
        Conexion.closeConnection();
    }

}
