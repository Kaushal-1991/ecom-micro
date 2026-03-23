package com.ecom.micro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.micro.dto.OrderResponseDto;
import com.ecom.micro.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@RequestHeader("X-User-Id") String userId){
		
		OrderResponseDto responseDto = orderService.createOrder(userId);
		
		return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
	}
	
}
