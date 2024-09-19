/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.MeGustaDB;
import com.mycompany.proyecto1.backend.sql.MeGustaRevista;
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
@WebServlet(name = "MeGusta", urlPatterns = {"/MeGusta"})
public class MeGusta extends HttpServlet {

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
        String idRevista = request.getParameter("idRevista");
        String nombreUsuario = request.getParameter("username");
        Conexion conexion = new Conexion();
        conexion.conectar();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            MeGustaDB reaccionDAO = new MeGustaDB(Conexion.connection);
            MeGustaRevista reaccionRevistaDAO = new MeGustaRevista(Conexion.connection);

            boolean reaccionExistente = reaccionDAO.existeReaccion(nombreUsuario);

            if (!reaccionExistente) {
                int idReaccion = reaccionDAO.insertarReaccion(nombreUsuario);
                reaccionRevistaDAO.insertarReaccionRevista(idReaccion, idRevista, nombreUsuario);
                out.write("{\"status\":\"success\", \"message\":\"Me gusta agregado.\"}");
            } else {
                out.write("{\"status\":\"error\", \"message\":\"Ya has reaccionado a esta revista.\"}");
            }

        } catch (SQLException e) {
            out.write("{\"status\":\"error\", \"message\":\"No se pudo registrar la reacción.\"}");
        } finally {
            Conexion.closeConnection();
            out.flush();
        }
    }

}
