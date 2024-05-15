package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.DetailDeclarationDto;
import com.example.demo.entities.DetailDeclaration;
import com.example.demo.repository.DetailDecalrationRepository;

@Service
public class DetailDeclarationservice {
@Autowired
private DetailDecalrationRepository detailRepo;

public boolean updateDetail(DetailDeclarationDto dt) {
	Optional<DetailDeclaration> detail=detailRepo.findById(dt.getIdDetailDeclaration());
	if(detail.isPresent()) {
		DetailDeclaration detailcree=detail.get();
		 detailcree.setValeur(dt.getValeur());
		detailRepo.save(detailcree);
		return true;
	}else {
		return false;
	}
}
}
