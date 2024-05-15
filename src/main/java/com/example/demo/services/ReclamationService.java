package com.example.demo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ReclamationDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Reclamation;
import com.example.demo.enuum.Etat;
import com.example.demo.repository.ContribuableRepository;
import com.example.demo.repository.ReclamationRepository;

@Service
public class ReclamationService {
	@Autowired 
    private ReclamationRepository reclamationrepo;
    @Autowired
    private ContribuableRepository contribuableRepository;


 
    public Reclamation saveReclamation(ReclamationDto c) {
        // Fetch the existing Contribuable entity from the database using its ID
        Contribuable existingContribuable = this.contribuableRepository.findById(c.getContribuable().getIdContribuable())
                .orElseThrow(() -> new IllegalArgumentException("Contribuable not found"));

        // Create a new Reclamation object and set its attributes
        Reclamation newReclamation = new Reclamation();
        newReclamation.setContenu(c.getContenu());
        newReclamation.setEtat(Etat.EN_ATTENTE);
        newReclamation.setSolution(null); // Assuming solution can be null initially
        newReclamation.setDateReclamation(new Date());
        newReclamation.setTitre(c.getTitre());

        // Set the existing Contribuable in the Reclamation entity
        newReclamation.setContribuable(existingContribuable);

        // Save the Reclamation entity
        this.reclamationrepo.save(newReclamation);

        return newReclamation; // Indicate that the save operation is attempted
    }
}
