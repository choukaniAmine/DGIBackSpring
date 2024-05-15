package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.TypeImpot;

public class ObligationFiscaledto {
	private Long idObligation;
	  private Date dateDebut;
	  private Date dateFin;
	  private TypeImpotDto typeImpot;
	  
	public Long getIdObligation() {
		return idObligation;
	}
	public void setIdObligation(Long idObligation) {
		this.idObligation = idObligation;
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
	public TypeImpotDto getTypeImpot() {
		return typeImpot;
	}
	public void setTypeImpot(TypeImpotDto typeImpot) {
		this.typeImpot = typeImpot;
	}
	 
	 
	  
}
