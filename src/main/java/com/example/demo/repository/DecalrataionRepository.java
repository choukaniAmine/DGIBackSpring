package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Declaration;
import com.example.demo.entities.ObligationFiscale;

@Repository
public interface DecalrataionRepository extends JpaRepository<Declaration, Long> {
	Optional<Declaration> findByMoisEffetAndAnneEffetAndObligationFiscale(int moisEffet, int anneeEffet, ObligationFiscale obligation);
	  List<Declaration> findByObligationFiscale_Contribuable_MatriculeFiscale(int matriculeFiscale);
}
