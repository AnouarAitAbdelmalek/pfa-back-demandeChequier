package com.pfa.demandeChequier.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfa.demandeChequier.entities.Compte;
import com.pfa.demandeChequier.entities.DemandeChequier;

public interface DemandeChequierRepository extends JpaRepository<DemandeChequier, Long> {

	List<DemandeChequier> findAllByCompte(Compte compte);

	Optional<DemandeChequier> findById(Long id);

}
