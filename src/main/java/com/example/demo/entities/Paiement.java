package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Paiement\"")
public class Paiement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator40Name")
    @SequenceGenerator(name = "yourGenerator40Name", sequenceName = "paiement_seq", allocationSize = 1)
	private Long idPaiment;
	private String numeroTransaction;
	private Date datePaiement;
	
	 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "declaration_id")
	 	private Declaration declaration;

	public Long getIdPaiment() {
		return idPaiment;
	}

	public void setIdPaiment(Long idPaiment) {
		this.idPaiment = idPaiment;
	}

	public String getNumeroTransaction() {
		return numeroTransaction;
	}

	public void setNumeroTransaction(String numeroTransaction) {
		this.numeroTransaction = numeroTransaction;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}
	 
}
