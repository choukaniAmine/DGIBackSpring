package com.example.demo.entities;



import java.util.Date;

import com.example.demo.enuum.Etat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"reclamation\"")
public class Reclamation {
	private static final long serialVersionUID = 1L;
	  @Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator15Name")
	  @SequenceGenerator(name = "yourGenerator15Name", sequenceName = "ObligationFiscale_seq", allocationSize = 1)
	  private Long idReclamation;
	


	    private String titre;

	    private String contenu;

	    private Etat etat;

	     private Date dateReclamation;

	     private String solution;



	     @ManyToOne
	        @JoinColumn(name = "contribuable_id",  nullable = false)
	     private  Contribuable contribuable;



		public Date getDateReclamation() {
			return dateReclamation;
		}



		public void setDateReclamation(Date dateReclamation) {
			this.dateReclamation = dateReclamation;
		}



		public Long getIdReclamation() {
			return idReclamation;
		}



		public void setIdReclamation(Long idReclamation) {
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



		



		public String getSolution() {
			return solution;
		}



		public void setSolution(String solution) {
			this.solution = solution;
		}



		public Contribuable getContribuable() {
			return contribuable;
		}



		public void setContribuable(Contribuable contribuable) {
			this.contribuable = contribuable;
		}



		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	     
}
