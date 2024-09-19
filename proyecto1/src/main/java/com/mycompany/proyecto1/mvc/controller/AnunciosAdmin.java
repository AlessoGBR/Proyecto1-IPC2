/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Anuncio;
import com.mycompany.proyecto1.backend.sql.AnunciosSQL;
import com.mycompany.proyecto1.backend.sql.Conexion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "AnunciosAdmin", urlPatterns = {"/AnunciosAdmin"})
public class AnunciosAdmin extends HttpServlet {

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
        List<Anuncio> anuncios = new ArrayList<>();
        Connection conn = null;

        // Establecer conexión
        Conexion conexion = new Conexion();
        conexion.conectar();
        conn = Conexion.connection;
        AnunciosSQL anuncioDAO = new AnunciosSQL(conn);
        anuncios = anuncioDAO.obtenerAnunciosPendientes();
        request.setAttribute("anuncios", anuncios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AnuncioAdmin.jsp");
        dispatcher.forward(request, response);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo del error al cerrar la conexión
            }
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
