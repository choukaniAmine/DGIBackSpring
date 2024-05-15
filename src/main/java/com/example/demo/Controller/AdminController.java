package com.example.demo.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CompteDtos;
import com.example.demo.dtos.ContribuableDtos;
import com.example.demo.dtos.DetailImpotDto;
import com.example.demo.dtos.ImpotDto;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.TypeImpotDto;
import com.example.demo.dtos.UpdatePasswordDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.entities.DetailImpot;
import com.example.demo.services.AdminService;
import com.example.demo.services.CompteService;
import com.example.demo.services.ContribuableService;
import com.example.demo.services.DetailImpotService;
import com.example.demo.services.PeriodiciteService;
import com.example.demo.services.TypeImpotService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
@Autowired
private CompteService compteService;
@Autowired
private AdminService adminService;
@Autowired
private ContribuableService contribuableservice;
@Autowired
private TypeImpotService typeImpotService;
@Autowired
private PeriodiciteService periodservice;
@Autowired
private DetailImpotService detailImpotService;


@PostMapping("/compte")
public ResponseEntity<?> createCopmte(@RequestBody CompteDtos compte){
	boolean comptecree=compteService.saveCompte(compte);
	if(comptecree==false) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
	}
	
	return ResponseEntity.status(HttpStatus.CREATED).body(comptecree);
}
@GetMapping("/inscription")
public ResponseEntity<List<UserDto>> getAllInscription(){
    List<UserDto> inscriptionList=adminService.getAllInscription();
    return ResponseEntity.ok(inscriptionList);
}
@PostMapping("/accept")
public ResponseEntity<?> acceptCompte(@RequestBody UserDto user ){
	CompteDtos comptemodifier=adminService.acceptInscri(user);
	if(comptemodifier==null) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request!");
	}
	
	return ResponseEntity.status(HttpStatus.CREATED).body(comptemodifier);
}
@GetMapping("/lescompte")
public ResponseEntity<List<CompteDtos>> getAllcompte(){



    List<CompteDtos> inscriptionList=compteService.getAllCompte();
    return ResponseEntity.ok(inscriptionList);
}
@PostMapping("/bloqueCompte")
public ResponseEntity<?> bloqueCompte(@RequestBody CompteDtos compteDto) {
    try {
        boolean compteCree = compteService.blocageCompte(compteDto);
        if (!compteCree) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de blocage de compte!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(compteCree);
    } catch (ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired");
    }
}
@PostMapping("/debloqueCompte")
public ResponseEntity<?> deblocageCompte(@RequestBody CompteDtos compteDto) {
    try {
        boolean compteCree = compteService.AcceptCompte(compteDto);
        if (!compteCree) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de deblocage de compte!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(compteCree);
    } catch (ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired");
    }
}
@GetMapping("/lesContribuables")
public ResponseEntity<List<ContribuableDtos>> getAllcontribuable() {
    try {
        List<ContribuableDtos> contribuableList = contribuableservice.lesContribuables();
        return ResponseEntity.ok(contribuableList);
    } catch (ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
@PostMapping("/changepassword")
public ResponseEntity<?> changePswword(@RequestBody UpdatePasswordDto up){
	CompteDtos cd=adminService.changePassword(up);
	if(cd==null) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de modifer password!");
	}
	 return ResponseEntity.status(HttpStatus.CREATED).body(cd);
}
@PostMapping("/typeImpot")
public ResponseEntity<?> createImpot(@RequestBody TypeImpotDto typeImpot ){
    TypeImpotDto impotcree=typeImpotService.saveImpot(typeImpot);
    if(impotcree==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation de Contribuable!");
    return ResponseEntity.status(HttpStatus.CREATED).body(impotcree);

}
@GetMapping("/lesperiodes")
public ResponseEntity<List<PeriodeDto>> getAllPeriode() {
    try {
        List<PeriodeDto> contribuableList = periodservice.findAllPeriode();
        return ResponseEntity.ok(contribuableList);
    } catch (ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
@GetMapping("/lesimpots")
public ResponseEntity<List<TypeImpotDto>> getAllImpot() {
    try {
        List<TypeImpotDto> impotList = typeImpotService.findAllImpot();
        return ResponseEntity.ok(impotList);
    } catch (ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
@PostMapping("/ajoutdetail")
public ResponseEntity<?> createDetailImpot(@RequestBody DetailImpotDto typeImpot ){
    DetailImpotDto impotcree=detailImpotService.saveDetailImpot(typeImpot);
    if(impotcree==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de creation de Contribuable!");
    return ResponseEntity.status(HttpStatus.CREATED).body(impotcree);

}
@GetMapping("/detailimpot")
public ResponseEntity<?> findByimpot(@RequestParam("libelle") String libelle) {
    List<DetailImpot> listDetail= detailImpotService.findByTypeImpot(libelle);
        if (listDetail != null)
            return ResponseEntity.ok(listDetail);

        return ResponseEntity.notFound().build();

}
@GetMapping("/typeimpot")
public ResponseEntity<?> findTypeImpot(@RequestParam("libelle") String libelle) {
    TypeImpotDto listDetail= typeImpotService.findTypeImpotByLibelle(libelle);
        if (listDetail != null)
            return ResponseEntity.ok(listDetail);

        return ResponseEntity.notFound().build();

}
@PutMapping("/updateformule")
public ResponseEntity<?> updateformule(@RequestBody ImpotDto request) {
    boolean result =typeImpotService.updateImpotFormule(request); 
    if (result) {
        return ResponseEntity.ok(result);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impot not found!"); 
    }
}
}
