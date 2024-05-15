package com.example.demo.entities;

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

@Table(name= "\"echeance\"")
public class Echeance {
	@Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator10Name")
	  @SequenceGenerator(name = "yourGenerator10Name", sequenceName = "Echeance_seq", allocationSize = 1)
	  private Long idEcheance;
	 private int jour;
	 private int mois;
	 private int numeroEcheance;
	 private int annee;
	 
	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "typeImpot_id")
	    private TypeImpot typeImpot;
	    
	public TypeImpot getTypeImpot() {
			return typeImpot;
		}
		public void setTypeImpot(TypeImpot typeImpot) {
			this.typeImpot = typeImpot;
		}
	public Long getIdEcheance() {
		return idEcheance;
	}
	public void setIdEcheance(Long idEcheance) {
		this.idEcheance = idEcheance;
	}
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getNumeroEcheance() {
		return numeroEcheance;
	}
	public void setNumeroEcheance(int numeroEcheance) {
		this.numeroEcheance = numeroEcheance;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	 
}
