/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.Revista;
import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.ObtenerRevistas;
import com.mycompany.proyecto1.backend.sql.RevistasAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class Admin extends HttpServlet {

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
        Conexion conexion = new Conexion();
        conexion.conectar();

        try {
            RevistasAdmin obtener = new RevistasAdmin(Conexion.connection);
            List<Revista> revistas = obtener.obtenerRevistasSinAprobar();
            request.setAttribute("revistas", revistas);
            // Redirigir a la JSP con las revistas sin procesar
            List<Revista> revistasDenegadas = obtener.obtenerRevistasDenegadas();
            request.setAttribute("revistasDenegadas", revistasDenegadas);

            request.getRequestDispatcher("jsp/inicioAdmin.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener revistas sin aprobar", e);
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
