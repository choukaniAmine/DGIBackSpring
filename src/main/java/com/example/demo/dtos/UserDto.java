package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Contribuable;
import com.example.demo.enuum.Identifiant;
import com.example.demo.enuum.UserRole;

import lombok.Data;

@Data
public class UserDto {
private Long id;
private String name;
private String email;
private String password ;
private String randomCode;
private boolean enabled;
private boolean nonLocked;
private Date date;
private UserRole userRole;
private String poste;
public String valeurIdentifiant;
public Identifiant typeIdentifiant;
private  Contribuable contribuable;


public String getValeurIdentifiant() {
	return valeurIdentifiant;
}
public void setValeurIdentifiant(String valeurIdentifiant) {
	this.valeurIdentifiant = valeurIdentifiant;
}
public Identifiant getTypeIdentifiant() {
	return typeIdentifiant;
}
public void setTypeIdentifiant(Identifiant typeIdentifiant) {
	this.typeIdentifiant = typeIdentifiant;
}
public Contribuable getContribuable() {
	return contribuable;
}
public void setContribuable(Contribuable contribuable) {
	this.contribuable = contribuable;
}
public String getPoste() {
	return poste;
}
public void setPoste(String poste) {
	this.poste = poste;
}
public boolean isNonLocked() {
	return nonLocked;
}
public void setNonLocked(boolean nonLocked) {
	this.nonLocked = nonLocked;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public String getRandomCode() {
	return randomCode;
}
public void setRandomCode(String randomCode) {
	this.randomCode = randomCode;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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

}
