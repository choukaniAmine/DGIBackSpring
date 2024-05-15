package com.example.demo.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"formeJuridique\"")
public class FormeJuridique {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator3Name")
    @SequenceGenerator(name = "yourGenerator3Name", sequenceName = "forme_juridique_seq", allocationSize = 1)
	private Long idFormeJuridique;
	private String libelle;
	
	@OneToMany(mappedBy = "formeJuridique")
	@JsonIgnore
    private List<Contribuable> contribuables;
	
	public List<Contribuable> getContribuables() {
		return contribuables;
	}
	public void setContribuables(List<Contribuable> contribuables) {
		this.contribuables = contribuables;
	}
	public Long getIdFormeJuridique() {
		return idFormeJuridique;
	}
	public void setIdFormeJuridique(Long idFormeJuridique) {
		this.idFormeJuridique = idFormeJuridique;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
