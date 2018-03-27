package com.mycompany.gestioncompra.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.gestioncompra.dto.Orden;
import com.mycompany.gestioncompra.modelo.CarInfo;
import java.util.List;
import com.mycompany.gestioncompra.modelo.OrdenDetalleInfo;
import com.mycompany.gestioncompra.modelo.OrdenInfo;
import com.mycompany.gestioncompra.modelo.PaginationResult;

/**
 *
 * @author josera
 */
public interface OrdenDAO {
    public void saveOrder(CarInfo cartInfo);
 
    public PaginationResult<OrdenInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrdenInfo getOrderInfo(String orderId);
    
    public List<OrdenDetalleInfo> listOrderDetailInfos(String orderId);
}
