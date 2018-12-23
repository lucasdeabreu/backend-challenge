package com.invillia.acme.storeservice.controllers;

import com.invillia.acme.storeservice.dtos.mappers.StoreDtoMapper;
import com.invillia.acme.storeservice.services.StoreService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@Api(value="store", description="Actions related to Store Service")
public class StoreController {

    private final StoreService storeService;
    private final StoreDtoMapper mapper;

    public StoreController(StoreService storeService, StoreDtoMapper mapper) {
        this.storeService = storeService;
        this.mapper = mapper;
    }




}
