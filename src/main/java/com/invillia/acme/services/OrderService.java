package com.invillia.acme.services;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> findByParameters(String address, OrderStatus status, LocalDateTime confirmationDate);
}
