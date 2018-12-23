package com.invillia.acme.dtos;

import com.invillia.acme.entities.OrderStatus;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private Long id;

    @NotEmpty
    private String address;

    @NotNull
    private LocalDateTime confirmationDate;

    @NotNull
    private OrderStatus status;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderItemDto> items;

}
