package com.invillia.acme.storeservice.exceptions;

public class StoreNotFound extends RuntimeException {
    public StoreNotFound(Long storeId) {
        super("Cannot find a Store with Id " + storeId);
    }
}
