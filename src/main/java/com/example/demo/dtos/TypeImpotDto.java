package com.example.demo.dtos;

import com.example.demo.entities.Periodicite;

public class TypeImpotDto {
	
		 private String libelle;
		 private PeriodeDto periodicite;
	
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public PeriodeDto getPeriodicite() {
			return periodicite;
		}
		public void setPeriodicite(PeriodeDto periodicite) {
			this.periodicite = periodicite;
		}
	
		 
}
