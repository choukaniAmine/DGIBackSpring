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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"obligationFiscale\"")
public class ObligationFiscale {
	 private static final long serialVersionUID = 1L;
	  @Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator7Name")
	  @SequenceGenerator(name = "yourGenerator7Name", sequenceName = "ObligationFiscale_seq", allocationSize = 1)
	  private Long idObligationFiscale;
	  private Date dateDebut;
	  private Date dateFin;
	  
	  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "typeImpot_id")
	    private TypeImpot typeImpot;
	  
	  
	  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "Contribuale_id")
	    private Contribuable contribuable;
	  
	
	  
	
	public TypeImpot getTypeImpot() {
		return typeImpot;
	}
	public void setTypeImpot(TypeImpot typeImpot) {
		this.typeImpot = typeImpot;
	}
	public Contribuable getContribuable() {
		return contribuable;
	}
	public void setContribuable(Contribuable contribualbe) {
		this.contribuable = contribualbe;
	}
	public Long getIdObligationFiscale() {
		return idObligationFiscale;
	}
	public void setIdObligationFiscale(Long idObligationFiscale) {
		this.idObligationFiscale = idObligationFiscale;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
}
