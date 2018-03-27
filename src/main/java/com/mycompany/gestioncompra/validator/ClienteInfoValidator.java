/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestioncompra.validator;

import com.mycompany.gestioncompra.modelo.ClienteInfo;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author josera
 */
@Component
public class ClienteInfoValidator implements Validator {
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    // This Validator support CustomerInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ClienteInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        ClienteInfo clieInfo = (ClienteInfo) target;
 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
 
        if (!emailValidator.isValid(clieInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
}
