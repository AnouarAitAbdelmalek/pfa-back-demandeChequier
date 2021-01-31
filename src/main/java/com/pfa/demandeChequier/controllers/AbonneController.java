package com.pfa.demandeChequier.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.demandeChequier.entities.Abonne;
import com.pfa.demandeChequier.entities.Beneficiaire;
import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.entities.DemandeChequier;
import com.pfa.demandeChequier.exceptions.ConflictException;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.services.AbonneService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class AbonneController {

	@Autowired
	AbonneService service;
	
	

	//POST
	@PostMapping("/abonne")
	@ResponseStatus(HttpStatus.CREATED)  
	public Abonne addAbonne(@RequestBody Abonne abonne) throws ConflictException
	{
		return service.addAbonne(abonne);
	}
	



	//GET
	@GetMapping("/abonne")
	@ResponseStatus(HttpStatus.OK)
	public Abonne getAbonne(@RequestParam(value="id") Long id)  throws NotFoundException
	{
		return service.getAbonne(id);
		
	}
	
	
	//GET
		@GetMapping("/abonnes")
		@ResponseStatus(HttpStatus.OK)
		public List<Abonne> getAbonnes()  throws NotFoundException
		{
			return service.getAbonnes();
			
		}
	
	
	
	//GET BY USERNAME
	@GetMapping("abonne/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Abonne getByUsername(@PathVariable(value="username") String username)  throws NotFoundException
	{
		return service.getByUsername(username);
	}
	
	
	
	//GET COMPTES
	@GetMapping("/abonne/{id}/comptes")
	@ResponseStatus(HttpStatus.OK)
	public List<Compte> getComptes(@PathVariable(value="id") Long id)  throws NotFoundException
	{
		return service.getComptes(id);
	}
	

	//GET BENEFICIAIRES
	@GetMapping("/abonne/{id}/beneficiaires")
	@ResponseStatus(HttpStatus.OK)
	public List<Beneficiaire> getBeneficiaires(@PathVariable(value="id") Long id)  throws NotFoundException
	{
		return service.getBeneficiaires(id);
	}
	
	
	//DELETE
	@DeleteMapping("/abonne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAbonne(@PathVariable(value="id") Long id)  throws NotFoundException
	{
		service.deleteAbonne(id);
	}
	
	
	@GetMapping("/abonne/{id}/demandeChequiers")
	public List<DemandeChequier> demandeChequiers(@PathVariable(value="id") Long id) throws NotFoundException
	{
		return service.demandeChequiers(id);
	}


}
