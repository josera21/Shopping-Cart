/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dao;

import com.mycompany.gestioncompra.dto.Categoria;
import com.mycompany.gestioncompra.modelo.CategoryInfo;
import com.mycompany.gestioncompra.modelo.PaginationResult;

/**
 *
 * @author josera
 */
public interface CategoriaDAO {
    public Categoria findCategory(int code);
    
    public CategoryInfo findCategoryInfo(int code) ;
  
    
    public PaginationResult<CategoryInfo> queryCategories(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<CategoryInfo> queryCategories(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(CategoryInfo categoryInfo);
}
