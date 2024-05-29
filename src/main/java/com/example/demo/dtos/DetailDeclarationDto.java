package com.example.demo.dtos;

import com.example.demo.entities.Declaration;
import com.example.demo.enuum.NatureRubrique;

public class DetailDeclarationDto {
private Long idDetailDeclaration;
private String valeur;
private Long idDeclaration;

private NatureRubrique naturerebrique;

public NatureRubrique getNaturerebrique() {
	return naturerebrique;
}
public void setNaturerebrique(NatureRubrique naturerebrique) {
	this.naturerebrique = naturerebrique;
}
public Long getIdDeclaration() {
	return idDeclaration;
}
public void setIdDeclaration(Long idDeclaration) {
	this.idDeclaration = idDeclaration;
}
public Long getIdDetailDeclaration() {
	return idDetailDeclaration;
}
public void setIdDetailDeclaration(Long idDetailDeclaration) {
	this.idDetailDeclaration = idDetailDeclaration;
}
public String getValeur() {
	return valeur;
}
public void setValeur(String valeur) {
	this.valeur = valeur;
}

}
