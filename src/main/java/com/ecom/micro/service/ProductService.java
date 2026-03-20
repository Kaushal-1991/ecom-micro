package com.ecom.micro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.micro.dto.ProductRequestDto;
import com.ecom.micro.dto.ProductResponseDto;
import com.ecom.micro.model.Product;
import com.ecom.micro.repositary.ProductRepositary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepositary productRepositary;

	public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
		Product product = new Product();
		Product savedProduct = updateProductFromRequest(product, productRequestDto);
		productRepositary.save(product);
		return mapToProductResponse(savedProduct);
	}

	public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto productRequestDto) {
		Optional<Product> product = productRepositary.findById(id);
		return product.map(existingproduct -> {
			updateProductFromRequest(existingproduct, productRequestDto);
			Product save = productRepositary.save(existingproduct);
			return mapToProductResponse(save);
		});
	}

	public List<ProductResponseDto> getAllProduct() {
		return productRepositary.findByActiveTrue().stream().map(this::mapToProductResponse)
				.collect(Collectors.toList());
	}

	public boolean deletePoduct(Long id) {
		return productRepositary.findById(id).map(product -> {
			product.setActive(false);
			productRepositary.save(product);
			return true;
		}).orElse(false);
	}

	public List<ProductResponseDto> serachProducts(String keyword) {

		return productRepositary.searchProduct(keyword)
				.stream().map(product -> mapToProductResponse(product))
				.collect(Collectors.toList());
	}

	private ProductResponseDto mapToProductResponse(Product product) {
		ProductResponseDto response = new ProductResponseDto();
		response.setActive(product.getActive());
		response.setDescription(product.getDescription());
		response.setId(product.getId());
		response.setImageUrl(product.getImageUrl());
		response.setName(product.getName());
		response.setPrice(product.getPrice());
		response.setStockQuantity(product.getStockQuantity());
		return response;
	}

	private Product updateProductFromRequest(Product product, ProductRequestDto productRequestDto) {
		product.setName(productRequestDto.getName());
		product.setDescription(productRequestDto.getDescription());
		product.setImageUrl(productRequestDto.getImageUrl());
		product.setPrice(productRequestDto.getPrice());
		product.setStockQuantity(productRequestDto.getStockQuantity());
		return product;
	}
}
