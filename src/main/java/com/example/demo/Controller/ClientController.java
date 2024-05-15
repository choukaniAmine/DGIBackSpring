package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.script.ScriptException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CalculationRequest;
import com.example.demo.dtos.ContribuableDtos;
import com.example.demo.dtos.DetailDeclarationDto;
import com.example.demo.dtos.ObligationFiscaledto;
import com.example.demo.dtos.ReclamationDto;
import com.example.demo.dtos.SaveDeclaration;
import com.example.demo.dtos.TypeDeclarationDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.DetailImpot;
import com.example.demo.entities.Reclamation;
import com.example.demo.entities.TypeImpot;
import com.example.demo.repository.ContribuableRepository;
import com.example.demo.repository.TypeImpotRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.CompteService;
import com.example.demo.services.ContribuableService;
import com.example.demo.services.Declarationservice;
import com.example.demo.services.DetailDeclarationservice;
import com.example.demo.services.DetailImpotService;
import com.example.demo.services.ObligationFiscaleService;
import com.example.demo.services.ReclamationService;
import com.example.demo.services.TypeDeclarationService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
	 @Autowired
	    private CompteService compteservice;
	    @Autowired
	    private AdminService adminservice;
	    @Autowired
	    private ContribuableService contribuableservice;
	    @Autowired
		private DetailImpotService detailImpotService;
		@Autowired
		private Declarationservice declarationService;
		@Autowired
		private ContribuableRepository contribuableRepo;
		@Autowired
		private ObligationFiscaleService obligationFiscaleService;
		@Autowired
		private TypeDeclarationService typeDecService;
	    @Autowired
	    private TypeImpotRepository typeImpotRepo;
@Autowired
private ReclamationService reclamationservice;

@Autowired
private DetailDeclarationservice detailDeclarationser;


	     @GetMapping("/contribuable/{id}")
	        public ResponseEntity<?> findContribuableByIdCompte(@PathVariable("id") long id) {
	            ContribuableDtos contribuable = contribuableservice.findContribuableByIdCompte(id);
	            if (contribuable != null) {
	                return new ResponseEntity<>(contribuable, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
	            }
	        }
	     @PostMapping("/savereclamation")
	     public ResponseEntity<?> saveReclamation(@RequestBody ReclamationDto reclamationDto) {
	        Reclamation saved = reclamationservice.saveReclamation(reclamationDto);
	         if (saved!=null) {
	             return ResponseEntity.ok(saved);
	         } else {
	             return ResponseEntity.badRequest().body(null);
	         }
	     }
	  
	     @GetMapping("/obligationbycontribuable/{contribuableId}")
	     public ResponseEntity<?> getObligationsByContribuable(@PathVariable Long contribuableId) {
	         Optional<Contribuable> cd = this.contribuableRepo.findById(contribuableId);
	         if(cd.isPresent()) {
	         List<ObligationFiscaledto> obligations = obligationFiscaleService.getObligationFiscaleDeContribuable(cd.get());
	         return ResponseEntity.ok(obligations);
	     }else return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de création de déclaration!");
	         }
	     @PostMapping("/declaration")
	     public ResponseEntity<?> createDeclaration(@RequestBody SaveDeclaration declarationDtos) {
	         Map<DetailImpot, DetailDeclarationDto> detailMap = declarationService.saveDeclaration(declarationDtos);

	         if (detailMap.isEmpty()) {
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probleme de création de déclaration!");
	         } else {
	             return ResponseEntity.status(HttpStatus.CREATED).body(detailMap);
	         }
	     }
	     @GetMapping("/lestypedeclaration")
	     public ResponseEntity<List<TypeDeclarationDto>> getAllTypeDeclaration() {
	         try {
	             List<TypeDeclarationDto> typeList = typeDecService.findAllTypeDeclaration();
	             return ResponseEntity.ok(typeList);
	         } catch (ExpiredJwtException ex) {
	             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	         }
	     }
		 @GetMapping("/contribuableMatricule")
		 public ResponseEntity<?> findByMatriculeFiscale(@RequestParam("matriculeFiscale") int matriculeFiscale) {
		 		ContribuableDtos contribuable = contribuableservice.findByMatriculeFiscale(matriculeFiscale);
		         if (contribuable != null)
		         	return ResponseEntity.ok(contribuable);

		         return ResponseEntity.notFound().build();

		 }
		 @PostMapping("/calculate")
		 public double calculate(@RequestBody CalculationRequest request) {
		     String formula = request.getFormula();
		     for (String key : request.getValues().keySet()) {
		         formula = formula.replaceAll("\\b" + key + "\\b", String.valueOf(request.getValues().get(key)));
		     }

		     try (Context context = Context.create()) {
		         Value result = context.eval("js", formula);
		         return result.asDouble();
		     } catch (PolyglotException e) {
		         // Gérer l'exception
		         e.printStackTrace();
		         throw new RuntimeException("Error evaluating the formula: " + formula, e);
		     }
		 }

		 @PutMapping("/updatedetaildeclaration")
		 public ResponseEntity<?> updateDeclaration(@RequestBody DetailDeclarationDto request) {
		     boolean result =detailDeclarationser.updateDetail(request); 
		     if (result) {
		         return ResponseEntity.ok(result);
		     } else {
		         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detail declaration not found!"); 
		     }
		 }
		 @GetMapping("/formule")
		 public ResponseEntity<?> findFormuleByLibelle(@RequestParam("libelle") String libelle){
			 Optional<TypeImpot> type=typeImpotRepo.findByLibelle(libelle);
			 if(type.isPresent()) {
				 return ResponseEntity.ok(type.get());

			 }
			 return ResponseEntity.notFound().build();
		 }
}
