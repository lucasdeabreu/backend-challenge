package com.invillia.acme.utils;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.dtos.OrderItemDto;
import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.entities.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderSampleData {

    public static OrderDto getOrderDtoSample() {
        return OrderDto.builder()
                .address("St. I really don't know")
                .confirmationDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .items(getOrderItemsDtoSample())
                .build();
    }

    private static List<OrderItemDto> getOrderItemsDtoSample() {
        return Arrays.asList(
                OrderItemDto.builder()
                        .description("Macbook $$")
                        .unitPrice(BigDecimal.ONE)
                        .quantity(10)
                        .build(),
                OrderItemDto.builder()
                        .description("Hiphone Xzy")
                        .unitPrice(BigDecimal.TEN)
                        .quantity(1)
                        .build()
        );
    }

    public static Order getOrderSample() {
        return Order.builder()
                .id(1L)
                .address("St. I really don't know")
                .confirmationDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .items(getOrderItems())
                .build();
    }

    private static List<OrderItem> getOrderItems() {
        return Arrays.asList(
                OrderItem.builder()
                        .id(1L)
                        .description("Macbook $$")
                        .unitPrice(BigDecimal.ONE)
                        .quantity(10)
                        .build(),
                OrderItem.builder()
                        .id(1L)
                        .description("Hiphone Xzy")
                        .unitPrice(BigDecimal.TEN)
                        .quantity(1)
                        .build()
        );
    }
}
