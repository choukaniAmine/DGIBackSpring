package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TypeImpot;
@Repository
public interface TypeImpotRepository extends JpaRepository<TypeImpot,Long> {
Optional<TypeImpot> findByLibelle(String libelle);
}
