/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.validator;

import com.mycompany.gestioncompra.dao.ProductoDAO;
import com.mycompany.gestioncompra.dto.Producto;
import com.mycompany.gestioncompra.modelo.ProductoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author josera
 */
// @Component: As a Bean.
@Component
public class ProductoInfoValidator implements Validator {
    @Autowired
    private ProductoDAO productDAO;
 
    // This Validator support ProductInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductoInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        ProductoInfo productInfo = (ProductoInfo) target;
 
        // Check the fields of ProductInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
 
        String code = productInfo.getCode();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.productForm.code");
            } else if(productInfo.isNewProduct()) {
                Producto product = productDAO.findProduct(code);
                if (product != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }
    }
}
