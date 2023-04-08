package com.humanresourcesmanagement.controller.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ServletValidation {
    //  ---------SINGLETON---------------------------------------------------------------
    private static final ServletValidation servletValidation = new ServletValidation();
    private ServletValidation() {
    }

    public static ServletValidation getValidation() {
        return servletValidation;
    }

    //  ---------CREATE-VALIDATION-FACTORY-----------------------------------------------
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    //  ---------CREATE-MAP-FOR-ERRORS---------------------------------------------------
    Map<String,String> errors = new HashMap<>();

    //  ---------VALIDATING-OBJECT-FIELDS-INPUT------------------------------------------
    public Map<String,String> doValidation(Object object){
        for (ConstraintViolation<Object> violation : validator.validate(object)) {
            errors.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        return errors;
    }
}
