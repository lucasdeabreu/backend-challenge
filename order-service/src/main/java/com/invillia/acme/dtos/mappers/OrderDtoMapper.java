package com.invillia.acme.dtos.mappers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.entities.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class OrderDtoMapper {

    @AfterMapping
    protected void fillOrderOnItems(@MappingTarget Order order) {
        if (order.getItems() != null) {
            order.getItems().forEach(i -> i.setOrder(order));
        }
    }

    public abstract Order dtoToOrder(OrderDto dto);

    public abstract OrderDto orderToDto(Order order);
}
