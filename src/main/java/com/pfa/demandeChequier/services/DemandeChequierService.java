package com.pfa.demandeChequier.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.demandeChequier.entities.Abonne;
import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.entities.DemandeChequier;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.repositories.AbonneRepository;
import com.pfa.demandeChequier.repositories.CompteRepository;
import com.pfa.demandeChequier.repositories.DemandeChequierRepository;

@Service
public class DemandeChequierService {
	
	@Autowired
	DemandeChequierRepository demandeChequierRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	AbonneRepository abonneRepository;
	
	
	public DemandeChequier EffectuerDemandeChequier(DemandeChequier demandeChequier) throws NotFoundException
	{
		String numeroCompte = demandeChequier.getCompte().getNumeroCompte();
		Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
				.orElseThrow(() -> new NotFoundException("Aucun compte avec le numéro "+numeroCompte+" trouvé."));
		
		demandeChequier.setCompte(compte);
		demandeChequier.setDateCreation(new Date());
		demandeChequier.setStatut("Enregistrée");
		
		return demandeChequierRepository.save(demandeChequier);
	}
	
	
	public DemandeChequier getDemandeChequier(Long id)  throws NotFoundException
	{
		DemandeChequier demandeChequier = demandeChequierRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucune demande de chéquier effectuée ayant l'id "+id));
		
		return demandeChequier;
	}
	
	public DemandeChequier modifierDemandeChequier(Long id, DemandeChequier demandeChequier) 
	{
		DemandeChequier updated = demandeChequierRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucune demande de chéquier effectuée ayant l'id "+id));
			

		
		if(compteRepository.findByNumeroCompte(demandeChequier.getCompte().getNumeroCompte()).isPresent())
			updated.setCompte(compteRepository.findByNumeroCompte(demandeChequier.getCompte().getNumeroCompte()).get());
		if(demandeChequier.getDateExecution()!=null) updated.setDateExecution(demandeChequier.getDateExecution());
		if(demandeChequier.getMotif()!=null && !demandeChequier.getMotif().isEmpty()) updated.setMotif(demandeChequier.getMotif());
		if(demandeChequier.getMontantChequier()!=null ) updated.setMontantChequier(demandeChequier.getMontantChequier());
		
		
		return demandeChequierRepository.save(updated);


		
	}
	
	public DemandeChequier signer(Long id)
	{
		DemandeChequier demandeChequier = demandeChequierRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucune demande de chéquier effectuée ayant l'id "+id));
		
		
				demandeChequier.setStatut("Signée");
				return demandeChequierRepository.save(demandeChequier);
	}

}
