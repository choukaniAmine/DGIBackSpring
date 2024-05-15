package com.example.demo.dtos;

import com.example.demo.entities.TypeDeclaration;

public class SaveDeclaration {
private int moisEffet;
private int anneEffet;
private Long idObligation;
private TypeDeclaration type;

public TypeDeclaration getType() {
	return type;
}
public void setType(TypeDeclaration type) {
	this.type = type;
}
public int getMoisEffet() {
	return moisEffet;
}
public void setMoisEffet(int moisEffet) {
	this.moisEffet = moisEffet;
}
public int getAnneEffet() {
	return anneEffet;
}
public void setAnneEffet(int anneEffet) {
	this.anneEffet = anneEffet;
}
public Long getIdObligation() {
	return idObligation;
}
public void setIdObligation(Long idObligation) {
	this.idObligation = idObligation;
}

}
