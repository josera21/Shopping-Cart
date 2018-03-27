/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.modelo;

import com.mycompany.gestioncompra.dto.Categoria;
import com.mycompany.gestioncompra.dto.Producto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author josera
 */
public class ProductoInfo {
    private String code;
    private String name;
    private double price;
    
    private int categoryCode;
    
    private boolean newProduct=false;
 
    // Upload file.
    private CommonsMultipartFile fileData;
 
    public ProductoInfo() {
    }
 
    public ProductoInfo(Producto product) {
        this.code = product.getCodigo();
        this.name = product.getNombre();
        this.price = product.getPrecio();
    }
    // For search all products
    public ProductoInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
    
    // For search by category
    public ProductoInfo(String code, String name, double price, int categoryCode) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    /**
     * @return the categoryCode
     */
    public int getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode the categoryCode to set
     */
    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
