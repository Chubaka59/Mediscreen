package com.openclassrooms.mediscreenanalysis.controller.handler;

import lombok.Data;

@Data
public class FieldError {
    private String field;
    private String errorCode;
}
