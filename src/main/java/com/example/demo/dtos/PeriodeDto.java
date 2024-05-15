package com.example.demo.dtos;

import com.example.demo.enuum.Periode;

public class PeriodeDto {
	  private Long idPeriodicite;
		 private Periode periode;
		public Long getIdPeriodicite() {
			return idPeriodicite;
		}
		public void setIdPeriodicite(Long idPeriodicite) {
			this.idPeriodicite = idPeriodicite;
		}
		public Periode getPeriode() {
			return periode;
		}
		public void setPeriode(Periode periode) {
			this.periode = periode;
		}
		 
}
