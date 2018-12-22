package com.invillia.acme.repositories.specifications;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.LocalDateTime;

public class OrderSpecification {

    private OrderSpecification() {
    }

    public static Specification<Order> addressContains(String address) {
        return (root, cq, cb) ->
                address == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.like(cb.lower(root.get("address")), address.toLowerCase() + "%");
    }

    public static Specification<Order> statusEqualsTo(OrderStatus status) {
        return (root, cq, cb) ->
                status == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.equal(root.get("status"), status);
    }

    public static Specification<Order> confirmationDateEqualsTo(LocalDateTime confirmationDate) {
        return (root, cq, cb) ->
                confirmationDate == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.equal(root.get("confirmationDate"), confirmationDate);
    }

    public static Specification<Order> fetchItems() {
        return (root, cq, cb) -> {
            root.fetch("items", JoinType.LEFT);
            return null;
        };
    }

}
