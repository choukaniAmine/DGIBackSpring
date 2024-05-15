package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.paysDtos;
import com.example.demo.entities.Pays;
import com.example.demo.repository.PaysRepository;

@Service
public class PaysService {
@Autowired
private PaysRepository paysRepository;
public paysDtos createPays(paysDtos p) {
	Pays pays=new Pays();
	pays.setLibelle(p.getLibelle());
	
	Pays payscree=paysRepository.save(pays);
	paysDtos paysdtos=new paysDtos();
	paysdtos.setIdPays(payscree.getIdPays());
	paysdtos.setLibelle(payscree.getLibelle());

	return paysdtos;
}
}
