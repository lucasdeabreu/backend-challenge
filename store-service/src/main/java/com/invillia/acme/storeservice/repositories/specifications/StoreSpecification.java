package com.invillia.acme.storeservice.repositories.specifications;

import com.invillia.acme.storeservice.entities.Store;
import org.springframework.data.jpa.domain.Specification;

public class StoreSpecification {

    private StoreSpecification() {
    }

    public static Specification<Store> nameContains(String name) {
        return (root, cq, cb) ->
                name == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.like(cb.lower(root.get("name")), name.toLowerCase() + "%");
    }

    public static Specification<Store> addressContains(String address) {
        return (root, cq, cb) ->
                address == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.like(cb.lower(root.get("address")), address.toLowerCase() + "%");
    }
}
