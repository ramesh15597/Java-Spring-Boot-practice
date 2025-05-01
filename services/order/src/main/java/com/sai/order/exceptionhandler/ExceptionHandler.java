package com.sai.order.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@RestControllerAdvice
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionHandler extends RuntimeException {

    private String message;
    private String status;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ErrorResponse handleValidationException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage().substring(0,99));
        body.put("status", "Failure");
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    public ErrorResponse handleCustomerNotFoundException(BusinessException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", "Failure");
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
