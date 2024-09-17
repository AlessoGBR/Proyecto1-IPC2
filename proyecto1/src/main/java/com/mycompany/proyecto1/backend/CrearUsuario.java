/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import java.util.List;

/**
 *
 * @author alesso
 */
public class CrearUsuario {

    private final String username;
    private final String password;
    private final TipoUsuarioEnum userType;
    private final String descripcion;
    private final List<String> tags;
    private final String foto;

    public CrearUsuario(String username, String password, TipoUsuarioEnum userType, String descripcion, List<String> tags, String foto) {
        this.username = username;
        this.password = password;
        this.descripcion = descripcion;
        this.userType = userType;
        this.tags = tags;
        this.foto = foto;
    }

    public Usuario crearUsuario() throws UserDataInvalid {
        validarDatos();
        return new Usuario(username, password, userType, descripcion, tags, foto);
    }

    private void validarDatos() throws UserDataInvalid {
        if (username == null || username.isEmpty()) {
            throw new UserDataInvalid("El nombre de usuario no puede estar vacío");
        }
        if (password == null || password.isEmpty()) {
            throw new UserDataInvalid("La contraseña no puede estar vacía");
        }
        if (tags == null || tags.isEmpty() || tags.stream().allMatch(String::isEmpty)) {
            throw new UserDataInvalid("Debes de seleccionar al menos una etiqueta");
        }
        if (username.length() >= 50) {
            throw new UserDataInvalid("Este campo no puede contener más de 50 caracteres. Por favor, edite su entrada");
        }
        if (descripcion.length() >= 200) {
            throw new UserDataInvalid("Este campo no puede contener más de 200 caracteres. Por favor, edite su entrada");
        }
    }

}
