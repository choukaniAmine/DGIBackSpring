package com.example.demo.entities;

import com.example.demo.enuum.NatureRubrique;
import com.example.demo.enuum.TypeDeDetailImpot;

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

@Table(name= "\"detailImpot\"")
public class DetailImpot {
	@Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator12Name")
	  @SequenceGenerator(name = "yourGenerator12Name", sequenceName = "DetailImpot_seq", allocationSize = 1)
	  private Long idDetail;
	private String libelle;
	private TypeDeDetailImpot typeDetail;
	private NatureRubrique natureRubrique;
	private int ordre;
	private boolean obligatoire;
	
	   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "typeImpot_id")
	    private TypeImpot typeImpot;
	    
	public TypeImpot getTypeImpot() {
		return typeImpot;
	}
	public void setTypeImpot(TypeImpot typeImpot) {
		this.typeImpot = typeImpot;
	}
	public Long getIdDetail() {
		return idDetail;
	}
	public void setIdDetail(Long idDetail) {
		this.idDetail = idDetail;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public TypeDeDetailImpot getTypeDetail() {
		return typeDetail;
	}
	public void setTypeDetail(TypeDeDetailImpot typeDetail) {
		this.typeDetail = typeDetail;
	}
	public NatureRubrique getNatureRubrique() {
		return natureRubrique;
	}
	public void setNatureRubrique(NatureRubrique natureRubrique) {
		this.natureRubrique = natureRubrique;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	public boolean isObligatoire() {
		return obligatoire;
	}
	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}
	@Override
	public String toString() {
		return "DetailImpot [idDetail=" + idDetail + ", libelle=" + libelle + ", typeDetail=" + typeDetail
				+ ", natureRubrique=" + natureRubrique + ", ordre=" + ordre + ", obligatoire=" + obligatoire
				+ ", typeImpot=" + typeImpot + "]";
	}
	
	
	
}
