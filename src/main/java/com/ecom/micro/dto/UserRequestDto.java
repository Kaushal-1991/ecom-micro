package com.ecom.micro.dto;

import lombok.Data;

@Data
public class UserRequestDto {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private AddressDto address;
}
