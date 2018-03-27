package com.mycompany.gestioncompra.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.gestioncompra.dto.Producto;
import com.mycompany.gestioncompra.modelo.PaginationResult;
import com.mycompany.gestioncompra.modelo.ProductoInfo;

/**
 *
 * @author josera
 */
public interface ProductoDAO {
    public Producto findProduct(String code);
    
    public ProductoInfo findProductInfo(String code) ;
  
    
    public PaginationResult<ProductoInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ProductoInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName);
    
    public PaginationResult<ProductoInfo> queryProductsByCate(int page, int maxResult,
                    int maxNavigationPage, int code_cate);
    
    public void save(ProductoInfo productInfo);
}
