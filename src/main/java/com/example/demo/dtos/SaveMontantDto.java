package com.example.demo.dtos;

public class SaveMontantDto {
	private Long idDeclaration;
	private float montantApayer;
	public Long getIdDeclaration() {
		return idDeclaration;
	}
	public void setIdDeclaration(Long idDeclaration) {
		this.idDeclaration = idDeclaration;
	}
	public float getMontantApayer() {
		return montantApayer;
	}
	public void setMontantApayer(float montantApayer) {
		this.montantApayer = montantApayer;
	}
	
}
