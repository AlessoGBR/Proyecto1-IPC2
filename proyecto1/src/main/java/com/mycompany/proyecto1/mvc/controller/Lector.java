/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.ConsultaEtiquetasService;
import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.Revista;
import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.MeGustaRevista;
import com.mycompany.proyecto1.backend.sql.RevistaLector;
import com.mycompany.proyecto1.backend.sql.Suscripciones;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "Lector", urlPatterns = {"/Lector"})
public class Lector extends HttpServlet {

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
        Conexion connection = new Conexion();
        connection.conectar();
        String user = request.getParameter("username");

        RevistaLector revistaSQL = new RevistaLector(Conexion.connection);
        ConsultaEtiquetasService consultaEtiquetasService = new ConsultaEtiquetasService(Conexion.connection);
        MeGustaRevista meGusta = new MeGustaRevista(Conexion.connection);
        Suscripciones suscripciones = new Suscripciones(Conexion.connection);
        List<String> etiquetas;
        List<Revista> revistasAprobadas;
        List<Revista> suscripcionRevistas;

        try {
            etiquetas = consultaEtiquetasService.obtenerEtiquetas();
            request.setAttribute("etiquetas", etiquetas);

            revistasAprobadas = revistaSQL.obtenerRevistasAprobadas();
            suscripcionRevistas = suscripciones.obtenerRevistasSuscritas(user);
            for (Revista revista : revistasAprobadas) {
                int likes = meGusta.cantidadLikes(revista.getIdRevista());
                revista.setLikes(likes);
            }
            for (Revista revista : suscripcionRevistas) {
                int likes = meGusta.cantidadLikes(revista.getIdRevista());
                revista.setLikes(likes);
            }

            request.setAttribute("revistasAprobadas", revistasAprobadas);
            request.setAttribute("suscripcion", suscripcionRevistas);
            request.getRequestDispatcher("jsp/inicio.jsp").forward(request, response);

        } catch (UserDataInvalid ex) {
            Logger.getLogger(CrearRevista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.closeConnection();
        }

    }

}
