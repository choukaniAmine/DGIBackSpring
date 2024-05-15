package com.example.demo.entities;

import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.TypeImpotDto;

import jakarta.persistence.CascadeType;
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
@Entity

@Table(name= "\"typeImpot\"")
public class TypeImpot {
	 @Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator8Name")
	  @SequenceGenerator(name = "yourGenerator8Name", sequenceName = "TypeImpot_seq", allocationSize = 1)
	  private Long idTypeImpot;
	 private String libelle;
	 private String formule;

	    
	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "periodicte_id")
	    private Periodicite periodicite;
	    
	    
public TypeImpotDto getImpot() {
	TypeImpotDto impot=new TypeImpotDto();
	impot.setLibelle(libelle);
	PeriodeDto per=new PeriodeDto();
	per.setIdPeriodicite(periodicite.getIdPeriodicite());
	per.setPeriode(periodicite.getPeriode());
	impot.setPeriodicite(per);
	return impot;
}

		public String getFormule() {
	return formule;
}

public void setFormule(String formule) {
	this.formule = formule;
}

		public Periodicite getPeriodicite() {
			return periodicite;
		}
		public void setPeriodicite(Periodicite periodicite) {
			this.periodicite = periodicite;
		}
	public Long getIdTypeImpot() {
		return idTypeImpot;
	}
	public void setIdTypeImpot(Long idTypeImpot) {
		this.idTypeImpot = idTypeImpot;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "TypeImpot [idTypeImpot=" + idTypeImpot + ", libelle=" + libelle + ", periodicite=" + periodicite + "]";
	}
	 
	 
}
