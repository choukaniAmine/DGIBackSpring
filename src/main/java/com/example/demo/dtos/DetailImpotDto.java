package com.example.demo.dtos;

import com.example.demo.entities.TypeImpot;
import com.example.demo.enuum.NatureRubrique;
import com.example.demo.enuum.TypeDeDetailImpot;

public class DetailImpotDto {
	 
	private String libelle;
	private TypeDeDetailImpot typeDetail;
	 private boolean calculable;
	 private String formule;
	private int ordre;
	private boolean obligatoire;
	 private TypeImpotDto typeImpot;

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public TypeDeDetailImpot getTypeDetail() {
		return typeDetail;
	}
	public void setTypeDetail(TypeDeDetailImpot typeDetail) {
		this.typeDetail = typeDetail;
	}

	public boolean isCalculable() {
		return calculable;
	}
	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	public boolean isObligatoire() {
		return obligatoire;
	}
	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}
	public TypeImpotDto getTypeImpot() {
		return typeImpot;
	}
	public void setTypeImpot(TypeImpotDto typeImpot) {
		this.typeImpot = typeImpot;
	}

	 
}
