package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CompteById;
import com.example.demo.dtos.CompteDtos;
import com.example.demo.dtos.MotDePassdto;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Inscription;
import com.example.demo.enuum.UserRole;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.InscriptionRepository;
@Service
public class CompteService {
	@Autowired
	private CompteRepository compteRepository;
@Autowired
private InscriptionRepository inscrepo;


	
		public boolean saveCompte(CompteDtos cd) {
		    Compte newCompte = new Compte();
		    newCompte.setEmail(cd.getEmail());
		    newCompte.setUserRole(cd.getUserRole());
		    if (newCompte.getUserRole() == UserRole.Admin || newCompte.getUserRole() == UserRole.Responsable) {
		        newCompte.setPassword(new BCryptPasswordEncoder().encode(cd.getPassword()));
		    } else {
		        newCompte.setPassword(cd.getPassword());
		    
		    Inscription UserDto=new Inscription();
		       UserDto.setIdInscription(cd.getInscription().getId());
		       UserDto.setEmail(cd.getInscription().getEmail());
		        UserDto.setUserRole(cd.getInscription().getUserRole());
		        UserDto.setVerificationCode(cd.getInscription().getRandomCode());
		        UserDto.setEnabled(cd.getInscription().isEnabled());
		        UserDto.setNonLocked(cd.getInscription().isNonLocked());
		        UserDto.setDateInscri(cd.getInscription().getDate());
		        UserDto.setContribuable(cd.getInscription().getContribuable());
		        UserDto.setNom(cd.getInscription().getNom());
		      UserDto.setPrenom(cd.getInscription().getPrenom());
		        UserDto.setTypeIdentifiant(cd.getInscription().getTypeIdentifiant());
		        UserDto.setValeurIdentifiant(cd.getInscription().getValeurIdentifiant());
		    newCompte.setInscription(UserDto);}
		    Compte compteCree = compteRepository.save(newCompte);


		    if (compteCree != null && compteCree.getIdCompte() != null) {

		        return true;
		    } else {

		        return false;
		    }
		}



		
		public List<CompteDtos> getAllCompte() {
			return compteRepository.findAll().stream().map(Compte::getcompteDtos).collect(Collectors.toList());
		}



		
		public boolean blocageCompte(CompteDtos cd) {
		    
		    Optional<Compte> existingCompteOptional = compteRepository.findById(cd.getIdCompte());
		    
		    // Check if the Compte exists
		    if (existingCompteOptional.isPresent()) {
		        Compte existingCompte = existingCompteOptional.get();

		       
		        existingCompte.setEmail(cd.getEmail());
		        existingCompte.setUserRole(cd.getUserRole());
		       
		        if (existingCompte.getUserRole() == UserRole.Admin || existingCompte.getUserRole() == UserRole.Responsable) {
		            existingCompte.setPassword(new BCryptPasswordEncoder().encode(cd.getPassword()));
		        } else {
		            existingCompte.setPassword(cd.getPassword());
		        }

		        
		        Inscription existingUser = existingCompte.getInscription();
		        existingUser.setEmail(cd.getInscription().getEmail());
		        existingUser.setUserRole(cd.getInscription().getUserRole());
		        existingUser.setVerificationCode(cd.getInscription().getRandomCode());
		        existingUser.setEnabled(cd.getInscription().isEnabled());
		        existingUser.setNonLocked(false); // Consider revising this logic
		        existingUser.setDateInscri(cd.getInscription().getDate());
		        existingUser.setContribuable(cd.getInscription().getContribuable());
		        existingUser.setNom(cd.getInscription().getNom());
		        existingUser.setPrenom(cd.getInscription().getPrenom());
		        existingUser.setTypeIdentifiant(cd.getInscription().getTypeIdentifiant());
		        existingUser.setValeurIdentifiant(cd.getInscription().getValeurIdentifiant());
		        existingUser.setPoste(cd.getInscription().getPoste());
		        existingUser.setPassword(cd.getPassword()); // Revisit this assignment
		        
		        // Save the updated Compte
		        Compte compteCree = compteRepository.save(existingCompte);

		        // Check if the account is locked
		        if (!compteCree.getInscription().isNonLocked()) {
		            return true;
		        } else {
		            return false;
		        }
		    } else {
		        // Compte with the given ID not found
		        return false;
		    }
		}



		
		public boolean AcceptCompte(CompteDtos cd) {
			 Optional<Compte> existingCompteOptional = compteRepository.findById(cd.getIdCompte());
			    
			    
			    if (existingCompteOptional.isPresent()) {
			        Compte existingCompte = existingCompteOptional.get();

			       
			        existingCompte.setEmail(cd.getEmail());
			        existingCompte.setUserRole(cd.getUserRole());
			        existingCompte.setFailedAttempt(0);
			        if (existingCompte.getUserRole() == UserRole.Admin) {
			            existingCompte.setPassword(new BCryptPasswordEncoder().encode(cd.getPassword()));
			        } else {
			            existingCompte.setPassword(cd.getPassword());
			        }

			        
			        Inscription existingUser = existingCompte.getInscription();
			        existingUser.setEmail(cd.getInscription().getEmail());
			        existingUser.setUserRole(cd.getInscription().getUserRole());
			        existingUser.setVerificationCode(cd.getInscription().getRandomCode());
			        existingUser.setEnabled(cd.getInscription().isEnabled());
			        existingUser.setNonLocked(true); // Consider revising this logic
			        existingUser.setDateInscri(cd.getInscription().getDate());
			        existingUser.setContribuable(cd.getInscription().getContribuable());
			        existingUser.setNom(cd.getInscription().getNom());
			        existingUser.setPrenom(cd.getInscription().getPrenom());
			        existingUser.setTypeIdentifiant(cd.getInscription().getTypeIdentifiant());
			        existingUser.setValeurIdentifiant(cd.getInscription().getValeurIdentifiant());
			        existingUser.setPoste(cd.getInscription().getPoste());
			        existingUser.setPassword(cd.getPassword());
			        
			       
			        Compte compteCree = compteRepository.save(existingCompte);

			        
			        if (!compteCree.getInscription().isNonLocked()) {
			            return false;
			        } else {
			            return true;
			        }
			    } else {
			        
			        return false;
			    }
		}
		
		public boolean blocageCompteParEmail(String email) {
			// TODO Auto-generated method stub
			 Optional<Compte> existingCompteOptional = compteRepository.findByEmail(email);


			    if (existingCompteOptional.isPresent()) {
			        Compte existingCompte = existingCompteOptional.get();


			        existingCompte.setEmail(existingCompteOptional.get().getEmail());
			        existingCompte.setUserRole(existingCompteOptional.get().getUserRole());

			        if (existingCompte.getUserRole() == UserRole.Admin) {
			            existingCompte.setPassword(new BCryptPasswordEncoder().encode(existingCompteOptional.get().getPassword()));
			        } else {
			            existingCompte.setPassword(existingCompteOptional.get().getPassword());
			        }


			        Inscription existingUser = existingCompte.getInscription();
			        existingUser.setEmail(existingCompteOptional.get().getInscription().getEmail());
			        existingUser.setUserRole(existingCompteOptional.get().getInscription().getUserRole());
			        existingUser.setVerificationCode(existingCompteOptional.get().getInscription().getVerificationCode());
			        existingUser.setEnabled(existingCompteOptional.get().getInscription().isEnabled());
			        existingUser.setNonLocked(false); // Consider revising this logic
			        existingUser.setDateInscri(existingCompteOptional.get().getInscription().getDateInscri());
			        existingUser.setContribuable(existingCompteOptional.get().getInscription().getContribuable());
			        existingUser.setNom(existingCompteOptional.get().getInscription().getNom());
			        existingUser.setPrenom(existingCompteOptional.get().getInscription().getPrenom());
			        existingUser.setTypeIdentifiant(existingCompteOptional.get().getInscription().getTypeIdentifiant());
			        existingUser.setValeurIdentifiant(existingCompteOptional.get().getInscription().getValeurIdentifiant());
			        existingUser.setPoste(existingCompteOptional.get().getInscription().getPoste());
			        existingUser.setPassword(existingCompteOptional.get().getPassword());


			        Compte compteCree = compteRepository.save(existingCompte);


			        if (!compteCree.getInscription().isNonLocked()) {
			            return false;
			        } else {
			            return true;
			        }
			    } else {

			        return false;
			    }
		}



		public void updateFailedAttempt(String email) {
		    Optional<Compte> existingCompteOptional = compteRepository.findByEmail(email);
		    if(existingCompteOptional.isPresent()) {
		        Compte existingCompte = existingCompteOptional.get();
		        int currentFailedAttempt = existingCompte.getFailedAttempt();
		        existingCompte.setFailedAttempt(currentFailedAttempt + 1);
		        compteRepository.save(existingCompte);
		    }
		}



		public void resetFailedAttempt(String email) {
			// TODO Auto-generated method stub
			Optional<Compte> existingCompteOptional = compteRepository.findByEmail(email);
		    if(existingCompteOptional.isPresent()) {
		        Compte existingCompte = existingCompteOptional.get();
		        
		        existingCompte.setFailedAttempt(0);
		        compteRepository.save(existingCompte);
		    }
			
		}

		public CompteById getCompteByid(Long id) {
			
			
			Optional<Compte>  compte=compteRepository.findById(id);
			if(compte.isPresent()) {
				CompteById compteDto=new CompteById();
				compteDto.setEmail(compte.get().getEmail());
				compteDto.setFirstName(compte.get().getInscription().getNom());
				compteDto.setLastName(compte.get().getInscription().getPrenom());
				return compteDto;
			}else return null;
			}
		
		public boolean updatepassword(MotDePassdto md) {
		    // Fetch the user account by id
		    Optional<Compte> userOptional = compteRepository.findById(md.getIdCompte());
		    
		    if (userOptional.isPresent()) {
		        Compte user = userOptional.get();
		        
		        // Fetch the associated user details by email
		        Optional<Inscription> userDetailOptional = inscrepo.findById(user.getInscription().getIdInscription());
		        
		        if (userDetailOptional.isPresent()) {
		            Inscription userDetail = userDetailOptional.get();
		            
		            // Verify the old password
		            if (verifyPassword(md.getAncienMotDePass(), user.getPassword())) {
		                // Encode the new password
		       
		                
		                // Update passwords in both Compte and User
		                user.setPassword(new BCryptPasswordEncoder().encode(md.getMotDePass()));
		                userDetail.setPassword(new BCryptPasswordEncoder().encode(md.getMotDePass()));
		                
		                // Save the updated entities
		                compteRepository.save(user);
		                inscrepo.save(userDetail);
		                
		                return true;
		            } else {
		                // Old password verification failed
		                return false;
		            }
		        } else {
		            // User details not found
		            return false;
		        }
		    } else {
		        // User account not found
		        return false;
		    }
		}
		public boolean verifyPassword(String rawPassword, String encodedPasswordFromDB) {
			   
			   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    return encoder.matches(rawPassword, encodedPasswordFromDB);
		}

		
}
