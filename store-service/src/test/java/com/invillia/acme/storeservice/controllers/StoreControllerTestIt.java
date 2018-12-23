package com.invillia.acme.storeservice.controllers;

import com.invillia.acme.storeservice.dtos.StoreDto;
import com.invillia.acme.storeservice.repositories.StoreRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreControllerTestIt {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private StoreRepository storeRepository;

    @After
    public void after() {
        storeRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewOrder() {

        StoreDto dto = StoreDto.builder().name("New Store").address("Av Bady Bassitt").build();
        HttpEntity<Object> storeDto = getHttpEntity(dto);
        ResponseEntity<StoreDto> response = template.postForEntity("/stores", storeDto, StoreDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        StoreDto dtoRes = response.getBody();
        assertNotNull(dtoRes);
        assertNotNull(dtoRes.getId());
        assertEquals(dto.getAddress(), dtoRes.getAddress());
        assertEquals(dto.getName(), dtoRes.getName());
    }

    @Test
    @Sql(scripts = "/store-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindAllWhenNoParameters() {

        String uri = "/stores";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(6, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/store-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindAllByAllParameters() {

        String uri = "/stores?name=Mu&address=av";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/store-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindAllByName() {

        String uri = "/stores?name=am";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/store-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindAllByAddress() {

        String uri = "/stores?address=rua";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}