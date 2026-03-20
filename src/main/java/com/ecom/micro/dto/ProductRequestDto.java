package com.ecom.micro.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private String imageUrl;
}
