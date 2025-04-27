package com.sai.products.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorProductResponse {

    private int statusCode;
    private String message;

    public ErrorProductResponse(String message)
    {
        super();
        this.message = message;
    }
}
