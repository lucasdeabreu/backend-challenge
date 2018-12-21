package com.invillia.acme.controllers;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;
import com.invillia.acme.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/orders")
public class OrderControllers {

    private final OrderService orderService;

    public OrderControllers(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity findByParameters(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) LocalDateTime confirmationDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.emptyList());
    }
}
