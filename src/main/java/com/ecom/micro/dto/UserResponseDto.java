package com.ecom.micro.dto;

import com.ecom.micro.enums.UserRole;

import lombok.Data;

@Data
public class UserResponseDto {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private UserRole role;
	private AddressDto address;
}
