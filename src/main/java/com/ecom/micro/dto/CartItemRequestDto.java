package com.ecom.micro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemRequestDto {

	private Long productId;
	private Integer quantity;
}
