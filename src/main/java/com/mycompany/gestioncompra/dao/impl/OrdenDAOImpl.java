/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dao.impl;

import com.mycompany.gestioncompra.dao.OrdenDAO;
import com.mycompany.gestioncompra.dao.ProductoDAO;
import com.mycompany.gestioncompra.dto.Orden;
import com.mycompany.gestioncompra.dto.OrdenDetalle;
import com.mycompany.gestioncompra.dto.Producto;
import com.mycompany.gestioncompra.modelo.CarInfo;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.mycompany.gestioncompra.modelo.CarLineInfo;
import com.mycompany.gestioncompra.modelo.ClienteInfo;
import com.mycompany.gestioncompra.modelo.OrdenDetalleInfo;
import com.mycompany.gestioncompra.modelo.OrdenInfo;
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
public class OrdenDAOImpl implements OrdenDAO {

    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductoDAO productoDAO;
    
    private int getMaxOrderNum() {
        String sql = "Select max(o.num_orden) from " + Orden.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }
    
    @Override
    public void saveOrder(CarInfo cartInfo) {
        Session session = sessionFactory.getCurrentSession();
 
        int orderNum = this.getMaxOrderNum() + 1;
        Orden orden = new Orden();
 
        orden.setId(UUID.randomUUID().toString());
        orden.setNumOrden(orderNum);
        orden.setFechaOrden(new Date());
        orden.setMontoTotal(cartInfo.getAmountTotal());
 
        ClienteInfo clienteInfo = cartInfo.getClienteInfo();
        orden.setNombCliente(clienteInfo.getName());
        orden.setEmailCliente(clienteInfo.getEmail());
        orden.setTlfCliente(clienteInfo.getPhone());
        orden.setDireccionCliente(clienteInfo.getAddress());
 
        session.persist(orden);
 
        List<CarLineInfo> lines = cartInfo.getCarLines();
 
        for (CarLineInfo line : lines) {
            OrdenDetalle detail = new OrdenDetalle();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrden(orden);
            detail.setMontoAcum(line.getAmount());
            detail.setPrecio(line.getProductoInfo().getPrice());
            detail.setCantidad(line.getQuantity());
 
            String code = line.getProductoInfo().getCode();
            Producto product = this.productoDAO.findProduct(code);
            detail.setProducto(product);
 
            session.persist(detail);
        }
 
        // Set OrderNum for report.
        // Set OrderNum để thông báo cho người dùng.
        cartInfo.setOrderNum(orderNum);
    }

    @Override
    public PaginationResult<OrdenInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrdenInfo.class.getName()//
                + "(ord.id, ord.fecha_orden, ord.num_orden, ord.monto_total, "
                + " ord.nomb_cliente, ord.direccion_cliente, ord.email_cliente, ord.tlf_cliente) " + " from "
                + Orden.class.getName() + " ord "//
                + " order by ord.num_orden desc";
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
 
        return new PaginationResult<OrdenInfo>(query, page, maxResult, maxNavigationPage);
    }

    public Orden findOrden(String ordenId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Orden.class);
        crit.add(Restrictions.eq("id", ordenId));
        return (Orden) crit.uniqueResult();
    }
    
    @Override
    public OrdenInfo getOrderInfo(String ordenId) {
        Orden orden = this.findOrden(ordenId);
        if (orden == null) {
            return null;
        }
        return new OrdenInfo(orden.getId(), orden.getFechaOrden(), //
                orden.getNumOrden(), orden.getMontoTotal(), orden.getNombCliente(), //
                orden.getDireccionCliente(), orden.getEmailCliente(), orden.getTlfCliente());
    }

    @Override
    public List<OrdenDetalleInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrdenDetalleInfo.class.getName() //
                + "(d.id, d.producto.codigo, d.producto.nombre , d.cantidad,d.precio,d.monto_acum) "//
                + " from " + OrdenDetalle.class.getName() + " d "//
                + " where d.orden.id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return query.list();
    }
    
}
