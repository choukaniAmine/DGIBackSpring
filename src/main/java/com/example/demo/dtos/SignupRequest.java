package com.example.demo.dtos;

import com.example.demo.entities.Contribuable;
import com.example.demo.enuum.Identifiant;

import lombok.Data;

@Data
public class SignupRequest {
private String email;
private String prenom;
private String nom;
private String password;
private String poste;
private  Contribuable contribuable;
private  Identifiant typeIdentifiant;
private String valeurIdentifiant;

public Identifiant getTypeIdentifiant() {
	return typeIdentifiant;
}
public void setTypeIdentifiant(Identifiant typeIdentifiant) {
	this.typeIdentifiant = typeIdentifiant;
}

public String getValeurIdentifiant() {
	return valeurIdentifiant;
}
public void setValeurIdentifiant(String valeurIdentifiant) {
	this.valeurIdentifiant = valeurIdentifiant;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
