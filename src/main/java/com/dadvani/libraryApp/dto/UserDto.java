package com.dadvani.libraryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String username;
	private String password;
	private String fullname;
	private String role;
	private String phoneNumber;
	private boolean membershipStatus;
}
