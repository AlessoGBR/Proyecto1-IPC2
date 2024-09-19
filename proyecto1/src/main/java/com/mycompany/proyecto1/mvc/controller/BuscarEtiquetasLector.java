/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.Revista;
import com.mycompany.proyecto1.backend.sql.BuscarRevistas;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alesso
 */
@WebServlet(name = "BuscarEtiquetasLector", urlPatterns = {"/Buscar"})
public class BuscarEtiquetasLector extends HttpServlet {

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
        String username = request.getParameter("username");
        String selectedTags = request.getParameter("selectedTagsInput");
        Conexion conexion = new Conexion();
        conexion.conectar();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username != null && selectedTags != null && !selectedTags.trim().isEmpty()) {
            try {
                List<String> etiquetasSeleccionadas = Arrays.asList(selectedTags.split(","));
                BuscarRevistas obtenerRevistas = new BuscarRevistas(Conexion.connection);

                List<Revista> revistas = obtenerRevistas.buscarRevistasPorEtiquetas(etiquetasSeleccionadas);

                // Verificar si hay resultados
                if (revistas.isEmpty()) {
                    out.println("<div class='alert alert-info'>No se encontraron revistas para las etiquetas seleccionadas.</div>");
                } else {
                    // Mostrar las revistas encontradas
                    out.println("<div class='container'>");
                    out.println("<h3>Revistas encontradas:</h3>");
                    for (Revista revista : revistas) {
                        out.println("<div class='list-group-item mb-3'>");
                        out.println("<h4>" + revista.getTitulo() + "</h4>");
                        out.println("<p>" + revista.getDescripcion() + "</p>");
                        out.println("<div class='d-flex'>");

                        // Botón Me Gusta
                        out.println("<form action='javascript:void(0);' method='POST' class='mr-2' onsubmit=\"event.preventDefault(); meGusta('" + revista.getIdRevista() + "', '" + username + "');\">");
                        out.println("<input type='hidden' name='idRevista' value='" + revista.getIdRevista() + "'>");
                        out.println("<input type='hidden' name='username' value='" + username + "'>");
                        out.println("<button type='submit' class='btn btn-primary'>");
                        out.println("Me Gusta <span class='badge badge-light'>" + revista.getLikes() + "</span>");
                        out.println("</button>");
                        out.println("</form>");

                        // Formulario Agregar Comentario
                        out.println("<form action='javascript:void(0);' method='POST' class='mr-2' onsubmit=\"event.preventDefault(); agregarComentario('" + revista.getIdRevista() + "');\">");
                        out.println("<button type='submit' class='btn btn-info'>Agregar Comentario</button>");
                        out.println("<input type='hidden' name='idRevista' value='" + revista.getIdRevista() + "'>");
                        out.println("<input type='hidden' id='username' value='" + username + "'>");
                        out.println("<textarea id='comentario" + revista.getIdRevista() + "' placeholder='Escribe tu comentario aquí...' required maxlength='200'></textarea>");
                        out.println("</form>");

                        // Botón Previsualizar
                        out.println("<form class='mr-2'>");
                        out.println("<input type='hidden' name='idRevista' value='" + revista.getIdRevista() + "'>");
                        out.println("<button type='button' class='btn btn-info' data-toggle='modal' data-target='#modalRevista" + revista.getIdRevista() + "'>");
                        out.println("Previsualizar");
                        out.println("</button>");
                        out.println("</form>");

                        out.println("</div>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
            } catch (SQLException ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos.");
            }
        } else {
            // Enviar mensaje si no se seleccionaron etiquetas
            out.println("<div class='alert alert-warning'>Por favor, selecciona al menos una etiqueta para buscar revistas.</div>");
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
