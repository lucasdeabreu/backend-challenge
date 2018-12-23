package com.invillia.acme.services;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;
import com.invillia.acme.repositories.OrderRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.invillia.acme.repositories.specifications.OrderSpecification.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByParameters(String address, OrderStatus status, LocalDateTime confirmationDate) {

        Specification<Order> spec = Specification
                .where(addressContains(address))
                .and(statusEqualsTo(status))
                .and(confirmationDateEqualsTo(confirmationDate))
                .and(fetchItems());
        return orderRepository.findAll(spec);
    }
}
