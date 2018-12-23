package com.invillia.acme.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private Integer quantity;
}
