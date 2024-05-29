package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ReclamationDto;
import com.example.demo.dtos.ReclamtionResponse;
import com.example.demo.dtos.UpdateSolutionRecDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.Reclamation;
import com.example.demo.enuum.Etat;
import com.example.demo.repository.ContribuableRepository;
import com.example.demo.repository.DecalrataionRepository;
import com.example.demo.repository.ReclamationRepository;

@Service
public class ReclamationService {
	@Autowired 
    private ReclamationRepository reclamationrepo;
    @Autowired
    private ContribuableRepository contribuableRepository;
@Autowired
private DecalrataionRepository declarationrepo;

 
    public Reclamation saveReclamation(ReclamationDto c) {
        // Fetch the existing Contribuable entity from the database using its ID
        Contribuable existingContribuable = this.contribuableRepository.findById(c.getContribuable().getIdContribuable())
                .orElseThrow(() -> new IllegalArgumentException("Contribuable not found"));

        // Create a new Reclamation object and set its attributes
        if(c.getIdDeclaration()!=0l) {
    	    Optional<Declaration> declaration=declarationrepo.findById(c.getIdDeclaration());

    	 if(declaration.isPresent()) {
    	    Reclamation newReclamation = new Reclamation();
    	    newReclamation.setContenu(c.getContenu());
    	    newReclamation.setEtat(Etat.EN_ATTENTE);
    	    newReclamation.setSolution(null); 
    	    newReclamation.setDateReclamation(new Date());
    	    newReclamation.setTitre(c.getTitre());
    	    newReclamation.setDeclaration(declaration.get());
    	    newReclamation.setContribuable(existingContribuable);
    	    this.reclamationrepo.save(newReclamation);
    	    return newReclamation; 
    	 }else return null;
    }else {
    	Reclamation newReclamation = new Reclamation();
        newReclamation.setContenu(c.getContenu());
        newReclamation.setEtat(Etat.EN_ATTENTE);
        newReclamation.setSolution(null); 
        newReclamation.setDateReclamation(new Date());
        newReclamation.setTitre(c.getTitre());
        newReclamation.setContribuable(existingContribuable);
        this.reclamationrepo.save(newReclamation);
        return newReclamation;
    	}


    }
    public List<ReclamtionResponse> getAllReclamation() {
		return reclamationrepo.findAll().stream().map(Reclamation::getreclamation).collect(Collectors.toList());
		
	}
    
    public Reclamation updateSolution(UpdateSolutionRecDto rd) {
    	Optional<Reclamation> reclamation=reclamationrepo.findById(rd.getIdReclamation());
    	if(reclamation.isPresent()) {
    		if(rd.getEtat()==Etat.REFUSEE) {
    			reclamation.get().setSolution("Votre réclamation a été refusée en raison de la non-conformité aux termes et conditions.");
    			reclamation.get().setEtat(Etat.REFUSEE);
    			reclamationrepo.save(reclamation.get());
    		return reclamation.get();}
    		else{
    			reclamation.get().setSolution(rd.getSolution());
    			reclamation.get().setEtat(Etat.EN_COURS);
    			reclamationrepo.save(reclamation.get());
    			return reclamation.get();
    		}
    	}else {
    		return null;
    	}
    }
}
