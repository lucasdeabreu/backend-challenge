package com.invillia.acme.storeservice.services;

import com.invillia.acme.storeservice.entities.Store;
import com.invillia.acme.storeservice.exceptions.StoreNotFound;
import com.invillia.acme.storeservice.repositories.StoreRepository;
import com.invillia.acme.storeservice.repositories.specifications.StoreSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Store> findAllByParameters(String name, String address) {
        Specification<Store> spec = Specification
                .where(StoreSpecification.nameContains(name))
                .and(StoreSpecification.addressContains(address));
        return storeRepository.findAll(spec);
    }

    @Override
    public Store update(Long storeId, Store store) {
        if (storeRepository.existsById(storeId)) {
            store.setId(storeId);
            return save(store);
        }
        throw new StoreNotFound(storeId);
    }
}
