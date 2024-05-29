package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CompteDtos;
import com.example.demo.dtos.UpdatePasswordDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Inscription;
import com.example.demo.enuum.UserRole;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AdminService {
@Autowired
private InscriptionRepository inscrepo;
@Autowired
private CompteRepository compteRepo;
@Autowired
private UserRepository userRepository;
public List<UserDto> getAllInscription(){
	return inscrepo.findAll().stream().map(Inscription::getUserDto).collect(Collectors.toList());
}
public CompteDtos acceptInscri(UserDto ud) {
	 Inscription user=new Inscription();
	 	user.setIdInscription(ud.getId());
       user.setEmail(ud.getEmail());
       user.setPassword(ud.getPassword());
       user.setUserRole(UserRole.Client);
       user.setPoste(ud.getPoste());
       user.setEnabled(ud.isEnabled());
       user.setContribuable(ud.getContribuable());
       user.setNom(ud.getNom());
       user.setPrenom(ud.getPrenom());
       user.setTypeIdentifiant(ud.getTypeIdentifiant());
       user.setValeurIdentifiant(ud.getValeurIdentifiant());
       user.setNonLocked(true);
       user.setDateInscri(ud.getDate());
       user.setVerificationCode(ud.getRandomCode());
       Inscription modifierCustomer=userRepository.save(user);
       Compte compteCree=new Compte();
       compteCree.setEmail(modifierCustomer.getEmail());
       compteCree.setPassword(modifierCustomer.getPassword());
       compteCree.setUserRole(modifierCustomer.getUserRole());
       compteCree.setInscription(modifierCustomer);
       compteCree.setFailedAttempt(0);
       Compte compteCreefinal=compteRepo.save(compteCree);
       CompteDtos compteCreeDto=new CompteDtos();
       compteCreeDto.setEmail(compteCreefinal.getEmail());
       compteCreeDto.setPassword(compteCreefinal.getPassword());
       compteCreeDto.setUserRole(compteCreefinal.getUserRole());
       UserDto UserDto=new UserDto();
      UserDto.setId(compteCreefinal.getInscription().getIdInscription());
      UserDto.setEmail(compteCreefinal.getInscription().getEmail());
       UserDto.setUserRole(compteCreefinal.getInscription().getUserRole());
       UserDto.setRandomCode(compteCreefinal.getInscription().getVerificationCode());
       UserDto.setEnabled(true);
       UserDto.setNonLocked(true);
       UserDto.setDate(compteCreefinal.getInscription().getDateInscri());
       UserDto.setContribuable(compteCreefinal.getInscription().getContribuable());
       UserDto.setNom(compteCreefinal.getInscription().getNom());
     UserDto.setPrenom(compteCreefinal.getInscription().getPrenom());
       UserDto.setTypeIdentifiant(compteCreefinal.getInscription().getTypeIdentifiant());
       UserDto.setValeurIdentifiant(compteCreefinal.getInscription().getValeurIdentifiant());
       compteCreeDto.setInscription(UserDto);
       return compteCreeDto;


}
public CompteDtos changePassword(UpdatePasswordDto up) {
	Optional<Compte> c=compteRepo.findById(up.getId());
	if(verifyPassword(up.getPasswordPrec(), c.get().getPassword())) {
		Compte nvCompte=new Compte();
		nvCompte.setIdCompte(c.get().getIdCompte());
		nvCompte.setEmail(c.get().getEmail());
		nvCompte.setPassword((new BCryptPasswordEncoder().encode(up.getNvPassword())));
		nvCompte.setUserRole(c.get().getUserRole());
		 Compte compteCreefinal=compteRepo.save(nvCompte);
	       CompteDtos compteCreeDto=new CompteDtos();
	       compteCreeDto.setEmail(compteCreefinal.getEmail());
	       compteCreeDto.setPassword(compteCreefinal.getPassword());
	       compteCreeDto.setUserRole(compteCreefinal.getUserRole());
	       return compteCreeDto;
	}
	else {
		return null;
	}
	
}
public boolean verifyPassword(String rawPassword, String encodedPasswordFromDB) {
   
	   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(rawPassword, encodedPasswordFromDB);
}
}
