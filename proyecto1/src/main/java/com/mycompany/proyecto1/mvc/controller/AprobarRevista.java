/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.RevistasAdmin;
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
@WebServlet(name = "AprobarRevista", urlPatterns = {"/AprobarRevista"})
public class AprobarRevista extends HttpServlet {

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
        String idRevistaStr = request.getParameter("idRevista");
        int idRevista = Integer.parseInt(idRevistaStr);

        Conexion conexion = new Conexion();
        conexion.conectar();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            RevistasAdmin revistaDAO = new RevistasAdmin(Conexion.connection);
            revistaDAO.aprobarRevista(idRevista);
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Revista aprobada exitosamente\"}");
        } catch (SQLException e) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Error al aprobar la revista\"}");
        } finally {
            Conexion.closeConnection();
        }
    }

}
