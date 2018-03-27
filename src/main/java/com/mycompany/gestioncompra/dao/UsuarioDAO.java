package com.mycompany.gestioncompra.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.gestioncompra.dto.Usuario;

/**
 *
 * @author josera
 */
public interface UsuarioDAO {
    public Usuario findAccount(String userName );
}
