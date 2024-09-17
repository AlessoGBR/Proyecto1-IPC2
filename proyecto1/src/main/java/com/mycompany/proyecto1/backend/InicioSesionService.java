/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import com.mycompany.proyecto1.backend.Exceptions.UserDataInvalid;
import com.mycompany.proyecto1.backend.sql.InicioSesionDB;

/**
 *
 * @author alesso
 */
public class InicioSesionService {

    private final InicioSesionDB inicioSesionDB;

    public InicioSesionService(String username, String password) {
        this.inicioSesionDB = new InicioSesionDB(username, password);
    }

    public String desencriptarPassword(String encryptedPassword) throws UserDataInvalid {
        try {
            return inicioSesionDB.desencriptarPass(encryptedPassword, "ipc2");
        } catch (Exception e) {
            throw new UserDataInvalid("Error al desencriptar la contraseña");
        }
    }

    public String verificarUser(String username) throws UserDataInvalid {
        return inicioSesionDB.verificarUser(username);
    }

    public Integer verificarTipoUser(String username) throws UserDataInvalid {
        return inicioSesionDB.verificarTipoUser(username);
    }
}
