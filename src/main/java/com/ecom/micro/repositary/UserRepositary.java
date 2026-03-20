package com.ecom.micro.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.micro.model.User;

@Repository
public interface UserRepositary extends JpaRepository<User, Long>{
	
}