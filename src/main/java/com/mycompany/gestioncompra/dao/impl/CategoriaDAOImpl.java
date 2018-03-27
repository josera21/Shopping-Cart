/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dao.impl;

import com.mycompany.gestioncompra.dao.CategoriaDAO;
import com.mycompany.gestioncompra.dto.Categoria;
import com.mycompany.gestioncompra.modelo.CategoryInfo;
import com.mycompany.gestioncompra.modelo.PaginationResult;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
public class CategoriaDAOImpl implements CategoriaDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Categoria findCategory(int code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Categoria.class);
        crit.add(Restrictions.eq("codigo", code));
        return (Categoria) crit.uniqueResult();
    }

    @Override
    public CategoryInfo findCategoryInfo(int code) {
        Categoria categoria = this.findCategory(code);
        if (categoria == null) {
            return null;
        }
        return new CategoryInfo(categoria.getCodigo(), categoria.getNombre());
    }

    @Override
    public PaginationResult<CategoryInfo> queryCategories(int page, int maxResult, int maxNavigationPage) {
        return queryCategories(page, maxResult, maxNavigationPage, null);
    }

    @Override
    public PaginationResult<CategoryInfo> queryCategories(int page, int maxResult, int maxNavigationPage, String likeName) {
        String sql = "Select new " + CategoryInfo.class.getName() //
                + "(c.codigo, c.nombre) " + " from "//
                + Categoria.class.getName() + " c ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(c.nombre) like :likeName ";
        }
        sql += " order by c.nombre desc ";
        //
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public void save(CategoryInfo categoryInfo) {
        int code = categoryInfo.getCode();
 
        Categoria categoria = null;
 
        boolean isNew = false;
        if (code != 0) {
            categoria = this.findCategory(code);
        }
        if (categoria == null) {
            isNew = true;
            categoria = new Categoria();
        }
        
        categoria.setCodigo(code);
        categoria.setNombre(categoryInfo.getName());
 
        if (categoryInfo.getFileData() != null) {
            byte[] image = categoryInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                categoria.setImagen(image);
            }
        }
        
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(categoria);
        }
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
    }
    
}
