/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.ConsultaEtiquetasService;
import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.InicioSesionService;
import com.mycompany.proyecto1.backend.VerificarUsuarioService;
import com.mycompany.proyecto1.backend.sql.Conexion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

    private String username;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        String password = request.getParameter("password");
        Conexion conexion = new Conexion();
        conexion.conectar();
        try (Connection connection = Conexion.connection) {
            // Verifica si el usuario existe
            VerificarUsuarioService verificarUsuarioService = new VerificarUsuarioService(connection);
            if (verificarUsuarioService.verificarUser(username)) {

                InicioSesionService inicioSesionService = new InicioSesionService(username, password);
                String encryptedPassword = inicioSesionService.verificarUser(username);
                String decryptedPassword = inicioSesionService.desencriptarPassword(encryptedPassword);

                if (decryptedPassword.equals(password)) {
                    Integer tipoUsuario = inicioSesionService.verificarTipoUser(username);
                    manejarRedireccionSegunTipoUsuario(request, response, tipoUsuario, connection);
                } else {
                    throw new UserDataInvalid("Usuario o contraseña incorrecta");
                }
            } else {
                throw new UserDataInvalid("Usuario o contraseña incorrecta");
            }
        } catch (UserDataInvalid e) {
            manejarErrorDeSesion(request, response, e.getMessage());
        } catch (SQLException e) {
            manejarErrorDeSesion(request, response, "Error al procesar la solicitud.");
        }
        Conexion.closeConnection();
    }

    private void manejarRedireccionSegunTipoUsuario(HttpServletRequest request, HttpServletResponse response, Integer tipoUsuario, Connection connection) throws ServletException, IOException, UserDataInvalid {
        switch (tipoUsuario) {
            case 1: // ADMINISTRADOR
                request.getRequestDispatcher("jsp/inicioAdmin.jsp").forward(request, response);
                break;
            case 2: // LECTOR
                request.getRequestDispatcher("jsp/inicio.jsp").forward(request, response);
                break;
            case 3: // EDITOR                
                response.sendRedirect(request.getContextPath() + "/RevistasCreadas?username=" + URLEncoder.encode(username, "UTF-8"));
                break;
            default:
                request.getRequestDispatcher("jsp/inicio.jsp").forward(request, response);
                break;
        }
    }

    private void manejarErrorDeSesion(HttpServletRequest request, HttpServletResponse response, String mensajeError) throws ServletException, IOException {
        request.setAttribute("error", mensajeError);
        request.getRequestDispatcher("jsp/InicioDeSesion.jsp").forward(request, response);
    }

}
