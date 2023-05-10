package com.humanresourcesmanagement.controller.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.HashMap;
import java.util.Map;

public class Validation {
    //  ---------SINGLETON---------------------------------------------------------------
    private static final Validation VALIDATION = new Validation();
    private Validation() {
    }

    public static Validation getValidation() {
        return VALIDATION;
    }

    //  ---------CREATE-VALIDATION-FACTORY-----------------------------------------------
    Validator validator = jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator();

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
