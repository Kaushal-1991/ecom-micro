package com.ecom.micro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.micro.dto.CartItemRequestDto;
import com.ecom.micro.model.CartItem;
import com.ecom.micro.model.Product;
import com.ecom.micro.model.User;
import com.ecom.micro.repositary.CartRepositary;
import com.ecom.micro.repositary.ProductRepositary;
import com.ecom.micro.repositary.UserRepositary;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

	private final CartRepositary cartRepositary;
	private final ProductRepositary productRepositary;
	private final UserRepositary userRepositary;
	
	public boolean addItemCart(String userId, CartItemRequestDto request) {
		Optional<Product> optProduct = productRepositary.findById(request.getProductId());
		if(optProduct.isEmpty())
			return false;
		
		Product product = optProduct.get();

		if(product.getStockQuantity() < request.getQuantity())
			return false;
		
		Optional<User> optUser = userRepositary.findById(Long.valueOf(userId));
		if(optUser.isEmpty())
			return false;
		
		User user = optUser.get();
		
		CartItem existingItem = cartRepositary.findByUserAndProduct(user,product);
		
		if(existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
			existingItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
			
			cartRepositary.save(existingItem);
		}else {
			CartItem cartItem = new CartItem();
			cartItem.setUser(user);
			cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			
			cartRepositary.save(cartItem);
		}
		
		return true;
	}

	public boolean deleteItemCart(String userId, Long productId) {
		Optional<Product> optProduct = productRepositary.findById(productId);
		Optional<User> optUser = userRepositary.findById(Long.valueOf(userId));
		
		if(optUser.isPresent() && optProduct.isPresent()) {
			cartRepositary.deleteByUserAndProduct(optUser.get(),optProduct.get());
			return true;
		}
		return false;
	}

	public List<CartItem> getCart(String userId) {	
//		Optional<User> optUser = userRepositary.findById(Long.valueOf(userId));
//		if(optUser.isEmpty())
//			return List.of();
//		User user = optUser.get();
//		List<CartItem> cartItem = cartRepositary.findByUser(user);
//		
//		return cartItem;
		
		return userRepositary.findById(Long.valueOf(userId))
				             .map(cartRepositary::findByUser)
				             .orElseGet(List::of);
	}

}
