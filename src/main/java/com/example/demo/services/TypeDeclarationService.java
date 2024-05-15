package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.TypeDeclarationDto;
import com.example.demo.entities.Periodicite;
import com.example.demo.entities.TypeDeclaration;
import com.example.demo.repository.TypeDeclarationRepo;

@Service
public class TypeDeclarationService {
@Autowired
private TypeDeclarationRepo typeRepo;
public List<TypeDeclarationDto> findAllTypeDeclaration() {
    return typeRepo.findAll().stream().map(TypeDeclaration::getTypeDeclaration).collect(Collectors.toList());
}
}
