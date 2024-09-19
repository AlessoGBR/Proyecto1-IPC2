/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.itextpdf.text.DocumentException;
import com.mycompany.proyecto1.backend.ComentarioRevista;
import com.mycompany.proyecto1.backend.GenerarReporteEditor;
import com.mycompany.proyecto1.backend.RevistaReacciones;
import com.mycompany.proyecto1.backend.SuscriptorRevista;
import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.ReporteRevistaSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "GenerarReporte", urlPatterns = {"/GenerarReporte"})
public class GenerarReporte extends HttpServlet {

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
        String nombreUsuario = request.getParameter("username");
        String reporte = request.getParameter("reporte");

        Conexion conexion = new Conexion();
        conexion.conectar();
        ReporteRevistaSQL obtenerRevistas = new ReporteRevistaSQL(conexion);

        try {
            GenerarReporteEditor generarReporte = new GenerarReporteEditor();
            if ("comentarios".equals(reporte)) {
                List<ComentarioRevista> comentariosRevistas = obtenerRevistas.obtenerRevistasPorUsuario(nombreUsuario);
                generarReporte.crearPDF(response.getOutputStream(), comentariosRevistas);
            } else if ("suscripciones".equals(reporte)) {
                List<SuscriptorRevista> suscripcionesRevistas = obtenerRevistas.obtenerSuscripcionesPorUsuario(nombreUsuario);
                generarReporte.crearPDFSuscripcion(response.getOutputStream(), suscripcionesRevistas);
            } else if ("likes".equals(reporte)) {
                List<RevistaReacciones> RevistaReacciones = obtenerRevistas.obtenerTop5RevistasPorReacciones();
                generarReporte.crearPDFTopRevistas(response.getOutputStream(), RevistaReacciones);
            }
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");
        } catch (DocumentException e) {
            throw new ServletException("Error al generar el PDF", e);
        } finally {
            Conexion.closeConnection();

        }
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

    }

}
