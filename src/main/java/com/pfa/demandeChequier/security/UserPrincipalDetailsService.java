package com.pfa.demandeChequier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfa.demandeChequier.entities.Abonne;
import com.pfa.demandeChequier.repositories.AbonneRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	AbonneRepository rep;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Abonne abonne=rep.findByUsername(username).get();
		UserPrincipal userPrincipal= new UserPrincipal(abonne);
		return userPrincipal;
	}

}
