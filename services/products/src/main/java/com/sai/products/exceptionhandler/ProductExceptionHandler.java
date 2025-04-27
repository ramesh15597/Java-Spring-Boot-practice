package com.sai.products.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductExceptionHandler extends RuntimeException {

    private String message;
    private String status;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ErrorProductResponse handleValidationException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", "Failure");
        return new ErrorProductResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
 @org.springframework.web.bind.annotation.ExceptionHandler(value = EntityNotFoundException.class)
    public ErrorProductResponse handleEntityException(ProductExceptionHandler ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", "Failure");
        return new ErrorProductResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }


}
