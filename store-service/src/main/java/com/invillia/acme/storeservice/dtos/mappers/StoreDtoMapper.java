package com.invillia.acme.storeservice.dtos.mappers;

import com.invillia.acme.storeservice.dtos.StoreDto;
import com.invillia.acme.storeservice.entities.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreDtoMapper {

    Store dtoToStore(StoreDto dto);

    StoreDto storeToDto(Store store);
}
