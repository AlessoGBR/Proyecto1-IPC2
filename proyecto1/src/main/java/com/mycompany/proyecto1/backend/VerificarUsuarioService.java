/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.sql.VerificarUsuario;
import java.sql.Connection;

/**
 *
 * @author alesso
 */
public class VerificarUsuarioService {

    private final Connection connection;

    public VerificarUsuarioService(Connection connection) {
        this.connection = connection;
    }

    public boolean verificarUser(String username) throws UserDataInvalid {
        VerificarUsuario verificar = new VerificarUsuario(connection);
        return verificar.verificarUser(username);
    }
}
