package com.pandacreep.onlineshop.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> info = new TreeMap<>();
        info.put("error", "there is no such resource");
        info.put("id", String.valueOf(ex.getId()));
        info.put("resource", ex.getResource());
        return ResponseEntity.unprocessableEntity().body(info);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBindException(BindException ex) {
        var bindingResult = ex.getBindingResult();

        var apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());

        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}
