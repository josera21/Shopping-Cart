/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author josera
 */
public class OrdenInfo {
    private String id;
    private Date orderDate;
    private int orderNum;
    private double amount;
 
    private String clienteName;
    private String clienteAddress;
    private String clienteEmail;
    private String clientePhone;
 
    private List<OrdenDetalleInfo> details;
 
    public OrdenInfo() {
 
    }
 
    // Using for Hibernate Query.
    // Sử dụng cho Hibernate Query.
    public OrdenInfo(String id, Date orderDate, int orderNum, //
            double amount, String clienteName, String clienteAddress, //
            String clienteEmail, String clientePhone) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.amount = amount;
 
        this.clienteName = clienteName;
        this.clienteAddress = clienteAddress;
        this.clienteEmail = clienteEmail;
        this.clientePhone = clientePhone;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
 
    public String getClienteName() {
        return clienteName;
    }
 
    public void setClienteName(String ClienteName) {
        this.clienteName = ClienteName;
    }
 
    public String getClienteAddress() {
        return clienteAddress;
    }
 
    public void setClienteAddress(String ClienteAddress) {
        this.clienteAddress = ClienteAddress;
    }
 
    public String getClienteEmail() {
        return clienteEmail;
    }
 
    public void setClienteEmail(String ClienteEmail) {
        this.clienteEmail = ClienteEmail;
    }
 
    public String getClientePhone() {
        return clientePhone;
    }
 
    public void setClientePhone(String ClientePhone) {
        this.clientePhone = ClientePhone;
    }
 
    public List<OrdenDetalleInfo> getDetails() {
        return details;
    }
 
    public void setDetails(List<OrdenDetalleInfo> details) {
        this.details = details;
    }
}
