package com.ecom.micro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.micro.dto.CartItemRequestDto;
import com.ecom.micro.model.CartItem;
import com.ecom.micro.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;

	@PostMapping
	public ResponseEntity<String> addItemCart(@RequestHeader("X-User-Id") String userId,
			@RequestBody CartItemRequestDto request){
		    
		if(!cartService.addItemCart(userId,request)) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found or Product quantity is less or User if not found");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Void> deletedItemCart(@RequestHeader("X-User-Id") String userId,
			@PathVariable("id") Long productId){
		
		boolean deleted = cartService.deleteItemCart(userId,productId);
		
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-Id") String userId){
		
		List<CartItem> cartItemList = cartService.getCart(userId);
		return ResponseEntity.ok(cartItemList);
	}
}
