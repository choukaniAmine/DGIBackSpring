package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity

@Table(name= "\"activite\"")
public class Activite {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator1Name")
    @SequenceGenerator(name = "yourGenerator1Name", sequenceName = "activite_seq", allocationSize = 1)
	private Long idActivite;
	private String libelle;
	@ManyToMany(mappedBy = "activites")
	@JsonIgnore
    private Set<Contribuable> contribuables = new HashSet<>();
	
	public Set<Contribuable> getContribuables() {
		return contribuables;
	}
	public void setContribuables(Set<Contribuable> contribuables) {
		this.contribuables = contribuables;
	}

	public Long getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(Long idActivite) {
		this.idActivite = idActivite;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
