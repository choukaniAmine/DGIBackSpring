package com.example.demo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.DeclarationDto;
import com.example.demo.dtos.DetailDeclarationDto;
import com.example.demo.dtos.SaveDeclaration;
import com.example.demo.dtos.SaveMontantDto;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.DetailDeclaration;
import com.example.demo.entities.DetailImpot;
import com.example.demo.entities.ObligationFiscale;
import com.example.demo.enuum.TypeDeDeclaration;
import com.example.demo.repository.DecalrataionRepository;
import com.example.demo.repository.DetailDecalrationRepository;
import com.example.demo.repository.DetailImpotRepositpry;
import com.example.demo.repository.ObligationFiscaleRepository;

@Service
public class Declarationservice {
@Autowired
private ObligationFiscaleRepository obligationRepo;
@Autowired
private DetailImpotRepositpry detailimpotRepo;
@Autowired
private DecalrataionRepository declarationRepo;
@Autowired
private DetailDecalrationRepository detailDeclarationRepo;


public Map<DetailImpot, DetailDeclarationDto> saveDeclaration(SaveDeclaration dc) {
    Optional<ObligationFiscale> obligation = obligationRepo.findById(dc.getIdObligation());

    // Initialize detailMap here
    Map<DetailImpot, DetailDeclarationDto> detailMap = new HashMap<>();

    if (obligation.isPresent()) {
        Optional<Declaration> declaration = declarationRepo.findByMoisEffetAndAnneEffetAndObligationFiscale(dc.getMoisEffet(), dc.getAnneEffet(), obligation.get());
        if (!declaration.isPresent()) {
            Declaration newDeclaration = new Declaration();
            newDeclaration.setObligationFiscale(obligation.get());
            newDeclaration.setAnneEffet(dc.getAnneEffet());
            newDeclaration.setMoisEffet(dc.getMoisEffet());
            newDeclaration.setDateDeclaration(new Date());
            newDeclaration.setType(dc.getType());
            this.declarationRepo.save(newDeclaration);
            List<DetailImpot> lesDetailsImpot = detailimpotRepo.findByTypeImpot(obligation.get().getTypeImpot());

            for (DetailImpot detail : lesDetailsImpot) {
                DetailDeclaration newDetailDeclaration = new DetailDeclaration();
                newDetailDeclaration.setValeur(""); // Set valeur if needed

                // Assuming you have setters for detailImpot and declaration in DetailDeclaration class
                newDetailDeclaration.setDetailImpot(detail);
                newDetailDeclaration.setDeclaration(newDeclaration);

                this.detailDeclarationRepo.save(newDetailDeclaration);
                DetailDeclarationDto dto = new DetailDeclarationDto();
                dto.setIdDetailDeclaration(newDetailDeclaration.getIdDetailDeclaration());
                dto.setValeur(null);
                dto.setIdDeclaration(newDeclaration.getIdDeclaration());// Put the DetailImpot object as key and DetailDeclaration object as value into the map
                detailMap.put(detail, dto);
            }
            return detailMap;
        } else {
            if (dc.getType().getLibelle() == TypeDeDeclaration.INITIAL) {
                // Return an empty map if it's an initial type
                return new HashMap<>();
            } else {


                List<DetailImpot> lesDetailsImpot = detailimpotRepo.findByTypeImpot(declaration.get().getObligationFiscale().getTypeImpot());

                // Fetch all detail declarations associated with the given declaration
                List<DetailDeclaration> lesdetailsDeclaration = detailDeclarationRepo.findByDeclaration(declaration.get());

                // Map DetailImpot to DetailDeclaration
                for (DetailImpot detail : lesDetailsImpot) {
                    Optional<DetailDeclaration> relatedDetail = lesdetailsDeclaration.stream()
                            .filter(detailDeclaration -> detailDeclaration.getDetailImpot().getIdDetail() == detail.getIdDetail())
                            .findFirst();

                    // If a matching DetailDeclaration is found, create a DetailDeclarationDto
                    relatedDetail.ifPresent(detailDeclaration -> {
                        DetailDeclarationDto dto = new DetailDeclarationDto();
                        dto.setIdDetailDeclaration(detailDeclaration.getIdDetailDeclaration());
                        dto.setValeur(detailDeclaration.getValeur());
                        dto.setIdDeclaration(declaration.get().getIdDeclaration());
                        // Put the DetailImpot object as the key and DetailDeclarationDto object as the value into the map
                        detailMap.put(detail, dto);
                    });
                }

                return detailMap;
            }
        }
    }
    return new HashMap<>();
}


public boolean updateMontantaCalculer(SaveMontantDto di) {
	Optional<Declaration> declaration=declarationRepo.findById(di.getIdDeclaration());
	if(declaration.isPresent()) {
		declaration.get().setMontantApayer(di.getMontantApayer());
		declarationRepo.save(declaration.get());
		return true;
	}else return false;
}

public List<Declaration> getDeclarationsByMatriculeFiscale(int matriculeFiscale) {
	return declarationRepo.findByObligationFiscale_Contribuable_MatriculeFiscale(matriculeFiscale);
}
}
