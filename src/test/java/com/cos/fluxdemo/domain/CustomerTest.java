package com.cos.fluxdemo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.cos.fluxdemo.DBinit;
import com.cos.fluxdemo.repository.CustomerRepository;

import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(DBinit.class)
class CustomerTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void 한건찾기_테스트() {
		StepVerifier
		.create(customerRepository.findById(2L))
		.expectNextMatches((c)->{
			return c.getFirstName().equals("Chloe");
		})
		.expectComplete()
		.verify();
	}

}
