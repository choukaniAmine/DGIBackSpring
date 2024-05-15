package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.repository.UserRepository;

@Service

public class UserServiceImpl implements UserServices {

private  InscriptionRepository userRepository;
@Autowired
private CompteRepository compteRepo;
	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				return compteRepo.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException("user not found"));
			}
		};
	}

}
