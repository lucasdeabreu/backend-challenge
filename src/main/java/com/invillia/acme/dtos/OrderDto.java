package com.invillia.acme.dtos;

import com.invillia.acme.entities.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id"})
public class OrderDto {

    private Long id;

    private String address;

    private LocalDateTime confirmationDate;

    private OrderStatus status;

    private List<OrderItemDto> items;

}
