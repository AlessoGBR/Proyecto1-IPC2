/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.ComentarioRevista;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author alesso
 */
@WebServlet(name = "Comentar", urlPatterns = {"/Comentar"})
public class Comentar extends HttpServlet {

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
        String comentario = request.getParameter("comentario");
        String nombreUsuario = request.getParameter("username");
        String idRevistaStr = request.getParameter("idRevista");

        // Validar parámetros
        if (comentario == null || nombreUsuario == null || idRevistaStr == null || comentario.isEmpty() || nombreUsuario.isEmpty() || idRevistaStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros faltantes o inválidos.");
            return;
        }

        int idRevista;
        try {
            idRevista = Integer.parseInt(idRevistaStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de revista inválido.");
            return;
        }

        Conexion conexion = new Conexion();
        conexion.conectar();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            ComentarioRevista comentarioRevista = new ComentarioRevista(Conexion.connection);
            comentarioRevista.insertarComentario(comentario, nombreUsuario, idRevista);
            out.write("{\"status\":\"success\", \"message\":\"Comentario agregado.\"}");
        } catch (SQLException e) {
            out.write("{\"status\":\"error\", \"message\":\"No se pudo comentar. Detalles: " + e.getMessage() + "\"}");
        } finally {
            Conexion.closeConnection();
            out.flush();
        }
    }

}
