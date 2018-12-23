package com.invillia.acme.storeservice.dtos.mappers;

import com.invillia.acme.storeservice.dtos.StoreDto;
import com.invillia.acme.storeservice.entities.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StoreDtoMapper {

    public abstract Store dtoToOrder(StoreDto dto);

    public abstract StoreDto orderToDto(Store store);
}
