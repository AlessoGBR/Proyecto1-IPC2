/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.SuscriptorNuevo;
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
@WebServlet(name = "Suscribirse", urlPatterns = {"/Suscribirse"})
public class Suscribirse extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombreUsuario = request.getParameter("username");
        String idRevistaStr = request.getParameter("idRevista");
        System.out.println(nombreUsuario);
        System.out.println(idRevistaStr);

        int idRevista;
        try {
            idRevista = Integer.parseInt(idRevistaStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de revista inválido.");
            return;
        }

        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            SuscriptorNuevo suscripcionRevista = new SuscriptorNuevo(Conexion.connection);

            if (!suscripcionRevista.verificarSuscripcion(nombreUsuario, idRevista)) {
                suscripcionRevista.ingresoSuscriptor(nombreUsuario, idRevista);
                out.write("{\"status\":\"success\", \"message\":\"Te has suscrito a la revista.\"}");
            } else {
                // El usuario ya está suscrito
                out.write("{\"status\":\"error\", \"message\":\"Ya estás suscrito a la revista.\"}");
            }
        } catch (SQLException e) {
            out.write("{\"status\":\"error\", \"message\":\"No se pudo suscribir a la revista: " + e.getMessage() + "\"}");
        } finally {
            Conexion.closeConnection();
            out.flush();
        }
    }

}
