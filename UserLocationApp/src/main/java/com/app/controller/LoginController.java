package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EndUser;
import com.app.repository.EndUserRepository;

@RestController
public class LoginController {

	@Autowired
	private EndUserRepository endUserRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<EndUser> getLoggedInPatientDetailsHandler(Authentication auth){
		
		System.out.println(auth);
		
		 EndUser endUser= endUserRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		
		 //to get the token in body, pass HttpServletResponse inside this method parameter 
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		 
		 
		 return new ResponseEntity<>(endUser, HttpStatus.ACCEPTED);
		
		
	}
	
}
