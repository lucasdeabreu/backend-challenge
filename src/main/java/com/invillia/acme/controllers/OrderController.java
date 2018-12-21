package com.invillia.acme.controllers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.dtos.mappers.OrderDtoMapper;
import com.invillia.acme.entities.OrderStatus;
import com.invillia.acme.services.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDtoMapper mapper;

    public OrderController(OrderService orderService, OrderDtoMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody OrderDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.orderToDto(orderService.save(mapper.dtoToOrder(dto)))
        );
    }

    @GetMapping
    public ResponseEntity findByParameters(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime confirmationDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        orderService.findByParameters(address, status, confirmationDate)
                                .stream()
                                .map(mapper::orderToDto)
                                .collect(Collectors.toList())
                );
    }
}
