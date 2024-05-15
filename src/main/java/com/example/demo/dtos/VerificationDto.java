package com.example.demo.dtos;

import com.example.demo.entities.Inscription;

public class VerificationDto {
	private String message;
	private boolean valide;
	private UserDto inscription;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	public UserDto getInscription() {
		return inscription;
	}
	public void setInscription(UserDto inscription) {
		this.inscription = inscription;
	}
	
	public VerificationDto(String message, boolean valide, UserDto inscription) {
		super();
		this.message = message;
		this.valide = valide;
		this.inscription = inscription;
	}
	public VerificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
