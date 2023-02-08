package com.yeti.store.inventoryservice.exception;

public class ServiceException extends Exception{

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
    
}
