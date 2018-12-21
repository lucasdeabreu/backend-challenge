package com.invillia.acme.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

    private String description;

    private BigDecimal unitPrice;

    private Integer quantity;

}
