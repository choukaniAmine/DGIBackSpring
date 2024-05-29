package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ReclamtionResponse;
import com.example.demo.dtos.UpdateSolutionRecDto;
import com.example.demo.entities.DetailDeclaration;
import com.example.demo.entities.Reclamation;
import com.example.demo.services.DetailDeclarationservice;
import com.example.demo.services.NotificationService;
import com.example.demo.services.ReclamationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Responsable")
public class ResponsableController {
	@Autowired
	private ReclamationService reclamationservice;
	@Autowired
	private DetailDeclarationservice detailservice;
	@Autowired
	private NotificationService notificationservice;
	
	
	
	@PutMapping("/updatereclamation")
	public ResponseEntity<?> saveReclamation(@RequestBody UpdateSolutionRecDto reclamationDto) {
	    Reclamation saved = reclamationservice.updateSolution(reclamationDto);
	    if (saved!=null) {
	    	notificationservice.creatNotification(reclamationDto.getIdReclamation(), reclamationDto.getSolution());
	        return ResponseEntity.ok(saved);
	        
	    } else {
	        return ResponseEntity.badRequest().body(null);
	    }
	}
	@GetMapping("lesreclamations")
	public ResponseEntity<?> lesreclamations(){
		List<ReclamtionResponse> list=reclamationservice.getAllReclamation();
		return ResponseEntity.ok(list);
	}
	@GetMapping("lesdetailsdeclaration")
	public ResponseEntity<?> lesdetails(@RequestParam("declaration") Long iddeclaration){
		List<DetailDeclaration> list=detailservice.getdetailBydeclarationId(iddeclaration);
		return ResponseEntity.ok(list);
	}
}
