package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Contribuable;
import com.example.demo.entities.ObligationFiscale;

import lombok.Data;

public class DeclarationDto {
	 private Long idDeclaration;
	private Contribuable cd;

	public Long getIdDeclaration() {
		return idDeclaration;
	}
	public void setIdDeclaration(Long idDeclaration) {
		this.idDeclaration = idDeclaration;
	}
	public Contribuable getCd() {
		return cd;
	}
	public void setCd(Contribuable cd) {
		this.cd = cd;
	}
	
}
