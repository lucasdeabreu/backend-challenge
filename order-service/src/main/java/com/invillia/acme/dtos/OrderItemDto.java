package com.invillia.acme.dtos;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice;

    @Min(1)
    @NotNull
    private Integer quantity;

}
