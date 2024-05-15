package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.entities.Periodicite;
import com.example.demo.repository.PeriodiciteRepository;

@Service
public class PeriodiciteService {
	@Autowired
    private PeriodiciteRepository perioderepo;



    public List<PeriodeDto> findAllPeriode() {
        return perioderepo.findAll().stream().map(Periodicite::getPeriodeDto).collect(Collectors.toList());
    }
}
