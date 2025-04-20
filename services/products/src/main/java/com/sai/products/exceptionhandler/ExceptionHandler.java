/*
package com.sai.products.exceptionhandler;

import com.sai.ecommerece.exceptionhandler.CustomerNotFoundException;
import com.sai.ecommerece.exceptionhandler.ErrorResponse;
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
public class ExceptionHandler extends RuntimeException {

    private String message;
    private String status;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExceptionHandler.class)
    public ErrorResponse handleValidationException(ExceptionHandler ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", "Failure");
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
@org.springframework.web.bind.annotation.ExceptionHandler(value = CustomerNotFoundException.class)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", "Failure");
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }



}
*/
