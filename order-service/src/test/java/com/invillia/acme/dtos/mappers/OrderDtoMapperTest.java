package com.invillia.acme.dtos.mappers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.dtos.OrderItemDto;
import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.utils.OrderSampleData;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class OrderDtoMapperTest {

    private final OrderDtoMapper mapper = Mappers.getMapper(OrderDtoMapper.class);

    @Test
    public void shouldMapOrderToDto() {

        Order order = OrderSampleData.getOrderSample();
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

        OrderDto dto = OrderSampleData.getOrderDtoSample();
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
}