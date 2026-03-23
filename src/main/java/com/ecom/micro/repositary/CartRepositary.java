package com.ecom.micro.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.micro.model.CartItem;
import com.ecom.micro.model.Product;
import com.ecom.micro.model.User;

@Repository
public interface CartRepositary extends JpaRepository<CartItem, Long>{

	CartItem findByUserAndProduct(User user, Product product);

	void deleteByUserAndProduct(User user, Product product);

	List<CartItem> findByUser(User user);

}
