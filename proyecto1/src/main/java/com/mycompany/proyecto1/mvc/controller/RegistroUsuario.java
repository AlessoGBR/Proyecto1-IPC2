/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1.mvc.controller;

import com.mycompany.proyecto1.backend.CrearUsuario;
import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.GuardarImagen;
import com.mycompany.proyecto1.backend.TipoUsuarioEnum;
import com.mycompany.proyecto1.backend.Usuario;
import com.mycompany.proyecto1.backend.sql.Conexion;
import com.mycompany.proyecto1.backend.sql.RegistrarUsuarioDB;
import com.mycompany.proyecto1.backend.sql.VerificarUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alesso
 */
@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
@MultipartConfig
public class RegistroUsuario extends HttpServlet {

    private String fileName;

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
        Conexion conexion = new Conexion();
        conexion.conectar();
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String descripcion = request.getParameter("description");
        TipoUsuarioEnum userType = TipoUsuarioEnum.valueOf(request.getParameter("userType"));
        Part filePart = request.getPart("fileUpload");
        String etiqueta = request.getParameter("selectedTagsInput");
        List<String> etiquetas = Arrays.asList(etiqueta.split(","));
        VerificarUsuario registro = new VerificarUsuario(Conexion.connection);
        GuardarImagen guardarImagen = new GuardarImagen();
        try {
            fileName = guardarImagen.guardarImagen(filePart);
            request.setAttribute("imagen", fileName);
        } catch (IllegalArgumentException e) {

        }
        try {
            if (!password.equals(confirmPassword)) {
                throw new UserDataInvalid("Las contraseñas no coinciden");
            }

            CrearUsuario crearUser = new CrearUsuario(user, password, userType, descripcion, etiquetas, fileName);
            Usuario usuario = crearUser.crearUsuario();

            if (registro.verificarUser(usuario.getUsername())) {
                throw new UserDataInvalid("Usuario ya registrado");
            }

            if (etiquetas == null || etiquetas.isEmpty() || etiquetas.stream().allMatch(String::isEmpty)) {
                throw new UserDataInvalid("Debes de seleccionar al menos una etiqueta");
            }

            RegistrarUsuarioDB ingresoUsuario = new RegistrarUsuarioDB();
            if (ingresoUsuario.registrarUsuarioYPerfil(usuario)) {
                redirigirSegunTipoUsuario(request, response, userType, crearUser);
            }

        } catch (UserDataInvalid e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/MostrarEtiquetas");
        } catch (ServletException | IOException e) {
            request.getSession().setAttribute("error", "Ocurrio un error inesperado");
            response.sendRedirect(request.getContextPath() + "/MostrarEtiquetas");
        }

    }

    private void redirigirSegunTipoUsuario(HttpServletRequest request, HttpServletResponse response, TipoUsuarioEnum userType, CrearUsuario crearUser) throws ServletException, IOException {
        String paginaDestino = "jsp/inicio.jsp";

        switch (userType) {
            case ADMINISTRADOR:
                paginaDestino = "jsp/InicioDeSesion.jsp";
                break;
            case LECTOR:
                paginaDestino = "jsp/InicioDeSesion.jsp";
                break;
            case EDITOR:
                paginaDestino = "jsp/InicioDeSesion.jsp";
                break;
            case ANUNCIANTE:
                paginaDestino = "jsp/InicioDeSesion.jsp";
                break;
            default:
                break;
        }

        request.setAttribute("usuarioCreado", crearUser);
        request.getRequestDispatcher(paginaDestino).forward(request, response);
    }

}
