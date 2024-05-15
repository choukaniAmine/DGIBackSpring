package com.example.demo.dtos;

import com.example.demo.enuum.TypeDeDeclaration;

public class TypeDeclarationDto {
	private Long idTypeDeclaration;
	public TypeDeDeclaration libelle;
	public Long getIdTypeDeclaration() {
		return idTypeDeclaration;
	}
	public void setIdTypeDeclaration(Long idTypeDeclaration) {
		this.idTypeDeclaration = idTypeDeclaration;
	}
	public TypeDeDeclaration getLibelle() {
		return libelle;
	}
	public void setLibelle(TypeDeDeclaration libelle) {
		this.libelle = libelle;
	}
	
}
