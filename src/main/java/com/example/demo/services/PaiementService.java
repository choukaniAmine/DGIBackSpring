package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.paiementDto;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.Paiement;
import com.example.demo.repository.DecalrataionRepository;
import com.example.demo.repository.PaiementRepository;

@Service
public class PaiementService {
	@Autowired
	private DecalrataionRepository declarationrepo;
	@Autowired
	private PaiementRepository paiementrepo;
	

	public boolean createPaiement(paiementDto paiementDto) {
	    Optional<Declaration> optionalDeclaration = declarationrepo.findById(paiementDto.getIddeclaration());

	    if (optionalDeclaration.isPresent()) {
	    	List<Paiement> list=paiementrepo.findByNumeroTransaction(paiementDto.getNumeroTransaction());
	    	if(list.isEmpty()) {
 	        Declaration declaration = optionalDeclaration.get();

	        Paiement paiement = new Paiement();
	        paiement.setDeclaration(declaration);
	        paiement.setNumeroTransaction(paiementDto.getNumeroTransaction());
	        paiement.setDatePaiement(new Date());

	        Paiement savedPaiement = paiementrepo.save(paiement);

	        return savedPaiement != null;
	    }else 
	    	return false; 
	    	}else {
	        return false;
	    }
	}

}
