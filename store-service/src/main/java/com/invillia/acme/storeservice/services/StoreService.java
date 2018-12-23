package com.invillia.acme.storeservice.services;

import com.invillia.acme.storeservice.entities.Store;

import java.util.List;

public interface StoreService {
    Store save(Store store);

    List<Store> findAllByParameters(String name, String address);

    Store update(Long storeId, Store store);
}
