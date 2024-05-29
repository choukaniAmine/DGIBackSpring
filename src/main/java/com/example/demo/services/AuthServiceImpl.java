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
import com.example.demo.dtos.ResetPassword;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Inscription;

import com.example.demo.enuum.UserRole;
import com.example.demo.repository.CompteRepository;
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
	@Autowired
	private CompteRepository compteRep;
	@Override
	public UserDto  createCustomer(SignupRequest signupRequest) {
		Inscription user=new Inscription();
        user.setEmail(signupRequest.getEmail());
       user.setNom(signupRequest.getNom());
       user.setPrenom(signupRequest.getPrenom());
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
        createdUserDto.setNom(createdCustomer.getNom());
        createdUserDto.setPrenom(createdCustomer.getPrenom());
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
		 user.setNom(pd.getInscription().getNom());
		 user.setPrenom(pd.getInscription().getPrenom());
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
     createdUserDto.setNom(createdCustomer.getNom());
     createdUserDto.setPrenom(createdCustomer.getPrenom());
     
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
	     createdUserDto.setNom(createdCustomer.get().getNom());
	     createdUserDto.setPrenom(createdCustomer.get().getPrenom());
	     createdUserDto.setPoste(createdCustomer.get().getPoste());
	     createdUserDto.setTypeIdentifiant(createdCustomer.get().getTypeIdentifiant());
	     createdUserDto.setValeurIdentifiant(createdCustomer.get().getValeurIdentifiant());
	     return createdUserDto;
	}
	@Override
	public boolean sendUpdatePasswordEmail(String email) throws UnsupportedEncodingException, MessagingException {
Optional<Inscription> ins=this.userRepository.findByEmail(email);
if(ins.isPresent()) {
		  String subject = "Password Reset Request";
		    String senderName = "Direction Générale des Impôts";
		    String mailContent = "<p>Dear " + email + ",</p>";
		    mailContent += "<p>We received a request to reset your password. Please click the link below to reset your password: <br></p>";
		    String url = "http://localhost:4200/admin/resetpassword/"+email;
		    mailContent += "<h2><a href=\"" + url + "\">Reset Password</a></h2>";
		    mailContent += "<p>If you did not request a password reset, please ignore this email.</p>";
		    mailContent += "<p><br>Direction Générale des Impôts</p>";
		    
		    MimeMessage message = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		    
		    helper.setFrom("pfedgi1920@gmail.com", senderName);
		    helper.setTo(email);
		    helper.setSubject(subject);
		    helper.setText(mailContent, true);
		    
		    mailSender.send(message);
		    return true;
}else return false;
	}
	@Override
	public boolean resetPassword(ResetPassword rp) {
		Optional<Inscription> ins=this.userRepository.findByEmail(rp.getEmail());
		Optional<Compte> compte=this.compteRep.findByEmail(rp.getEmail());
		if(ins.isPresent()&&compte.isPresent()) {
			ins.get().setPassword(new BCryptPasswordEncoder().encode(rp.getPassword()));
			compte.get().setPassword(new BCryptPasswordEncoder().encode(rp.getPassword()));
			this.compteRep.save(compte.get());
			this.userRepository.save(ins.get());
			return true;
		}else return false;
	}
	

}
