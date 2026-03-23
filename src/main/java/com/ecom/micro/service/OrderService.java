package com.ecom.micro.service;

import org.springframework.stereotype.Service;

import com.ecom.micro.dto.OrderResponseDto;
import com.ecom.micro.repositary.OrderRepositary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepositary orderRepositary;

	public OrderResponseDto createOrder(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
