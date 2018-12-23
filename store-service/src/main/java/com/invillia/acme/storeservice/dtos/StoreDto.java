package com.invillia.acme.storeservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

}
