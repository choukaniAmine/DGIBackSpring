package com.example.demo.entities;

import com.example.demo.dtos.PeriodeDto;
import com.example.demo.enuum.Periode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity

@Table(name= "\"periodicite\"")
public class Periodicite {
	@Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator9Name")
	  @SequenceGenerator(name = "yourGenerator9Name", sequenceName = "Periodicite_seq", allocationSize = 1)
	  private Long idPeriodicite;
	 private Periode periode;
	 public PeriodeDto getPeriodeDto() {
		 PeriodeDto per=new PeriodeDto();
		 per.setIdPeriodicite(idPeriodicite);
		 per.setPeriode(periode);
		 return per;
	 }
	public Long getIdPeriodicite() {
		return idPeriodicite;
	}
	public void setIdPeriodicite(Long idPeriodicite) {
		this.idPeriodicite = idPeriodicite;
	}
	public Periode getPeriode() {
		return periode;
	}
	public void setPeriode(Periode libelle) {
		this.periode = libelle;
	}
	 
}
