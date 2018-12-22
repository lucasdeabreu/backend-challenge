package com.invillia.acme.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    private Long id;

    private String description;

    private BigDecimal unitPrice;

    private Integer quantity;

}
