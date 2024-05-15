package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Declaration;
import com.example.demo.entities.DetailDeclaration;

@Repository
public interface DetailDecalrationRepository extends JpaRepository<DetailDeclaration, Long>  {
List<DetailDeclaration> findByDeclaration(Declaration dec);
}
