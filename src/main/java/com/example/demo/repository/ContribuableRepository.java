package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contribuable;

@Repository
public interface  ContribuableRepository extends JpaRepository<Contribuable,Long> {
Optional<Contribuable> findByMatriculeFiscale(int matriculeFiscale);
}
