package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.DetailImpot;
import com.example.demo.entities.TypeImpot;

@Repository
public interface DetailImpotRepositpry extends JpaRepository<DetailImpot, Long> {
List<DetailImpot> findByTypeImpot(TypeImpot impot);
}
