package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Reclamation;
@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
	List<Reclamation> findByContribuable(Contribuable contribuable);
}
