package com.phellipe.departments.controller.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ApiExceptions {
    private final List<String> errors;
    public ApiExceptions(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

    public ApiExceptions(BusinessException ex) {
        this.errors = Collections.singletonList(ex.getMessage());
    }
}
