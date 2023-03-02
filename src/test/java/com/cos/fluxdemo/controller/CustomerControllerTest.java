package com.cos.fluxdemo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cos.fluxdemo.domain.Customer;
import com.cos.fluxdemo.repository.CustomerRepository;

import lombok.Builder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// 통합 테스트
//@SpringBootTest
//@AutoConfigureWebTestClient
@WebFluxTest
class CustomerControllerTest {

	@Autowired
	private WebTestClient webqClient; // 비동기로 http 요청
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	public void 한건찾기_테스트() {
		
		// given
		Mono<Customer> givenData = Mono.just(new Customer("Jack", "Bauer"));
		
		when(customerRepository.findById(1L)).thenReturn(givenData);
		
		webqClient.get().uri("/customer/{id}", 1L)
		.exchange()
		.expectBody()
		.jsonPath("$.firstName").isEqualTo("Jack")
		.jsonPath("$.lastName").isEqualTo("Bauer");
		
	}

}
