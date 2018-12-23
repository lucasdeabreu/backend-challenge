package com.invillia.acme.storeservice.controllers;

import com.invillia.acme.storeservice.dtos.StoreDto;
import com.invillia.acme.storeservice.dtos.mappers.StoreDtoMapper;
import com.invillia.acme.storeservice.exceptions.StoreNotFound;
import com.invillia.acme.storeservice.services.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stores")
@Api(value = "store", description = "Actions related to Store Service")
public class StoreController {

    private final StoreService storeService;
    private final StoreDtoMapper mapper;

    public StoreController(StoreService storeService, StoreDtoMapper mapper) {
        this.storeService = storeService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Create a Store")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid StoreDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.storeToDto(storeService.save(mapper.dtoToStore(dto)))
        );
    }

    @ApiOperation(value = "Update a Store")
    @PutMapping("/{storeId}")
    public ResponseEntity update(@PathVariable Long storeId, @RequestBody @Valid StoreDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    mapper.storeToDto(storeService.update(storeId, mapper.dtoToStore(dto)))
            );
        } catch (StoreNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @ApiOperation(value = "Find Store by parameters")
    @GetMapping
    public ResponseEntity findByParameters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        storeService.findAllByParameters(name, address)
                                .stream()
                                .map(mapper::storeToDto)
                                .collect(Collectors.toList())
                );
    }
}
