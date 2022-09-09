package com.springAngluar.controllers.exceptions;

import lombok.Data;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError{
    @Serial
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(String fieldName, String message){
        this.erros.add(new FieldMessage(fieldName, message));
    }
}
