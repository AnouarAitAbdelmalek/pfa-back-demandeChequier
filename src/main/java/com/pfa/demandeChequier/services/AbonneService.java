package com.pfa.demandeChequier.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.demandeChequier.entities.Abonne;
import com.pfa.demandeChequier.entities.Beneficiaire;
import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.entities.DemandeChequier;
import com.pfa.demandeChequier.exceptions.ConflictException;
import com.pfa.demandeChequier.exceptions.NotFoundException;
import com.pfa.demandeChequier.repositories.AbonneRepository;
import com.pfa.demandeChequier.repositories.CompteRepository;
import com.pfa.demandeChequier.repositories.DemandeChequierRepository;

@Service
public class AbonneService {

	@Autowired
	AbonneRepository abonneRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	DemandeChequierRepository demandeChequierRepository;
	

	//Ajouter un abonn�
	public Abonne addAbonne(Abonne abonne)  throws ConflictException
	{
		if(abonneRepository.findByUsername(abonne.getUsername()).isPresent()) 
			throw new ConflictException("Un abonn� avec le username "+abonne.getUsername()+" existe d�j�.");
		
		abonne.setPassword(new BCryptPasswordEncoder().encode(abonne.getPassword()));
		
		return abonneRepository.save(abonne);
	}
	
	
	
	
	public Abonne getAbonne(Long id)  throws NotFoundException
	{
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun abonn� avec l'id "+id+" trouv�."));
		
		return abonne;
	}
	
	
	
	public List<Abonne> getAbonnes()  throws NotFoundException
	{
		List<Abonne> abonnes = abonneRepository.findAll();
		if(abonnes.isEmpty()) throw new NotFoundException("Aucun abonn� trouv�.");
		
		return abonnes;
	}
	
	
	
	
	
	
	//Liste des comptes d'un abonn�
	public List<Compte> getComptes(Long id)  throws NotFoundException
	{
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun abonn� avec l'id "+id+" trouv�."));
		if(abonne.getComptes().isEmpty()) throw new NotFoundException("Cet abonn� n'a aucun compte.");
		
		return abonne.getComptes();
	}
	
	
	
	//Liste des b�n�ficiaires d'un abonn�
		public List<Beneficiaire> getBeneficiaires(Long id)  throws NotFoundException
		{
			Abonne abonne = abonneRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Aucun abonn� avec l'id "+id+" trouv�."));
			if(abonne.getBeneficiaires().isEmpty()) throw new NotFoundException("Cet abonn� n'a aucun b�n�ficiaire.");
			
			return abonne.getBeneficiaires();
		}
	
	
	
	
	
	//Trouver un abonn� par son username
	public Abonne getByUsername(String username)  throws NotFoundException
	{
		return abonneRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Aucun abonn� avec le username "+username+" trouv�."));
	}
	
	
	
	
	
	
	//Supprimer un abonn�
	public void deleteAbonne(Long id)
	{
		if(!abonneRepository.findById(id).isPresent()) throw new NotFoundException("Aucun abonn� avec l'id "+id+" n'est trouv�.");
		abonneRepository.deleteById(id);;
	}
	
	
	
	public List<DemandeChequier> demandeChequiers(Long id)
	{
		List<DemandeChequier> demandeChequiers = new ArrayList<DemandeChequier>();
		
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() ->  new NotFoundException("Aucun abonn� avec l'id "+id+" n'est trouv�."));
		
		List<Compte> comptes = abonne.getComptes();
		
		for (Compte compte : comptes) {
			Long idCompte= compte.getId();
			compte = compteRepository.findById(idCompte)
					.orElseThrow(() -> new NotFoundException("Aucun compte avec l'id "+ idCompte +" trouv�"));
			
			demandeChequiers.addAll(demandeChequierRepository.findAllByCompte(compte));
		}
		
		
		
		
		
		if(demandeChequiers.isEmpty()) throw new NotFoundException("Aucune demande de chequier effectu�e");
		
		return demandeChequiers;		
		
	}
	
	
	
	
}
