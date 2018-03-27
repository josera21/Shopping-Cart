/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dao.impl;

import com.mycompany.gestioncompra.dao.UsuarioDAO;
import com.mycompany.gestioncompra.dto.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author josera
 */
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Usuario findAccount(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Usuario.class);
        crit.add(Restrictions.eq("username", userName));
        return (Usuario) crit.uniqueResult();
    }
    
}
