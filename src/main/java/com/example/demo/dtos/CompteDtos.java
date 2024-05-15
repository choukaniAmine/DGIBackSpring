package com.example.demo.dtos;

import com.example.demo.entities.Inscription;
import com.example.demo.enuum.UserRole;

public class CompteDtos {
	private Long idCompte;

    private String email;
    private String password;
    private UserRole userRole;
    private UserDto inscription;
	public Long getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public UserDto getInscription() {
		return inscription;
	}
	public void setInscription(UserDto inscription) {
		this.inscription = inscription;
	}
	
}
