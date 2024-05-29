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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator77Name")
    @SequenceGenerator(name = "yourGenerator77Name", sequenceName = "notification_seq", allocationSize = 1)
	private Long idNotification;
	
	
	private Date dateNotification;
	
	
	private boolean checked;
	
	private boolean deleted;
	
	 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	    @JoinColumn(name = "reclamation_id")
	private Reclamation reclamation;

	public Long getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}

	public Date getDateNotification() {
		return dateNotification;
	}

	public void setDateNotification(Date dateNotification) {
		this.dateNotification = dateNotification;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
	 
}
