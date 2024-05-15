package com.example.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.dtos.ContribuableDtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
@Entity

@Table(name= "\"contribuable\"")
public class Contribuable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator2Name")
    @SequenceGenerator(name = "yourGenerator2Name", sequenceName = "contribuable_seq", allocationSize = 1)
	private Long idContribuable;
    private int matriculeFiscale;
    private String nomCommercial;
    private String email;
    private String adress;
    private Date dateDeMatriculation;
    private String raisonsSociale;
  private String directeur;
  
@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "formejuridique_id")
    private FormeJuridique formeJuridique;
 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pays_id")
    private Pays pays;
 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
 @JoinColumn(name = "activite_id")
 private  Activite activites;
   
	


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
public Pays getPays() {
	return pays;
}
public void setPays(Pays pays) {
	this.pays = pays;
}
public Activite getActivites() {
	return activites;
}
public void setActivites(Activite activites) {
	this.activites = activites;
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
	public ContribuableDtos getContribuable() {
        ContribuableDtos contribuable=new ContribuableDtos();
        contribuable.setActivites(activites);
        contribuable.setAdress(adress);
        contribuable.setDateDeMatriculation(dateDeMatriculation);
        contribuable.setDirecteur(directeur);
        contribuable.setFormeJuridique(formeJuridique);
        contribuable.setMatriculeFiscale(matriculeFiscale);
        contribuable.setEmail(email);
        contribuable.setRaisonsSociale(raisonsSociale);
        contribuable.setPays(pays);
        contribuable.setNomCommercial(nomCommercial);
        return contribuable;

    }
    
}
