package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import com.example.demo.enuum.NatureRubrique;
@Entity

@Table(name= "\"detailDeclaration\"")
public class DetailDeclaration {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator19Name")
    @SequenceGenerator(name = "yourGenerator19Name", sequenceName = "DetailDeclaration_seq", allocationSize = 1)
	private Long idDetailDeclaration;
	private String valeur;
	 private NatureRubrique naturerebrique;
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "DetailImpot_id")
	    private DetailImpot detailImpot;
	
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "Declaration_id")
	    private Declaration declaration;

	public Long getIdDetailDeclaration() {
		return idDetailDeclaration;
	}

	public void setIdDetailDeclaration(Long idDetailDeclaration) {
		this.idDetailDeclaration = idDetailDeclaration;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public DetailImpot getDetailImpot() {
		return detailImpot;
	}

	public void setDetailImpot(DetailImpot detailImpot) {
		this.detailImpot = detailImpot;
	}

	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration delcaration) {
		this.declaration = delcaration;
	}

	public NatureRubrique getNaturerebrique() {
		return naturerebrique;
	}

	public void setNaturerebrique(NatureRubrique naturerebrique) {
		this.naturerebrique = naturerebrique;
	}
	  
	  
}
