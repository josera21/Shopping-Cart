/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author josera
 */
@Entity
public class Usuario implements Serializable {
    @Id
    @Column(name = "username", length = 20, nullable = false)
    private String username;
    
    public static final String ROL_MANAGER = "MANAGER";
    public static final String ROL_EMPLEADO = "EMPLEADO";
    
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    @Column(name = "rol", length = 20, nullable = false)
    private String rol;
    @Column(name = "activo", length = 1, nullable = false)
    private boolean activo;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
