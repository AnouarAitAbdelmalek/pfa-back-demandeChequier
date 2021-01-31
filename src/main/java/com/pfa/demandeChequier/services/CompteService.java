package com.pfa.demandeChequier.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.exceptions.ConflictException;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.repositories.CompteRepository;

@Service
public class CompteService {

	@Autowired
	CompteRepository compteRepository;


	
	public Compte getCompte(Long id)  throws NotFoundException
	{
		
		Compte compte = compteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun compte avec l'id "+id+" trouv�"));
		
		return compte;
	}
	
	
	public List<Compte> getComptes()  throws NotFoundException
	{
		List<Compte> comptes= compteRepository.findAll();
				
		if(comptes.isEmpty())  throw new NotFoundException("Aucun compte trouv�");
		
		return comptes;
	}
	
	
	//Compte par numeroCompte
	public Compte getByNumeroCompte(String numeroCompte)  throws NotFoundException
	{
		Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
				.orElseThrow(() -> new NotFoundException("Ce compte n'existe pas"));
				
		return compte;
	}
	




	//Ajouter compte
	public Compte addCompte(Compte compte)  throws ConflictException
	{
		if(compteRepository.findByNumeroCompte(compte.getNumeroCompte()).isPresent()) {
			throw new ConflictException("Un compte avec le NumeroCompte "+compte.getNumeroCompte()+" existe d�j�");
		}

		return compteRepository.save(compte);

	}
	
	


	public void deleteCompte(Long id) throws NotFoundException
	{

		//v�rifier l'existence de l'compte
		if(!compteRepository.findById(id).isPresent()) throw new NotFoundException("Aucun compte avec l'id "+id+" n'est trouv�");
		compteRepository.deleteById(id);

	}
	
	
	


}
