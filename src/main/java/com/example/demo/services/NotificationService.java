package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.NotificationDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Reclamation;
import com.example.demo.repository.ContribuableRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.ReclamationRepository;

@Service
public class NotificationService {
	@Autowired
	private ReclamationRepository reclamationrepo;
	@Autowired
	private NotificationRepository notificationRepo;
	@Autowired
	private  SimpMessagingTemplate template ;
	@Autowired
	private ContribuableRepository contribuablerepo;
	

	public void creatNotification(Long id, String Solution) {
		Optional<Reclamation> reclamation=reclamationrepo.findById(id);
		if(reclamation.isPresent()) {
			Notification notif=new Notification();
			notif.setDateNotification(new Date());
			notif.setChecked(false);
			notif.setDeleted(false);
			notif.setReclamation(reclamation.get());
			String matriculeFiscale = String.valueOf(reclamation.get().getContribuable().getMatriculeFiscale());
			NotificationDto notification=new NotificationDto();
			notification.setDateReponse(notif.getDateNotification());
			notification.setIdReclamation(id);
			notification.setSolution(Solution);
			notification.setTitre(reclamation.get().getTitre());
			
			template.convertAndSendToUser(matriculeFiscale,"/queue/notification",notification);
			notificationRepo.save(notif);
			
			
		}
		
	}

	
	public List<NotificationDto> getNotificationByMatricule(int matricule) {
	    Optional<Contribuable> contribuable = contribuablerepo.findByMatriculeFiscale(matricule);
	    if (contribuable.isPresent()) {
	        List<Reclamation> reclamations = reclamationrepo.findByContribuable(contribuable.get());

	        List<NotificationDto> notificationDtos = new ArrayList<>();

	        // Loop through each reclamation to fetch the associated notifications
	        for (Reclamation reclamation : reclamations) {
	            List<Notification> notifications = notificationRepo.findByReclamation(reclamation);
	            for (Notification notification : notifications) {
	                NotificationDto dto = new NotificationDto(
	                	notification.getIdNotification(),
	                    reclamation.getIdReclamation(), // Assuming `getId()` returns the id of the reclamation
	                    notification.getDateNotification(), // Assuming `getDateReponse()` returns the response date
	                    notification.getReclamation().getTitre(), // Assuming `getTitre()` returns the title
	                    notification.getReclamation().getSolution(),
	                    notification.isChecked(),
	                    notification.isDeleted()// Assuming `getSolution()` returns the solution
	                );
	                notificationDtos.add(dto);
	            }
	        }
	        return notificationDtos;
	    }
	    return Collections.emptyList();
	}
	
	public void updateNotification(Long id) {
		Optional<Notification> notif=notificationRepo.findById(id);
		if(notif.isPresent()) {
			notif.get().setChecked(true);
			notificationRepo.save(notif.get());
		}
		
	}
	public void updateDeleted(long id) {
		Optional<Notification> notif=notificationRepo.findById(id);
		if(notif.isPresent()) {
			notif.get().setDeleted(true);
			notificationRepo.save(notif.get());
		}
		
	}
}
