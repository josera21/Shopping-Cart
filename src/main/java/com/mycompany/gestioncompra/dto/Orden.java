/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author josera
 */
@Entity
public class Orden implements Serializable {
    @Id
    @Column(name="id", length = 50)
    private String id;
    
    @Column(name="fecha_orden", nullable = false)
    private Date fecha_orden;
    
    @Column(name="num_orden", nullable = false)
    private int num_orden;
    
    @Column(name="monto_total", nullable = false)
    private double monto_total;
    
    @Column(name="nomb_cliente", length = 255, nullable = false)
    private String nomb_cliente;
    
    @Column(name="email_cliente", length = 128, nullable = false)
    private String email_cliente;
    
    @Column(name="direccion_cliente", length = 255, nullable = false)
    private String direccion_cliente;
    
    @Column(name="tlf_cliente", length = 128, nullable = false)
    private String tlf_cliente;

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
     * @return the fechaOrden
     */
    public Date getFechaOrden() {
        return fecha_orden;
    }

    /**
     * @param fechaOrden the fechaOrden to set
     */
    public void setFechaOrden(Date fechaOrden) {
        this.fecha_orden = fechaOrden;
    }

    /**
     * @return the numOrden
     */
    public int getNumOrden() {
        return num_orden;
    }

    /**
     * @param numOrden the numOrden to set
     */
    public void setNumOrden(int numOrden) {
        this.num_orden = numOrden;
    }

    /**
     * @return the montoTotal
     */
    public double getMontoTotal() {
        return monto_total;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(double montoTotal) {
        this.monto_total = montoTotal;
    }

    /**
     * @return the nombCliente
     */
    public String getNombCliente() {
        return nomb_cliente;
    }

    /**
     * @param nombCliente the nombCliente to set
     */
    public void setNombCliente(String nombCliente) {
        this.nomb_cliente = nombCliente;
    }

    /**
     * @return the emailCliente
     */
    public String getEmailCliente() {
        return email_cliente;
    }

    /**
     * @param emailCliente the emailCliente to set
     */
    public void setEmailCliente(String emailCliente) {
        this.email_cliente = emailCliente;
    }

    /**
     * @return the direccionCliente
     */
    public String getDireccionCliente() {
        return direccion_cliente;
    }

    /**
     * @param direccionCliente the direccionCliente to set
     */
    public void setDireccionCliente(String direccionCliente) {
        this.direccion_cliente = direccionCliente;
    }

    /**
     * @return the tlfCliente
     */
    public String getTlfCliente() {
        return tlf_cliente;
    }

    /**
     * @param tlfCliente the tlfCliente to set
     */
    public void setTlfCliente(String tlfCliente) {
        this.tlf_cliente = tlfCliente;
    }
    
    
}
