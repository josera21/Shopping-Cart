/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author josera
 */
@Entity
public class OrdenDetalle implements Serializable {
    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orden", nullable = false)
    private Orden orden;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producto", nullable = false)
    private Producto producto;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "precio", nullable = false)
    private double precio;
    
    @Column(name = "monto_acum", nullable = false)
    private double monto_acum;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the orden
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the montoAcum
     */
    public double getMontoAcum() {
        return monto_acum;
    }

    /**
     * @param montoAcum the montoAcum to set
     */
    public void setMontoAcum(double montoAcum) {
        this.monto_acum = montoAcum;
    }
}
