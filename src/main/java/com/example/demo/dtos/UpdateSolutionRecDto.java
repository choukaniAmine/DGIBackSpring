package com.example.demo.dtos;

import com.example.demo.enuum.Etat;

public class UpdateSolutionRecDto {
private long idReclamation;
private String solution;
private Etat etat;

public Etat getEtat() {
	return etat;
}
public void setEtat(Etat etat) {
	this.etat = etat;
}
public long getIdReclamation() {
	return idReclamation;
}
public void setIdReclamation(long idReclamation) {
	this.idReclamation = idReclamation;
}
public String getSolution() {
	return solution;
}
public void setSolution(String solution) {
	this.solution = solution;
}

}
