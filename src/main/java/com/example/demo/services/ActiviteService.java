package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ActiviteDtos;
import com.example.demo.entities.Activite;
import com.example.demo.repository.ActiviteRepository;

@Service
public class ActiviteService {
@Autowired
private ActiviteRepository activiteRepo;
public ActiviteDtos CreateActvite(ActiviteDtos ad) {
	Activite a=new Activite();
	a.setLibelle(ad.getLibelle());
	
	Activite activiteCree=activiteRepo.save(a);
	ActiviteDtos activiteDtos=new ActiviteDtos();
	activiteDtos.setLibelle(activiteCree.getLibelle());
	
	activiteDtos.setIdActivite(activiteCree.getIdActivite());
	return activiteDtos;
}

}
