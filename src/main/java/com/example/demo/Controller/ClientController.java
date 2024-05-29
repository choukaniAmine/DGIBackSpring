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
import com.example.demo.dtos.CompteById;
import com.example.demo.dtos.ContribuableDtos;
import com.example.demo.dtos.DetailDeclarationDto;
import com.example.demo.dtos.NotificationDto;
import com.example.demo.dtos.ObligationFiscaledto;
import com.example.demo.dtos.PayementRequest;
import com.example.demo.dtos.PayementResponse;
import com.example.demo.dtos.PayementStatus;
import com.example.demo.dtos.ReclamationDto;
import com.example.demo.dtos.SaveDeclaration;
import com.example.demo.dtos.TypeDeclarationDto;
import com.example.demo.dtos.paiementDto;
import com.example.demo.entities.Contribuable;
import com.example.demo.entities.Declaration;
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
import com.example.demo.services.KonnectPaymentService;
import com.example.demo.services.NotificationService;
import com.example.demo.services.ObligationFiscaleService;
import com.example.demo.services.PaiementService;
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
@Autowired
private KonnectPaymentService konnectService;
@Autowired
private PaiementService paiementService;
@Autowired
private NotificationService notifservice;

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
		 @GetMapping("/declarationbycontribuable")
		 public ResponseEntity<?> getDeclarationsByMatriculeFiscale(@RequestParam("matriculeFiscale") int matriculeFiscale) {
		     List<Declaration> declarations = declarationService.getDeclarationsByMatriculeFiscale(matriculeFiscale);
		     if (declarations.isEmpty()) {
		         return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No declarations found ");
		     }
		     return ResponseEntity.ok(declarations);
		 }
		 @PostMapping("/init")
		 public ResponseEntity<?> initPayment(@RequestBody PayementRequest paymentRequest) {
		     PayementResponse response = konnectService.initiatePayment(paymentRequest);
		     return ResponseEntity.ok(response);
		 }

		 @GetMapping("/{paymentId}")
		 public ResponseEntity<?> getPaymentStatus(@PathVariable String paymentId) {
		     PayementStatus status = konnectService.getPaymentStatus(paymentId);
		     return ResponseEntity.ok(status);
		 }
		 
		 @GetMapping("/getCompte")
		 public ResponseEntity<?> getCompteById(@RequestParam("idcompte") Long idCompte){
			 CompteById compte=compteservice.getCompteByid(idCompte);
			 if(compte!=null) {
			 return ResponseEntity.ok(compte);
		 }else return ResponseEntity.status(404).body("Compte not found");
			 }
		 @PostMapping("/savePaiement")
		 public ResponseEntity<?> savePaiement(@RequestBody paiementDto paiement)
		 {
			 boolean saved=paiementService.createPaiement(paiement);
			 if(saved) {
				    return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
			 }return ResponseEntity.status(404).body("Paiement not found");
		 }
		 @GetMapping("/notification")
		 public ResponseEntity<List<NotificationDto>> getAllNotifications(@RequestParam("matricule") int matricule) {
		     try {
		         List<NotificationDto> typeList = notifservice.getNotificationByMatricule(matricule);
		         return ResponseEntity.ok(typeList);
		     } catch (ExpiredJwtException ex) {
		         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		     }
		 }
		 @PutMapping("/updatechecked")
		 public ResponseEntity<?> updateNotification(@RequestParam("id") long id) {
		        notifservice.updateNotification(id);
		        return ResponseEntity.ok().build();
		    }
		 @PutMapping("/updatedeleted")
		 public ResponseEntity<?> updatedeleted(@RequestParam("id") long id) {
		        notifservice.updateDeleted(id);
		        return ResponseEntity.ok().build();
		    }
}
