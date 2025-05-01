package com.sai.order.exceptionhandler;


public class BusinessException extends RuntimeException{

    private String message;
    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }
}
