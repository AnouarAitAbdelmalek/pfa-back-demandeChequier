package com.pfa.demandeChequier.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.demandeChequier.entities.Beneficiaire;
import com.pfa.demandeChequier.exceptions.ConflictException;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.repositories.BeneficiaireRepository;

@Service
public class BeneficiaireService {
	
	
	@Autowired
	BeneficiaireRepository beneficiaireRepository;
	
	
	
	public Beneficiaire addBeneficiaire(Beneficiaire beneficiaire) throws ConflictException
	{
		if(beneficiaireRepository.findByNumeroCompte(beneficiaire.getNumeroCompte()).isPresent()) 
			throw new ConflictException("Un b�n�ficiaire avec le num�ro de compte "+beneficiaire.getNumeroCompte()+" existe d�j�.");
		
		return beneficiaireRepository.save(beneficiaire);
		
	}
	
	
	public Beneficiaire getBeneficiaire(Long id) throws NotFoundException
	{
		
		Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun b�n�ficiaire avec l'id "+id+" trouv�."));
		
		return beneficiaire;
	}
	
	
	
	
	public void deleteBeneficiaire(Long id) throws NotFoundException
	{
		if(!beneficiaireRepository.findById(id).isPresent())  throw new NotFoundException("Aucun b�n�ficiaire avec l'id "+id+" trouv�.");
		beneficiaireRepository.deleteById(id);
	}

}
