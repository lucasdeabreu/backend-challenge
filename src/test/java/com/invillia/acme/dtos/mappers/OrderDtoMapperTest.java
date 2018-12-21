package com.invillia.acme.dtos.mappers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.dtos.OrderItemDto;
import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.entities.OrderStatus;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDtoMapperTest {

    public final OrderDtoMapper mapper = Mappers.getMapper(OrderDtoMapper.class);

    @Test
    public void shouldMapOrderToDto() {

        Order order = Order.builder()
                .id(1L)
                .address("St. I really don't know")
                .confirmationDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .items(getOrderItems())
                .build();

        OrderDto dto = mapper.orderToDto(order);

        assertNotNull(dto);
        assertEquals(order.getAddress(), dto.getAddress());
        assertEquals(order.getConfirmationDate(), dto.getConfirmationDate());
        assertEquals(order.getStatus(), dto.getStatus());

        assertNotNull(dto.getItems());
        assertFalse(dto.getItems().isEmpty());
        assertEquals(order.getItems().size(), dto.getItems().size());

        for (int i = 0; i < order.getItems().size(); i++) {
            assertEquals(order.getItems().get(i).getDescription(), dto.getItems().get(i).getDescription());
            assertEquals(order.getItems().get(i).getQuantity(), dto.getItems().get(i).getQuantity());
            assertEquals(order.getItems().get(i).getUnitPrice(), dto.getItems().get(i).getUnitPrice());
        }
    }

    @Test
    public void shouldMapDtoToOrder() {

        OrderDto dto = OrderDto.builder()
                .address("St. I really don't know")
                .confirmationDate(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .items(getOrderItemsDto())
                .build();

        Order order = mapper.dtoToOrder(dto);

        assertNotNull(order);
        assertEquals(dto.getAddress(), order.getAddress());
        assertEquals(dto.getConfirmationDate(), order.getConfirmationDate());
        assertEquals(dto.getStatus(), order.getStatus());

        assertNotNull(order.getItems());
        assertFalse(order.getItems().isEmpty());
        assertEquals(dto.getItems().size(), order.getItems().size());

        for (int i = 0; i < order.getItems().size(); i++) {
            OrderItem item = order.getItems().get(i);
            OrderItemDto itemDto = dto.getItems().get(i);

            assertEquals(itemDto.getDescription(), item.getDescription());
            assertEquals(itemDto.getQuantity(), item.getQuantity());
            assertEquals(itemDto.getUnitPrice(), item.getUnitPrice());

            assertEquals(order, item.getOrder());
        }
    }

    private List<OrderItem> getOrderItems() {
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

    private List<OrderItemDto> getOrderItemsDto() {
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
        );    }


}