/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.ConsultaEtiquetas;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "MostrarEtiquetas", urlPatterns = {"/MostrarEtiquetas"})
public class MostrarEtiquetas extends HttpServlet {

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
        try (Connection connection = Conexion.connection) {
            ConsultaEtiquetas etiquetaDB = new ConsultaEtiquetas(connection);
            List<String> etiquetas = etiquetaDB.obtenerEtiquetas();
            request.setAttribute("etiquetas", etiquetas);
            String error = (String) request.getSession().getAttribute("error");
            if (error != null) {
                request.setAttribute("error", error);
            }
            request.getRequestDispatcher("jsp/RegistroUsuario.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al obtener etiquetas.");
        }
    }

}
