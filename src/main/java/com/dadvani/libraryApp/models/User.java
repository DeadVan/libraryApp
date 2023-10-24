package com.dadvani.libraryApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String fullName;
	private String role;
	private String phoneNumber = "";
	private Boolean membershipStatus = false;

	public User(String username, String password, String fullName,
				String role,String phoneNumber,boolean membershipStatus) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.membershipStatus = membershipStatus;
	}
}
