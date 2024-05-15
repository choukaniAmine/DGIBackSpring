package com.example.demo.entities;

import com.example.demo.dtos.TypeDeclarationDto;
import com.example.demo.enuum.TypeDeDeclaration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"typeDeclaration\"")
public class TypeDeclaration {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator20Name")
    @SequenceGenerator(name = "yourGenerator20Name", sequenceName = "TypeDeclaration_seq", allocationSize = 1)
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
	@Override
	public String toString() {
		return "TypeDeclaration [idTypeDeclaration=" + idTypeDeclaration + ", libelle=" + libelle + "]";
	}
	public TypeDeclarationDto getTypeDeclaration() {
		TypeDeclarationDto td=new TypeDeclarationDto();
		td.setIdTypeDeclaration(idTypeDeclaration);
		td.setLibelle(libelle);
		return td;
	}
	
}
