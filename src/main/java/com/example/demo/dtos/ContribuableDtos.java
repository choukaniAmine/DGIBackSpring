package com.example.demo.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.Activite;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.FormeJuridique;
import com.example.demo.entities.Pays;

public class ContribuableDtos {
	private Long idContribuable;
    private int matriculeFiscale;
    private String nomCommercial;
    private String email;
    private String adress;
    private Date dateDeMatriculation;
  
    private FormeJuridique formeJuridique;
    private Pays pays;
    private  Activite activites;

    private String raisonsSociale;
    private String directeur;
    
    
	public String getDirecteur() {
		return directeur;
	}
	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}
	public String getRaisonsSociale() {
		return raisonsSociale;
	}
	public void setRaisonsSociale(String raisonsSociale) {
		this.raisonsSociale = raisonsSociale;
	}
	public FormeJuridique getFormeJuridique() {
		return formeJuridique;
	}
	public void setFormeJuridique(FormeJuridique formeJuridique) {
		this.formeJuridique = formeJuridique;
	}
	public Activite getActivites() {
		return activites;
	}
	public void setActivites(Activite activites) {
		this.activites = activites;
	}
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	public Long getIdContribuable() {
		return idContribuable;
	}
	public void setIdContribuable(Long idContribuable) {
		this.idContribuable = idContribuable;
	}
	public int getMatriculeFiscale() {
		return matriculeFiscale;
	}
	public void setMatriculeFiscale(int matriculeFiscale) {
		this.matriculeFiscale = matriculeFiscale;
	}
	public String getNomCommercial() {
		return nomCommercial;
	}
	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getDateDeMatriculation() {
		return dateDeMatriculation;
	}
	public void setDateDeMatriculation(Date dateDeMatriculation) {
		this.dateDeMatriculation = dateDeMatriculation;
	}
	
    
}
