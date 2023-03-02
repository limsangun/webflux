package com.cos.fluxdemo.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

	@Id
	private Long id;
	
	private final String firstName;
	
	private final String lastName;
}
