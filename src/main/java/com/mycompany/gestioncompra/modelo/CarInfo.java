/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josera
 */
public class CarInfo {
    
    private int orderNum;
 
    private ClienteInfo clienteInfo;
 
    private final List<CarLineInfo> carLines = new ArrayList<>();
 
    public CarInfo() {
 
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public ClienteInfo getClienteInfo() {
        return clienteInfo;
    }
 
    public void setClienteInfo(ClienteInfo clienteInfo) {
        this.clienteInfo = clienteInfo;
    }
 
    public List<CarLineInfo> getCarLines() {
        return this.carLines;
    }
 
    private CarLineInfo findLineByCode(String code) {
        for (CarLineInfo line : this.carLines) {
            if (line.getProductoInfo().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }
 
    public void addProduct(ProductoInfo productoInfo, int quantity) {
        CarLineInfo line = this.findLineByCode(productoInfo.getCode());
 
        if (line == null) {
            line = new CarLineInfo();
            line.setQuantity(0);
            line.setProductoInfo(productoInfo);
            this.carLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.carLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
 
    public void validate() {
 
    }
 
    public void updateProduct(String code, int quantity) {
        CarLineInfo line = this.findLineByCode(code);
 
        if (line != null) {
            if (quantity <= 0) {
                this.carLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
 
    public void removeProduct(ProductoInfo productoInfo) {
        CarLineInfo line = this.findLineByCode(productoInfo.getCode());
        if (line != null) {
            this.carLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.carLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.clienteInfo != null && this.clienteInfo.isValid();
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CarLineInfo line : this.carLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public double getAmountTotal() {
        double total = 0;
        for (CarLineInfo line : this.carLines) {
            total += line.getAmount();
        }
        return total;
    }
 
    public void updateQuantity(CarInfo cartForm) {
        if (cartForm != null) {
            List<CarLineInfo> lines = cartForm.getCarLines();
            for (CarLineInfo line : lines) {
                this.updateProduct(line.getProductoInfo().getCode(), line.getQuantity());
            }
        }
 
    }
}
