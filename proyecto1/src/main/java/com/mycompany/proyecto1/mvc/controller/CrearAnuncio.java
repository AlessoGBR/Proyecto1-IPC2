/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.GuardarImagen;
import com.mycompany.proyecto1.backend.sql.Anuncio;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.SQLException;

/**
 *
 * @author alesso
 */
@WebServlet(name = "CrearAnuncio", urlPatterns = {"/CrearAnuncio"})
@MultipartConfig
public class CrearAnuncio extends HttpServlet {

    private String fileName;

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
        String tipoAnuncio = request.getParameter("userType");
        String textoAnuncio = request.getParameter("textAnuncio");
        String urlAnuncio = request.getParameter("urlAnuncio");
        Part filePart = request.getPart("fileUpload");
        Conexion conexion = new Conexion();
        conexion.conectar();
        try {

            Anuncio anuncioDAO = new Anuncio(Conexion.connection);
            int idAnunciante = anuncioDAO.obtenerIdAnunciantePorNombre(Conexion.connection, user);
            if ("TEXTO".equals(tipoAnuncio)) {
                anuncioDAO.crearAnuncioTexto(textoAnuncio, idAnunciante);
            } else if ("IMAGEN Y TEXTO".equals(tipoAnuncio)) {
                GuardarImagen guardarImagen = new GuardarImagen();
                try {
                    fileName = guardarImagen.guardarImagen(filePart);

                } catch (IllegalArgumentException e) {

                }
                anuncioDAO.crearAnuncioImagenYTexto(textoAnuncio, fileName, idAnunciante);
            } else if ("VIDEO".equals(tipoAnuncio)) {
                anuncioDAO.crearAnuncioVideo(urlAnuncio, idAnunciante);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Anuncio creado con éxito\"}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Error al crear el anuncio\"}");
        } finally {
            Conexion.closeConnection();
        }
    }

}
