package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ImpotDto;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.TypeImpotDto;
import com.example.demo.entities.Echeance;
import com.example.demo.entities.Periodicite;
import com.example.demo.entities.TypeImpot;
import com.example.demo.enuum.Periode;
import com.example.demo.repository.EcheanceRepository;
import com.example.demo.repository.PeriodiciteRepository;
import com.example.demo.repository.TypeImpotRepository;

@Service
public class TypeImpotService {
	@Autowired
	private EcheanceRepository echeanceRepository;
	@Autowired
	private TypeImpotRepository typeImpotRepository;
	@Autowired
	private PeriodiciteRepository periodiciteRepository;
	
	
	
	public TypeImpotDto saveImpot(TypeImpotDto td) {
		TypeImpot impot=new TypeImpot();
		impot.setLibelle(td.getLibelle());
		 Periodicite periodicite = periodiciteRepository.findById(td.getPeriodicite().getIdPeriodicite())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid periodicite"));

impot.setPeriodicite(periodicite);
Periodicite per1=new Periodicite();
per1.setIdPeriodicite(td.getPeriodicite().getIdPeriodicite());
per1.setPeriode(td.getPeriodicite().getPeriode());
		impot.setPeriodicite(per1);
		TypeImpot     impot1=typeImpotRepository.save(impot);
		if(impot.getPeriodicite().getPeriode()==Periode.MENSUELLE) {
			 for (int mois = 1; mois <= 12; mois++) {
		            Echeance echeance = new Echeance();
		            echeance.setJour(15); // Jour de l'échéance (exemple : le 15 de chaque mois)
		            echeance.setMois(mois+1); // Mois de l'échéance (1 pour janvier, 2 pour février, etc.)
		            echeance.setNumeroEcheance(mois); // Numéro de l'échéance (1 à 12 pour chaque mois)
		            
		            
		            int annee = 0;
		            if (mois == 12) {
		            	echeance.setMois(1);
		                annee = 1;
		            }
		           
		            echeance.setAnnee(annee); 
		            
		            echeance.setTypeImpot(impot); 

		           
		            echeanceRepository.save(echeance); // Assurez-vous d'injecter echeanceRepository dans votre classe
		        }
			
		}else if (impot.getPeriodicite().getPeriode() == Periode.TRIMESTRE) {
	        for (int trimestre = 1; trimestre <= 4; trimestre++) {
	        	Echeance echeance = new Echeance();
	            echeance.setJour(15); // Jour de l'échéance (exemple : le 15 de chaque mois)
	            echeance.setMois(trimestre+3); // Mois de l'échéance (1 pour janvier, 2 pour février, etc.)
	            echeance.setNumeroEcheance(trimestre);
	            int annee = 0;
	            if (trimestre == 4) {
	            	echeance.setMois(1);
	                annee = 1;
	            }
	            echeance.setAnnee(annee); 
	            
	            echeance.setTypeImpot(impot); 

	           
	            echeanceRepository.save(echeance);
	        }
	    } else if (impot.getPeriodicite().getPeriode() == Periode.SEMESTRE) {
	        for (int semestre = 1; semestre <= 2; semestre++) {
	        	Echeance echeance = new Echeance();
	            echeance.setJour(15); 
	            echeance.setMois(7); 
	            echeance.setNumeroEcheance(semestre);
	            int annee = 0;
	            if (semestre == 2) {
	            	echeance.setMois(1);
	                annee = 1;
	            }
	            echeance.setAnnee(annee); 
	            
	            echeance.setTypeImpot(impot); 
	            
	           
	            echeanceRepository.save(echeance);
	        }
	    } else if (impot.getPeriodicite().getPeriode() == Periode.ANNUELLE) {
	    	Echeance echeance = new Echeance();
            echeance.setJour(15); 
            echeance.setMois(1); 
            echeance.setNumeroEcheance(1);
         
           
            echeance.setAnnee(1); 
            
            echeance.setTypeImpot(impot); 
            
           
            echeanceRepository.save(echeance);
	    }
		
		TypeImpotDto savedImpot=new TypeImpotDto();
		
		savedImpot.setLibelle(impot1.getLibelle());
		PeriodeDto per=new PeriodeDto();
		per.setIdPeriodicite(impot1.getPeriodicite().getIdPeriodicite());
		per.setPeriode(impot1.getPeriodicite().getPeriode());
		savedImpot.setPeriodicite(per);
		return savedImpot;
	}
	 public List<TypeImpotDto> findAllImpot() {
	        return typeImpotRepository.findAll().stream().map(TypeImpot::getImpot).collect(Collectors.toList());
	    }
	 public TypeImpotDto findTypeImpotByLibelle(String Libelle) {
		 Optional<TypeImpot> type=typeImpotRepository.findByLibelle(Libelle);
		 if(type.get()!=null) {
			 TypeImpotDto typedto=new TypeImpotDto();
			 typedto.setLibelle(type.get().getLibelle());
			 PeriodeDto per=new PeriodeDto();
				per.setIdPeriodicite(type.get().getPeriodicite().getIdPeriodicite());
				per.setPeriode(type.get().getPeriodicite().getPeriode());
				typedto.setPeriodicite(per);
				return typedto;
				
		 }else return null;
	 }
	 
	 public boolean updateImpotFormule(ImpotDto im) {
		 Optional<TypeImpot> type=typeImpotRepository.findByLibelle(im.getLibelle());
		 if(type.isPresent()) {
			 TypeImpot typeUpdate=type.get();
			 typeUpdate.setFormule(im.getFormule());
			 typeImpotRepository.save(typeUpdate);
			 return true;
		 }else {
			 return false;
		 }
	 }
	 
}
