package com.example.demo.dtos;

import com.example.demo.entities.Inscription;

public class PasswordDto {
	private String password;
	private UserDto inscription;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDto getInscription() {
		return inscription;
	}
	public void setInscription(UserDto inscription) {
		this.inscription = inscription;
	}
	
	
}
