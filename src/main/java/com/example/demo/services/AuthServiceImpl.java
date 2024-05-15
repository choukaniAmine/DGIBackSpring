package com.example.demo.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PasswordDto;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;
import com.example.demo.entities.Inscription;

import com.example.demo.enuum.UserRole;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	@Autowired
private InscriptionRepository userRepository;
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public UserDto  createCustomer(SignupRequest signupRequest) {
		Inscription user=new Inscription();
        user.setEmail(signupRequest.getEmail());
       user.setName(signupRequest.getName());
        user.setUserRole(UserRole.Client);
        user.setPoste(signupRequest.getPoste());
        user.setEnabled(false);
     user.setContribuable(signupRequest.getContribuable());
     user.setTypeIdentifiant(signupRequest.getTypeIdentifiant());
     user.setValeurIdentifiant(signupRequest.getValeurIdentifiant());
     user.setDateInscri(new Date());
        String randomCode=RandomString.make(64);
        user.setVerificationCode(randomCode);
      Inscription createdCustomer=userRepository.save(user);
        UserDto createdUserDto=new UserDto();
        createdUserDto.setId(createdCustomer.getIdInscription());
        createdUserDto.setEmail(createdCustomer.getEmail());
        createdUserDto.setUserRole(createdCustomer.getUserRole());
        createdUserDto.setRandomCode(createdCustomer.getVerificationCode());
        createdUserDto.setEnabled(false);
        createdUserDto.setNonLocked(false);
        createdUserDto.setDate(new Date());
        createdUserDto.setName(createdCustomer.getName());
  createdUserDto.setContribuable(createdCustomer.getContribuable());
  createdUserDto.setTypeIdentifiant(createdCustomer.getTypeIdentifiant());
  createdUserDto.setValeurIdentifiant(createdCustomer.getValeurIdentifiant());
  createdUserDto.setPoste(createdCustomer.getPoste());
        return createdUserDto;
	}
	@Override
	public void sendVerificationEmail(UserDto user) throws UnsupportedEncodingException, MessagingException {

		String subject="Please verify your registration";
		String senderName="direction generale d'impots";
		String mailContent="<p>Dear  "+user.getEmail()+",</p>";
		mailContent +="<p>Please click the link below to verify your registration : <br> </p>";
		String url = "http://localhost:4200/admin/createpassword/" + user.getRandomCode();
		mailContent += "<h2><a href=\"" + url + "\">verify</a></h2>";
		mailContent+="<p><br>DGI </p> ";
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setFrom("pfedgi1920@gmail.com",senderName);
		helper.setTo(user.getEmail());
		helper.setText(mailContent, true);
		helper.setSubject(subject);

	        mailSender.send(message);

	}
	@Override
	public boolean verify(String verficationnCod) {
		

    Optional<Inscription> userOptional = userRepository.findByVerificationCode(verficationnCod);

    if (userOptional.isPresent()) {
        Inscription user = userOptional.get();

        System.out.println("Found User: " + user);

        if (!user.isEnabled()) {
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

    System.out.println("User not found or already enabled.");
    return false;
	}

	@Override
	public UserDto validePassword(PasswordDto pd) {
	Inscription user=new Inscription();
		 user.setContribuable(pd.getInscription().getContribuable());
		 user.setDateInscri(pd.getInscription().getDate());
		 user.setEmail(pd.getInscription().getEmail());
		 user.setEnabled(pd.getInscription().isEnabled());
		 user.setIdInscription(pd.getInscription().getId());
		 user.setValeurIdentifiant(pd.getInscription().getValeurIdentifiant());
		 user.setVerificationCode(pd.getInscription().getRandomCode());
		 user.setPoste(pd.getInscription().getPoste());
		 
		 user.setNonLocked(pd.getInscription().isNonLocked());
		 user.setName(pd.getInscription().getName());
		 user.setUserRole(pd.getInscription().getUserRole());
		 user.setTypeIdentifiant(pd.getInscription().getTypeIdentifiant());
	 
	 user.setPassword(new BCryptPasswordEncoder().encode(pd.getPassword()));
	 Inscription createdCustomer=userRepository.save(user);
	 UserDto createdUserDto=new UserDto();
     createdUserDto.setId(createdCustomer.getIdInscription());
     createdUserDto.setEmail(createdCustomer.getEmail());
     createdUserDto.setUserRole(createdCustomer.getUserRole());
     createdUserDto.setRandomCode(createdCustomer.getVerificationCode());
     createdUserDto.setEnabled(createdCustomer.isEnabled());
     createdUserDto.setNonLocked(createdCustomer.isNonLocked());
     createdUserDto.setDate(createdCustomer.getDateInscri());
     createdUserDto.setContribuable(createdCustomer.getContribuable());
     createdUserDto.setName(createdCustomer.getName());
     
     createdUserDto.setTypeIdentifiant(createdCustomer.getTypeIdentifiant());
     createdUserDto.setValeurIdentifiant(createdCustomer.getValeurIdentifiant());
     return createdUserDto;
	 
	}
	@Override
	public UserDto changeuser(String code) {
		Optional<Inscription> createdCustomer=userRepository.findByVerificationCode(code);
		UserDto createdUserDto=new UserDto();
		 createdUserDto.setId(createdCustomer.get().getIdInscription());
	     createdUserDto.setEmail(createdCustomer.get().getEmail());
	     createdUserDto.setUserRole(createdCustomer.get().getUserRole());
	     createdUserDto.setRandomCode(createdCustomer.get().getVerificationCode());
	     createdUserDto.setEnabled(createdCustomer.get().isEnabled());
	     createdUserDto.setNonLocked(createdCustomer.get().isNonLocked());
	     createdUserDto.setDate(createdCustomer.get().getDateInscri());
	     createdUserDto.setContribuable(createdCustomer.get().getContribuable());
	     createdUserDto.setName(createdCustomer.get().getName());
	     createdUserDto.setPoste(createdCustomer.get().getPoste());
	     createdUserDto.setTypeIdentifiant(createdCustomer.get().getTypeIdentifiant());
	     createdUserDto.setValeurIdentifiant(createdCustomer.get().getValeurIdentifiant());
	     return createdUserDto;
	}
}
