package com.invillia.acme.controllers;

import com.invillia.acme.dtos.OrderDto;
import com.invillia.acme.dtos.OrderItemDto;
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
import static org.junit.Assert.assertNotNull;

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

        OrderDto dtoRes = response.getBody();
        assertNotNull(dtoRes);
        assertEquals(dto.getAddress(), dtoRes.getAddress());
        assertEquals(dto.getConfirmationDate(), dtoRes.getConfirmationDate());
        assertEquals(dto.getStatus(), dtoRes.getStatus());

        for (int i = 0; i < dto.getItems().size(); i++) {
            OrderItemDto item = dto.getItems().get(i);
            OrderItemDto itemRes = dtoRes.getItems().get(i);

            assertEquals(item.getDescription(), itemRes.getDescription());
            assertEquals(item.getQuantity(), itemRes.getQuantity());
            assertEquals(item.getUnitPrice(), itemRes.getUnitPrice());
        }
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

    @Test
    @Sql(scripts = "/order-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindByAddress() {
        String uri = "/orders?address=rua jo";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/order-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindByConfirmationDate() {
        String uri = "/orders?confirmationDate=2018-12-20T10:00:00";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/order-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindByStatus() {
        String uri = "/orders?status=REFUND";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @Sql(scripts = "/order-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldFindAllWhenNoParameter() {
        String uri = "/orders";
        ResponseEntity<List> response = template.getForEntity(uri, List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(5, Objects.requireNonNull(response.getBody()).size());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}