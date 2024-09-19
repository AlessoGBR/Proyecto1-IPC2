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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "CargarCartera", urlPatterns = {"/CargarCartera"})
public class CargarCartera extends HttpServlet {

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
        String cantidadStr = request.getParameter("cantidad");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (cantidadStr == null || cantidadStr.trim().isEmpty()) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"La cantidad a recargar no puede estar vacía.\"}");
            return;
        }

        try {
            double cantidad = Double.parseDouble(cantidadStr);

            Conexion conexion = new Conexion();
            conexion.conectar();

            try (Connection connection = Conexion.connection) {
                Cartera carteraDAO = new Cartera(connection);
                double saldoActual = carteraDAO.obtenerCartera(connection, user);

                // Actualizamos el saldo sumando la cantidad recargada
                carteraDAO.actualizarSaldo(user, saldoActual + cantidad);

                // Respuesta en formato JSON
                response.getWriter().write("{\"status\": \"success\", \"message\": \"Saldo agregado con éxito\", \"nuevoSaldo\": " + (saldoActual + cantidad) + "}");

            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"status\": \"error\", \"message\": \"Error al agregar saldo a la cartera: " + e.getMessage() + "\"}");
            } finally {
                Conexion.closeConnection();
            }
        } catch (NumberFormatException e) {
            // Respuesta de error si la cantidad no es un número válido
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": \"error\", \"message\": \"La cantidad ingresada no es válida.\"}");
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
    }

}
