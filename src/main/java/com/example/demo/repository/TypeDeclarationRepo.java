package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TypeDeclaration;

@Repository
public interface TypeDeclarationRepo extends JpaRepository<TypeDeclaration, Long> {

}
