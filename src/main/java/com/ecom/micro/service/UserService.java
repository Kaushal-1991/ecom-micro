package com.ecom.micro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.micro.dto.AddressDto;
import com.ecom.micro.dto.UserRequestDto;
import com.ecom.micro.dto.UserResponseDto;
import com.ecom.micro.model.Address;
import com.ecom.micro.model.User;
import com.ecom.micro.repositary.UserRepositary;

@Service
public class UserService {

	private UserRepositary userRepositary;

	public UserService(UserRepositary userRepositary) {
		this.userRepositary = userRepositary;
	}

	public List<UserResponseDto> findAll() {
		return userRepositary.findAll()
				.stream().map(this::mapToUserResponse) //map(user -> mapToUserResponse(user))
				.collect(Collectors.toList());
	}

	public void createUser(UserRequestDto userRequst) {
		User user = new User();
		updateRequestDto(user,userRequst);
		userRepositary.save(user);
	}

	public Optional<UserResponseDto> findById(Long id) {
		return userRepositary.findById(id).map(this::mapToUserResponse);
	}

	public boolean updateUser(Long id, UserRequestDto updatedUser) {
		return userRepositary.findById(id).stream().filter(user -> user.getId().equals(id)).findFirst()
				.map(existingUser -> {
					updateRequestDto(existingUser,updatedUser);
					userRepositary.save(existingUser);
					return true;
				}).orElse(false);
	}

	private void updateRequestDto(User user, UserRequestDto userRequst) {
		user.setFirstName(userRequst.getFirstName());
		user.setLastName(userRequst.getLastName());
		user.setEmail(userRequst.getEmail());
		user.setPhone(userRequst.getPhone());
		
		if(userRequst.getAddress() != null) {
			Address address = new Address();
			address.setCity(userRequst.getAddress().getCity());
			address.setCountry(userRequst.getAddress().getCountry());
			address.setState(userRequst.getAddress().getState());
			address.setStreet(userRequst.getAddress().getStreet());
			address.setZipcode(userRequst.getAddress().getZipcode());
			
			user.setAddress(address);
		}
	}
	
	private UserResponseDto mapToUserResponse(User user) {
		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setId(String.valueOf(user.getId()));
		responseDto.setFirstName(user.getFirstName());
		responseDto.setLastName(user.getLastName());
		responseDto.setEmail(user.getEmail());
		responseDto.setPhone(user.getPhone());
		responseDto.setRole(user.getRole());
		
		if(user.getAddress() != null) {
			AddressDto addressDto = new AddressDto();
			addressDto.setCity(user.getAddress().getCity());
			addressDto.setCountry(user.getAddress().getCountry());
			addressDto.setState(user.getAddress().getState());
			addressDto.setStreet(user.getAddress().getStreet());
			addressDto.setZipcode(user.getAddress().getZipcode());
			responseDto.setAddress(addressDto);
		}
		return responseDto;
	}

}
