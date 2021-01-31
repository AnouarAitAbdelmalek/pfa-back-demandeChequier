package com.pfa.demandeChequier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.demandeChequier.entities.DemandeChequier;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.services.DemandeChequierService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class DemandeChequierController {

	@Autowired
	DemandeChequierService demandeChequierService;
	
	
	@PostMapping("/demandeChequier")
	@ResponseStatus(HttpStatus.CREATED) 
	public DemandeChequier EffectuerDemandeChequier(@RequestBody DemandeChequier demandeChequier)  throws NotFoundException
	{
		return demandeChequierService.EffectuerDemandeChequier(demandeChequier);
	}
	
	
	@GetMapping("/demandeChequier")
	@ResponseStatus(HttpStatus.OK)
	public DemandeChequier getDemandeChequier(@RequestParam(value="id") Long id)  throws NotFoundException
	{
		return demandeChequierService.getDemandeChequier(id);
	}
	
	
	@PutMapping("/demandeChequier/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DemandeChequier modifierDemandeChequier(@PathVariable(value="id") Long id, @RequestBody DemandeChequier demandeChequier)
	{
		return demandeChequierService.modifierDemandeChequier(id, demandeChequier);
	}
	
	
	@GetMapping("/demandeChequier/signer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DemandeChequier signer(@PathVariable(value="id") Long id)
	{
		return demandeChequierService.signer(id);
	}
	
}
