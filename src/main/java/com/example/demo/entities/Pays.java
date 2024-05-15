package com.example.demo.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"pays\"")
public class Pays {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator4Name")
    @SequenceGenerator(name = "yourGenerator4Name", sequenceName = "pays_seq", allocationSize = 1)
	private Long idPays;
	private String libelle;
	@OneToMany(mappedBy = "pays")
	@JsonIgnore
	private Set<Contribuable> contribuables;
	
	public Set<Contribuable> getContribuables() {
		return contribuables;
	}
	public void setContribuables(Set<Contribuable> contribuables) {
		this.contribuables = contribuables;
	}
	public Long getIdPays() {
		return idPays;
	}
	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
