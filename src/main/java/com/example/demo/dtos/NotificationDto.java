package com.example.demo.dtos;

import java.util.Date;

public class NotificationDto {
	private Long idNotification;
	private long idReclamation;
	private Date dateReponse;
	private String titre;
	private String Solution;
	private boolean checked;
	private boolean deleted;
	public long getIdReclamation() {
		return idReclamation;
	}
	public void setIdReclamation(long idReclamation) {
		this.idReclamation = idReclamation;
	}
	public Date getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSolution() {
		return Solution;
	}
	public void setSolution(String solution) {
		Solution = solution;
	}

	public Long getIdNotification() {
		return idNotification;
	}
	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
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
	public NotificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotificationDto(Long idNotification, long idReclamation, Date dateReponse, String titre, String solution,
			boolean checked, boolean deleted) {
		super();
		this.idNotification = idNotification;
		this.idReclamation = idReclamation;
		this.dateReponse = dateReponse;
		this.titre = titre;
		Solution = solution;
		this.checked = checked;
		this.deleted = deleted;
	}
	
}
