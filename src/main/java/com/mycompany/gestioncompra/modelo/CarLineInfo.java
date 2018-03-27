/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.modelo;

/**
 *
 * @author josera
 */
public class CarLineInfo {
    private ProductoInfo productoInfo;
    private int quantity;

    public CarLineInfo() {
        this.quantity = 0;
    }
    
    /**
     * @return the productoInfo
     */
    public ProductoInfo getProductoInfo() {
        return productoInfo;
    }

    /**
     * @param productoInfo the productoInfo to set
     */
    public void setProductoInfo(ProductoInfo productoInfo) {
        this.productoInfo = productoInfo;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getAmount() {
        return this.productoInfo.getPrice() * this.quantity;
    }
}
