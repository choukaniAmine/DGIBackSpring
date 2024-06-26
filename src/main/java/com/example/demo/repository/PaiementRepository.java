package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {

	
	
	List<Paiement> findByNumeroTransaction(String numeroTransaction);
	
}