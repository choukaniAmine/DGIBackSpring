package com.example.demo.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ContribuableDtos;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Contribuable;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.ContribuableRepository;

@Service
public class ContribuableService {
@Autowired 
private ContribuableRepository contribualbeRepository;
@Autowired
private CompteRepository compteRepository;
public ContribuableDtos createContribuable(ContribuableDtos cd) {
	Contribuable c=new Contribuable();
	c.setEmail(cd.getEmail());
	c.setAdress(cd.getAdress());
	c.setNomCommercial(cd.getNomCommercial());
	c.setMatriculeFiscale(cd.getMatriculeFiscale());
	c.setDateDeMatriculation(cd.getDateDeMatriculation());
	c.setFormeJuridique(cd.getFormeJuridique());
	c.setPays(cd.getPays());
	c.setRaisonsSociale(cd.getRaisonsSociale());
	c.setActivites(cd.getActivites());
	c.setDirecteur(cd.getDirecteur());
	c.setRaisonsSociale(cd.getRaisonsSociale());
	Contribuable contribuablecree=contribualbeRepository.save(c);
	ContribuableDtos contdtos=new ContribuableDtos();
	contdtos.setIdContribuable(contribuablecree.getIdContribuable());
	contdtos.setEmail(contribuablecree.getEmail());
	contdtos.setAdress(contribuablecree.getAdress());
	contdtos.setNomCommercial(contribuablecree.getNomCommercial());
	contdtos.setMatriculeFiscale(contribuablecree.getMatriculeFiscale());
	contdtos.setDateDeMatriculation(contribuablecree.getDateDeMatriculation());
	contdtos.setFormeJuridique(contribuablecree.getFormeJuridique());
	contdtos.setPays(contribuablecree.getPays());
	contdtos.setRaisonsSociale(contribuablecree.getRaisonsSociale());
	contdtos.setActivites(contribuablecree.getActivites());
	contdtos.setDirecteur(contribuablecree.getDirecteur());
	contdtos.setRaisonsSociale(contribuablecree.getRaisonsSociale());
	return contdtos;
}
public ContribuableDtos findByMatriculeFiscale(int matriculeFiscale) {
	Optional<Contribuable> contribuable=contribualbeRepository.findByMatriculeFiscale(matriculeFiscale);
	if(contribuable.isPresent()) {
	Contribuable c=contribuable.get();
		Hibernate.initialize(c.getFormeJuridique());
        Hibernate.initialize(c.getPays());
        Hibernate.initialize(c.getActivites());
    	ContribuableDtos contdtos=new ContribuableDtos();
		contdtos.setIdContribuable(contribuable.get().getIdContribuable());
		contdtos.setEmail(contribuable.get().getEmail());
		contdtos.setAdress(contribuable.get().getAdress());
		contdtos.setNomCommercial(contribuable.get().getNomCommercial());
		contdtos.setMatriculeFiscale(contribuable.get().getMatriculeFiscale());
		contdtos.setDateDeMatriculation(contribuable.get().getDateDeMatriculation());
		contdtos.setFormeJuridique(contribuable.get().getFormeJuridique());
		contdtos.setPays(contribuable.get().getPays());
		contdtos.setRaisonsSociale(contribuable.get().getRaisonsSociale());
		contdtos.setActivites(contribuable.get().getActivites());
		contdtos.setDirecteur(contribuable.get().getDirecteur());
		contdtos.setRaisonsSociale(contribuable.get().getRaisonsSociale());
		return contdtos;
	}
	return null;
}
public List<ContribuableDtos> lesContribuables() {
    // TODO Auto-generated method stub
    return contribualbeRepository.findAll().stream().map(Contribuable::getContribuable).collect(Collectors.toList());
}
public ContribuableDtos findContribuableByIdCompte(Long idCompte) {
	Optional<Compte> compteTrouve=compteRepository.findById(idCompte);
	if(compteTrouve.isPresent()) {
		Compte compte=compteTrouve.get();
		Contribuable contribuable=compte.getInscription().getContribuable();
		ContribuableDtos contribuableDtos= new ContribuableDtos();
		contribuableDtos.setIdContribuable(contribuable.getIdContribuable());;
		contribuableDtos.setActivites(contribuable.getActivites());
		contribuableDtos.setAdress(contribuable.getAdress());;
		contribuableDtos.setDateDeMatriculation(contribuable.getDateDeMatriculation());
		contribuableDtos.setDirecteur(contribuable.getDirecteur());
		contribuableDtos.setEmail(contribuable.getEmail());
		contribuableDtos.setFormeJuridique(contribuable.getFormeJuridique());
		contribuableDtos.setMatriculeFiscale(contribuable.getMatriculeFiscale());
		contribuableDtos.setNomCommercial(contribuable.getNomCommercial());
		contribuableDtos.setPays(contribuable.getPays());
		contribuableDtos.setRaisonsSociale(contribuable.getRaisonsSociale());
		return contribuableDtos;
	}
	else {
		return null;
	}
}
}
