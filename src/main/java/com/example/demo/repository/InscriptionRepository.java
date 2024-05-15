package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Inscription;



@Repository
public interface InscriptionRepository  extends JpaRepository<Inscription,Long> {
	 Optional<Inscription> findByEmail(String email);
	 Optional<Inscription> findByVerificationCode(String code);
}
