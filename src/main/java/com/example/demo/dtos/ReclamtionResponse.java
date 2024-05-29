package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Declaration;
import com.example.demo.enuum.Etat;

public class ReclamtionResponse {
	 private long idReclamation;
	    private String titre;
	    private String contenu;
	    private Etat etat;
	    private Date dateReclamation;
	    private String solution;
	  
	    private Declaration declaration;
	    private ContribuableDtos contribuable;
	    
	
		public long getIdReclamation() {
			return idReclamation;
		}
		public void setIdReclamation(long idReclamation) {
			this.idReclamation = idReclamation;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
		public String getContenu() {
			return contenu;
		}
		public void setContenu(String contenu) {
			this.contenu = contenu;
		}
		public Etat getEtat() {
			return etat;
		}
		public void setEtat(Etat etat) {
			this.etat = etat;
		}
		public Date getDateReclamation() {
			return dateReclamation;
		}
		public void setDateReclamation(Date dateReclamation) {
			this.dateReclamation = dateReclamation;
		}
		public String getSolution() {
			return solution;
		}
		public void setSolution(String solution) {
			this.solution = solution;
		}
		public Declaration getDeclaration() {
			return declaration;
		}
		public void setDeclaration(Declaration declaration) {
			this.declaration = declaration;
		}
		public ContribuableDtos getContribuable() {
			return contribuable;
		}
		public void setContribuable(ContribuableDtos contribuable) {
			this.contribuable = contribuable;
		}
	    
}
