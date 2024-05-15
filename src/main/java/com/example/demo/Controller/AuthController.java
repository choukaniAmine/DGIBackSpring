package com.example.demo.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AuthorizationManagerWebInvocationPrivilegeEvaluator.HttpServletRequestTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ActiviteDtos;
import com.example.demo.dtos.AuthenticationRequest;
import com.example.demo.dtos.AuthenticationResponse;
import com.example.demo.dtos.CompteDtos;
import com.example.demo.dtos.ContribuableDtos;
import com.example.demo.dtos.DeclarationDto;
import com.example.demo.dtos.DetailDeclarationDto;
import com.example.demo.dtos.DetailImpotDto;
import com.example.demo.dtos.FormeJuridiqueDtos;
import com.example.demo.dtos.ImpotDto;
import com.example.demo.dtos.ObligationFiscaledto;
import com.example.demo.dtos.PasswordDto;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.ReclamationDto;
import com.example.demo.dtos.SaveDeclaration;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.TypeDeclarationDto;
import com.example.demo.dtos.TypeImpotDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.dtos.VerificationDto;
import com.example.demo.dtos.paysDtos;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Declaration;
import com.example.demo.entities.DetailDeclaration;
import com.example.demo.entities.DetailImpot;
import com.example.demo.entities.Inscription;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.ContribuableRepository;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ActiviteService;
import com.example.demo.services.AdminService;
import com.example.demo.services.AuthService;
import com.example.demo.services.CompteService;
import com.example.demo.services.ContribuableService;
import com.example.demo.services.Declarationservice;
import com.example.demo.services.DetailDeclarationservice;
import com.example.demo.services.DetailImpotService;
import com.example.demo.services.FormeJuridiqueService;
import com.example.demo.services.ObligationFiscaleService;
import com.example.demo.services.PaysService;
import com.example.demo.services.PeriodiciteService;
import com.example.demo.services.ReclamationService;
import com.example.demo.services.TypeDeclarationService;
import com.example.demo.services.TypeImpotService;
import com.example.demo.services.UserServices;
import com.example.demo.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.persistence.JoinColumn;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
private AuthenticationManager authenticationManager;
	@Autowired
private UserServices userservice;
	@Autowired
private JwtUtil jwtUtil;
	@Autowired
private InscriptionRepository userRepository;
	@Autowired
private AuthService authService;
	@Autowired
	private FormeJuridiqueService formejuridiqueservice;
	@Autowired
	private PaysService paysservice;
	@Autowired
	private ContribuableService contribuableservice;
	@Autowired
	private ActiviteService activiteservice;
	@Autowired
	private CompteService compteService;
	@Autowired
	private CompteRepository compteRepo;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ObligationFiscaleService obligationFiscaleService;
	@Autowired
	private ReclamationService reclamationservice;
	@Autowired
	private TypeImpotService typeImpotService;
	@Autowired
	private DetailImpotService detailImpotService;
	@Autowired
	private Declarationservice declarationService;
	@Autowired
	private ContribuableRepository contribuableRepo;
	@Autowired
	private TypeDeclarationService typeDecService;
	@Autowired
	private DetailDeclarationservice detailDeclarationser;
	
	
	
@PostMapping("/signup")
public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest,HttpServletRequest request ) throws UnsupportedEncodingException, MessagingException{
	UserDto createdUserDto=authService.createCustomer(signupRequest);
	if(createdUserDto==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
	authService.sendVerificationEmail(createdUserDto);
	return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
}
@PostMapping("/compte")
public ResponseEntity<?> createCopmte(@RequestBody CompteDtos compte){
	boolean comptecree=compteService.saveCompte(compte);
	if(comptecree==false) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
	}
	
	return ResponseEntity.status(HttpStatus.CREATED).body(comptecree);
}

@PostMapping("/login")
public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws
BadCredentialsException,
DisabledException,
UsernameNotFoundException{
	try {
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
	    compteService.resetFailedAttempt(authenticationRequest.getEmail());
	
	} catch (BadCredentialsException e) {
		
		 compteService.updateFailedAttempt(authenticationRequest.getEmail());

	        Optional<Compte> compteOptional = compteRepo.findByEmail(authenticationRequest.getEmail());
	        if (compteOptional.isPresent() && compteOptional.get().getFailedAttempt() == 3) {
	           
	            compteService.blocageCompteParEmail(authenticationRequest.getEmail());
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account is disabled");
	        }

	        // Return response for incorrect password
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
	} catch (LockedException e) {
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account is disabled");
	} catch (UsernameNotFoundException e) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
    final UserDetails userDetails=userservice.userDetailsService()
            .loadUserByUsername(authenticationRequest.getEmail());
    Optional<Compte> optionalUser= compteRepo.findByEmail(userDetails.getUsername());
    final String jwt=jwtUtil.generateToken(userDetails);
    AuthenticationResponse authenticationResponse=new AuthenticationResponse();
    if(optionalUser.isPresent()) {
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setUserId(optionalUser.get().getIdCompte());
        authenticationResponse.setUserRole(optionalUser.get().getUserRole());

    }
    return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
}

@GetMapping("/verify")
public ResponseEntity<?> verificationresponse(@RequestParam("code") String parambody) {
	 if (parambody != null && parambody.endsWith("\"")) {
	        parambody = parambody.substring(0, parambody.length() - 1);
	    }

    Boolean verified = authService.verify(parambody);
    UserDto user=authService.changeuser(parambody);
    if (verified) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new VerificationDto("succefful validation",true,user));
    } else {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new VerificationDto("something went wrong",false,user));
    }
}

@PostMapping("/formejuridique")
public ResponseEntity<?> createFormejuridique(@RequestBody FormeJuridiqueDtos formejuridiqueDtos){
    FormeJuridiqueDtos formeJuridiqueCree=formejuridiqueservice.createFormeJuridique(formejuridiqueDtos);
    if(formeJuridiqueCree==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation de forme juridique!");
    return ResponseEntity.status(HttpStatus.CREATED).body(formeJuridiqueCree);

}

@PostMapping("/contribuable")
public ResponseEntity<?> createContribuable(@RequestBody ContribuableDtos contribuableDtos ){
    ContribuableDtos contribuableCree=contribuableservice.createContribuable(contribuableDtos);
    if(contribuableCree==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation de Contribuable!");
    return ResponseEntity.status(HttpStatus.CREATED).body(contribuableCree);

}
@PostMapping("/activite")
public ResponseEntity<?> createActivite(@RequestBody ActiviteDtos activiteDtos){
    ActiviteDtos activitecree=activiteservice.CreateActvite(activiteDtos);
    if(activitecree==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation d'activite!");
    return ResponseEntity.status(HttpStatus.CREATED).body(activitecree);
}
@PostMapping("/pays")
public ResponseEntity<?> createActivite(@RequestBody paysDtos paysDtos){
    paysDtos payscree=paysservice.createPays(paysDtos);
    if(payscree==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation d'pays!");
    return ResponseEntity.status(HttpStatus.CREATED).body(payscree);
}
@GetMapping("/contribuableMatricule")
public ResponseEntity<?> findByMatriculeFiscale(@RequestParam("matriculeFiscale") int matriculeFiscale) {
        ContribuableDtos contribuable = contribuableservice.findByMatriculeFiscale(matriculeFiscale);
        if (contribuable != null) 
            return ResponseEntity.ok(contribuable);

        return ResponseEntity.notFound().build();

}
/*
@PostMapping("/accept")
public ResponseEntity<?> acceptCompte(@RequestBody UserDto user ){
	CompteDtos comptemodifier=compteService.AcceptCompte(user);
	if(comptemodifier==null) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
	}
	
	return ResponseEntity.status(HttpStatus.CREATED).body(comptemodifier);
}*/
@PostMapping("/checkDeclaration")
public ResponseEntity<?> checkDeclaration(@RequestBody DeclarationDto request) {
    Declaration result = obligationFiscaleService.getNumerodeclaration(request.getCd(), request.getIdDeclaration());
    if (result!=null) {
        return ResponseEntity.ok(result);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Declaration not found!"); 
    }
}
@GetMapping("/inscription")
public ResponseEntity<List<UserDto>> getAllInscription(){
    List<UserDto> inscriptionList=adminService.getAllInscription();
    return ResponseEntity.ok(inscriptionList);
}
@PostMapping("/savepassword")
public ResponseEntity<?> savePassword(@RequestBody PasswordDto signupRequest ) throws UnsupportedEncodingException, MessagingException{

	UserDto createdUserDto=authService.validePassword(signupRequest);
    
    if(createdUserDto==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
}


}


