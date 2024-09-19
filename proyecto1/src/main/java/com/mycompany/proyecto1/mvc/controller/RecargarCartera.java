/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Cartera;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "RecargarCartera", urlPatterns = {"/Recargar"})
public class RecargarCartera extends HttpServlet {

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
        String user = request.getParameter("username");

        if (user != null) {
            Conexion conexion = new Conexion();
            conexion.conectar();
            request.setAttribute("username", user);
            Cartera cartera = new Cartera(Conexion.connection);
            double carteraUser;
            try {
                carteraUser = cartera.obtenerCartera(Conexion.connection, user);
                request.setAttribute("cartera", carteraUser);
            } catch (SQLException ex) {
                Logger.getLogger(RecargarCartera.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("jsp/Recargar.jsp").forward(request, response);
        } else {
            // Manejo de error si el parámetro es nulo
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre de usuario es requerido.");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
