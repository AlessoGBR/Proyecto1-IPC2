/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import java.util.List;

/**
 *
 * @author alesso
 */
public class Usuario {

    private String username;
    private String password;
    private TipoUsuarioEnum userType;
    private String descripcion;
    private String foto;
    private List<String> tags;

    public Usuario(String username1, String password1, TipoUsuarioEnum userType1, String descripcion1, List<String> tags1, String foto1) {
        this.username = username1;
        this.password = password1;
        this.userType = userType1;
        this.descripcion = descripcion1;
        this.foto = foto1;
        this.tags = tags1;
    }
    
    public Usuario(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuarioEnum getUserType() {
        return userType;
    }

    public void setUserType(TipoUsuarioEnum userType) {
        this.userType = userType;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }    
    

    public boolean isValid() {
        return username != null && userType != null && descripcion != null
                && password != null;
    }

}
