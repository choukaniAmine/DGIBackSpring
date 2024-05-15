package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.FormeJuridiqueDtos;
import com.example.demo.entities.FormeJuridique;
import com.example.demo.repository.FormeJuridiqueRepository;

@Service
public class FormeJuridiqueService {
@Autowired
private FormeJuridiqueRepository formejuriRepo;
public FormeJuridiqueDtos createFormeJuridique(FormeJuridiqueDtos fm) {
	FormeJuridique f=new FormeJuridique();
	f.setLibelle(fm.getLibelle());
	
	FormeJuridique formeCree=formejuriRepo.save(f);
	FormeJuridiqueDtos formDtos=new FormeJuridiqueDtos();
	formDtos.setIdFormeJuridique(formeCree.getIdFormeJuridique());
	formDtos.setLibelle(formeCree.getLibelle());

	return formDtos;
}
}
