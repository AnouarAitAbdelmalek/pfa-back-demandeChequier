package com.pfa.demandeChequier.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="demandechequier")
public class DemandeChequier extends Demande  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	BigDecimal montantChequier;

	public DemandeChequier(BigDecimal montantChequier) {
		super();
		this.montantChequier = montantChequier;
	}

	public DemandeChequier() {
		super();
	}


	public BigDecimal getMontantChequier() {
		return montantChequier;
	}

	public void setMontantChequier(BigDecimal montantChequier) {
		this.montantChequier = montantChequier;
	}
	
	
	

}
