package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.FormeJuridique;

@Repository
public interface FormeJuridiqueRepository  extends JpaRepository<FormeJuridique,Long> {

}
