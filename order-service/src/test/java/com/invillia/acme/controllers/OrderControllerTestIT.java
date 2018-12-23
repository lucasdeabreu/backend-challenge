package com.invillia.acme.controllers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.utils.OrderSampleData;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTestIT {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private OrderRepository orderRepository;

    @After
    public void after() {
        orderRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewOrder() {

        OrderDto dto = OrderSampleData.getOrderDtoSample();
        HttpEntity<Object> orderDto = getHttpEntity(dto);
        ResponseEntity<OrderDto> response = template.postForEntity("/orders", orderDto, OrderDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    @Sql(scripts = "/order-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindByAllParameters() {

        String uri = "/orders?" +
                "address=rua" +
                "&confirmationDate=2018-12-22T20:00:00" +
                "&status=NEW";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}