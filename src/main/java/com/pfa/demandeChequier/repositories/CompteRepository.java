package com.pfa.demandeChequier.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.demandeChequier.entities.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

	Optional<Compte> findById(Long id);

	Optional<Compte> findByNumeroCompte(String numeroCompte);



}
