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

@Table(name= "\"declaration\"")
public class Declaration {
	@Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator11Name")
	  @SequenceGenerator(name = "yourGenerator11Name", sequenceName = "Declaration_seq", allocationSize = 1)
	  private Long idDeclaration;
	private Date dateDeclaration;
private int moisEffet;
private int anneEffet;
  
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "Obligation_id")
	    private ObligationFiscale obligationFiscale;
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "typeDeclaration_id")
	    private TypeDeclaration type;
	  
	  
	  
	  
	  
	public TypeDeclaration getType() {
		return type;
	}

	public void setType(TypeDeclaration type) {
		this.type = type;
	}

	public int getMoisEffet() {
		return moisEffet;
	}

	public void setMoisEffet(int moisEffet) {
		this.moisEffet = moisEffet;
	}

	public int getAnneEffet() {
		return anneEffet;
	}

	public void setAnneEffet(int anneEffet) {
		this.anneEffet = anneEffet;
	}

	public ObligationFiscale getObligationFiscale() {
		return obligationFiscale;
	}

	public void setObligationFiscale(ObligationFiscale obligationFiscale) {
		this.obligationFiscale = obligationFiscale;
	}

	public Long getIdDeclaration() {
		return idDeclaration;
	}

	public void setIdDeclaration(Long idDeclaration) {
		this.idDeclaration = idDeclaration;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	
    
    
}
