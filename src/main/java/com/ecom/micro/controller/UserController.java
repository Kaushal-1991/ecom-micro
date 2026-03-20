package com.ecom.micro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.micro.dto.UserRequestDto;
import com.ecom.micro.dto.UserResponseDto;
import com.ecom.micro.model.User;
import com.ecom.micro.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/api/user")
	public ResponseEntity<List<UserResponseDto>> getAllUSer(){
		List<UserResponseDto> allUsers = userService.findAll();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
	
	@PostMapping("/api/user")
	public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequst) {
		userService.createUser(userRequst);
		return ResponseEntity.ok("User added successfully !!!");
	}
	
	@GetMapping("/api/user/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
		return  userService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	} 
	
	@PutMapping("/api/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserRequestDto userUpddated){
		boolean updated = userService.updateUser(id,userUpddated);
		if(updated) {
			return ResponseEntity.ok("User Updated successfully");
		}else {
			return ResponseEntity.notFound().build();
		}
		
//		return  userService.findById(id)
//				.map(ResponseEntity::ok)
//				.orElseGet(()->ResponseEntity.notFound().bSuild());
	} 
	
}
