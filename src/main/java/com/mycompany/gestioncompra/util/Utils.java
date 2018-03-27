/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.util;

import com.mycompany.gestioncompra.modelo.CarInfo;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author josera
 */
public class Utils {
    // Products in Cart, stored in Session.
    public static CarInfo getCartInSession(HttpServletRequest request) {
 
        // Get Cart from Session.
        CarInfo cartInfo = (CarInfo) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CarInfo();
            
            // And store to Session.
            request.getSession().setAttribute("myCart", cartInfo);
        }
 
        return cartInfo;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }
 
    public static void storeLastOrderedCartInSession(HttpServletRequest request, CarInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }
    
    public static CarInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CarInfo) request.getSession().getAttribute("lastOrderedCart");
    }
}
