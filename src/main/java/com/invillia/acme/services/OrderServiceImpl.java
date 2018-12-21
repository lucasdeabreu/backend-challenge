package com.invillia.acme.services;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;
import com.invillia.acme.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Order> findByParameters(String address, OrderStatus status, LocalDateTime confirmationDate) {
        return orderRepository.findByParameters(address, status, confirmationDate);
    }
}
