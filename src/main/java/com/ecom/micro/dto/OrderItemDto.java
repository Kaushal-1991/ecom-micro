package com.ecom.micro.dto;

import java.math.BigDecimal;

import com.ecom.micro.model.Product;

import lombok.Data;

@Data
public class OrderItemDto {

	private Long id;
	private Long productId;
	private Product product;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal subTotal;
}
