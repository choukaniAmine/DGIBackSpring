package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contribuable;
import com.example.demo.entities.ObligationFiscale;
@Repository
public interface ObligationFiscaleRepository extends JpaRepository<ObligationFiscale,Long> {
List<ObligationFiscale> findByContribuable(Contribuable c);
}
