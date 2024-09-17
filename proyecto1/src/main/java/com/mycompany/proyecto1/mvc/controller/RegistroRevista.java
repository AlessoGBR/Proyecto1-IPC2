/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.CrearRevista;
import com.mycompany.proyecto1.backend.Exceptions.RevistaDataInvalid;
import com.mycompany.proyecto1.backend.GuardarArchivo;
import com.mycompany.proyecto1.backend.Revista;
import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.NuevaRevista;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "RegistroRevista", urlPatterns = {"/Revista"})
@MultipartConfig
public class RegistroRevista extends HttpServlet {

    private String pathArchivo;

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

        Conexion conexion = new Conexion();
        conexion.conectar();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Procesar los parámetros del formulario
            String username = request.getParameter("username");
            Part filePart = request.getPart("fileUpload");
            String titulo = request.getParameter("tituloRevista");
            String version = request.getParameter("versionRevista");
            String fecha = request.getParameter("dateRevista");
            String descripcion = request.getParameter("descripcionRevista");
            boolean esGratuita = "on".equals(request.getParameter("esGratuita"));
            double precioRevista = Double.parseDouble(request.getParameter("precioRevista"));
            boolean comentario = "on".equals(request.getParameter("permitirComentarios"));
            boolean meGusta = "on".equals(request.getParameter("permitirMeGusta"));
            boolean subscriptores = "on".equals(request.getParameter("permitirSubscripcion"));
            String etiquetasStr = request.getParameter("selectedTagsInput");
            List<String> etiquetas = etiquetasStr != null ? Arrays.asList(etiquetasStr.split(",")) : new ArrayList<>();

            // Guardar el archivo
            GuardarArchivo guardar = new GuardarArchivo();
            pathArchivo = guardar.guardarPDF(filePart);

            // Crear la revista
            CrearRevista crearRevista = new CrearRevista(titulo, version, fecha, pathArchivo, descripcion, esGratuita, precioRevista, comentario, meGusta, subscriptores, etiquetas);
            Revista revista = crearRevista.crearRevista();
            NuevaRevista nuevaRevista = new NuevaRevista();

            if (nuevaRevista.verificarUser(username)) {
                try {
                    nuevaRevista.insertarRevista(revista, username);
                    out.write("{\"status\":\"success\", \"message\":\"Revista registrada correctamente.\"}");
                } catch (SQLException e) {
                    Connection connection = Conexion.connection;
                    if (connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException rollbackEx) {
                            out.write("{\"status\":\"error\", \"message\":\"Error durante el rollback: " + rollbackEx.getMessage() + "\"}");
                        }
                    }
                    out.write("{\"status\":\"error\", \"message\":\"Error al registrar la revista: " + e.getMessage() + "\"}");
                }
            } else {
                // Usuario no encontrado
                out.write("{\"status\":\"error\", \"message\":\"El usuario no existe.\"}");
            }

        } catch (RevistaDataInvalid | ServletException | IOException | NumberFormatException | ParseException e) {
            // Manejo de otros errores
            out.write("{\"status\":\"error\", \"message\":\"Error al registrar la revista: " + e.getMessage() + "\"}");
        } finally {
            out.flush();
            Conexion.closeConnection(); // Cerrar la conexión
        }

    }
}
