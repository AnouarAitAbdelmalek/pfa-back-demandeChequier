package com.pfa.demandeChequier.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="beneficiaire")
public class Beneficiaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String nom;
	
	String prenom;
	
	String numeroCompte;
	
	@ManyToOne
	Abonne abonne;


	public Beneficiaire() {
		super();
	}


	public Beneficiaire(Long id, String nom, String prenom, String numeroCompte, Abonne abonne) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numeroCompte = numeroCompte;
		this.abonne = abonne;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNumeroCompte() {
		return numeroCompte;
	}


	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}


	public Abonne getAbonne() {
		return abonne;
	}


	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}



	
	

}