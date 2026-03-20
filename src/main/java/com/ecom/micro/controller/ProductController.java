package com.ecom.micro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.micro.dto.ProductRequestDto;
import com.ecom.micro.dto.ProductResponseDto;
import com.ecom.micro.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProduct(){
		List<ProductResponseDto> allProducts = productService.getAllProduct();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
		return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(
			@RequestBody ProductRequestDto productRequestDto,
			@PathVariable Long id) {
		    
		    return productService.updateProduct(id, productRequestDto).map(ResponseEntity::ok) //map(product -> ResponseEntity.ok(product))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePoduct(@PathVariable Long id){
		boolean deleted = productService.deletePoduct(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductResponseDto>> serachProducts(@RequestParam String keyword){
		return ResponseEntity.ok(productService.serachProducts(keyword));
	}
	

}
