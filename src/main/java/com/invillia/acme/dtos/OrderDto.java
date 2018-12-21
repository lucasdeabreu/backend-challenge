package com.invillia.acme.dtos;

import com.invillia.acme.entities.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private String address;

    private LocalDateTime confirmationDate;

    private OrderStatus status;

    private List<OrderItemDto> items;

}
