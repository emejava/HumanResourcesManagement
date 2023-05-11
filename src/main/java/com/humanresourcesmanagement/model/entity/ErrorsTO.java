package com.humanresourcesmanagement.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class ErrorsTO {

    private Map<String,String> errors;
}
