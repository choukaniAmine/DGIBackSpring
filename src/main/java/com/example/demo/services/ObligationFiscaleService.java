package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ObligationFiscaledto;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.TypeImpotDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.ObligationFiscale;
import com.example.demo.repository.DecalrataionRepository;
import com.example.demo.repository.DetailDecalrationRepository;
import com.example.demo.repository.ObligationFiscaleRepository;

@Service
public class ObligationFiscaleService {
@Autowired
private ObligationFiscaleRepository  obligationFiscaleRepository;
@Autowired
private DecalrataionRepository declarationRepo;

public Declaration getNumerodeclaration(Contribuable cd, Long iddecalaration) {
    List<ObligationFiscale> lesobligations = obligationFiscaleRepository.findByContribuable(cd);
    Optional<Declaration> declaration = declarationRepo.findById(iddecalaration);
    
    if (declaration.isPresent()) {
        for (ObligationFiscale obligation : lesobligations) {
            if (obligation.getIdObligationFiscale() == declaration.get().getObligationFiscale().getIdObligationFiscale()) {
                return declaration.get();
            }
        }
    }
    
    return null;
}

public List<ObligationFiscaledto> getObligationFiscaleDeContribuable(Contribuable cd){
	List<ObligationFiscale> lesobligations=obligationFiscaleRepository.findByContribuable(cd);
	List<ObligationFiscaledto> lesobligationsDto=new ArrayList<>();
	
	  for(ObligationFiscale obligation : lesobligations) {
          ObligationFiscaledto obligationDto = new ObligationFiscaledto();
          obligationDto.setDateDebut(obligation.getDateDebut());
          obligationDto.setDateFin(obligation.getDateFin());
          // Assuming TypeImpotDto has a constructor that takes ObligationFiscale as argument
          obligationDto.setIdObligation(obligation.getIdObligationFiscale());
          TypeImpotDto impot=new TypeImpotDto();
          impot.setLibelle(obligation.getTypeImpot().getLibelle());
          PeriodeDto periode=new PeriodeDto();
          periode.setIdPeriodicite(obligation.getTypeImpot().getPeriodicite().getIdPeriodicite());
          periode.setPeriode(obligation.getTypeImpot().getPeriodicite().getPeriode());
          impot.setPeriodicite(periode);
          obligationDto.setTypeImpot(impot);

          lesobligationsDto.add(obligationDto);
      }

      return lesobligationsDto;
}
}
