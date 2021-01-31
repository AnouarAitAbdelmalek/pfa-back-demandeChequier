package com.pfa.demandeChequier.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.demandeChequier.entities.Abonne;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne, Long> {

	Optional<Abonne> findByUsername(String username);
	
	Optional<Abonne> findById(Long id);
}
