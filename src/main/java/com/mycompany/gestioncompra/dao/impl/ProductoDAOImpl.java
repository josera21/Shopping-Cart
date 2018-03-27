/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dao.impl;

import com.mycompany.gestioncompra.dao.ProductoDAO;
import com.mycompany.gestioncompra.dto.Producto;
import java.util.Date;
import com.mycompany.gestioncompra.modelo.PaginationResult;
import com.mycompany.gestioncompra.modelo.ProductoInfo;
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
public class ProductoDAOImpl implements ProductoDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Producto findProduct(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Producto.class);
        crit.add(Restrictions.eq("codigo", code));
        return (Producto) crit.uniqueResult();
    }

    @Override
    public ProductoInfo findProductInfo(String code) {
        Producto producto = this.findProduct(code);
        if (producto == null) {
            return null;
        }
        return new ProductoInfo(producto);
    }

    @Override
    public PaginationResult<ProductoInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }

    @Override
    public PaginationResult<ProductoInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
        String sql = "Select new " + ProductoInfo.class.getName() //
                + "(p.codigo, p.nombre, p.precio) " + " from "//
                + Producto.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.nombre) like :likeName ";
        }
        sql += " order by p.fecha_creacion desc ";
        //
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductoInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductoInfo> queryProductsByCate(int page, int maxResult, int maxNavigationPage, int code_cate) {
        String sql = "Select new " + ProductoInfo.class.getName() //
                + "(p.codigo, p.nombre, p.precio, p.categoria.codigo) " + " from " //
                + Producto.class.getName() + " p";
        
        if(code_cate != 0) {
            sql += " Where p.categoria.codigo = :code_cate ";
        }
        sql += " order by p.fecha_creacion desc ";
        
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery(sql);
        if (code_cate != 0) {
            query.setParameter("code_cate", code_cate);
        }
        return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
    }
    
    @Override
    public void save(ProductoInfo productInfo) {
        String code = productInfo.getCode();
 
        Producto product = null;
 
        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Producto();
            product.setFechaCreacion(new Date());
        }
        product.setCodigo(code);
        product.setNombre(productInfo.getName());
        product.setPrecio(productInfo.getPrice());
 
        if (productInfo.getFileData() != null) {
            byte[] image = productInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                product.setImagen(image);
            }
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
    }
}
