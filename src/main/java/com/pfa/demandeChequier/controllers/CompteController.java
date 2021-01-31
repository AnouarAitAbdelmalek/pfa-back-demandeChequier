package com.pfa.demandeChequier.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.exceptions.ConflictException;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.services.CompteService;


@RestController
@CrossOrigin(origins="http://localhost:3000")
public class CompteController {
	
	@Autowired
	CompteService service;
	
	
	//GET
			@GetMapping("/compte")
			@ResponseStatus(HttpStatus.OK)
			public Compte getCompte(@RequestParam(value="id") Long id)  throws NotFoundException
			{
				return service.getCompte(id);
			}
			
			
			@GetMapping("/comptes")
			@ResponseStatus(HttpStatus.OK)
			public List<Compte> getComptes() throws NotFoundException
			{
				return service.getComptes();
			}
			
			
			@GetMapping("/compte/{numeroCompte}")
			@ResponseStatus(HttpStatus.OK)
			public Compte getCompteByNumero(@PathVariable(value="numeroCompte") String numeroCompte) throws NotFoundException
			{
				return service.getByNumeroCompte(numeroCompte);
			}
			
			
	
		//POST
			
			@PostMapping("/compte")
			@ResponseStatus(HttpStatus.CREATED)
			public Compte addCompte(@RequestBody Compte compte)  throws ConflictException
			{
				return service.addCompte(compte);
			}		
		
		
			
		//DELETE
			
			@DeleteMapping("/compte/{id}")
			@ResponseStatus(HttpStatus.OK)
			public void deleteCompte(@PathVariable(value="id") Long id)  throws NotFoundException
			{
				service.deleteCompte(id);
			}
			
			
			

	

}

