package com.ecom.micro.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.micro.model.Product;

@Repository
public interface ProductRepositary extends JpaRepository<Product, Long>{

	List<Product> findByActiveTrue();

	@Query("SELECT p FROM products p WHERE p.active = true AND p.stockQuantity > 0 AND " +
		       "(LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) " +
		       "OR CAST(p.price as string) LIKE CONCAT('%',:keyword,'%'))")
	List<Product> searchProduct(@Param("keyword") String keyword);

}
