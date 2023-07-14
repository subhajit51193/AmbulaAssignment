package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.EndUserException;
import com.app.exception.UserLocationException;
import com.app.model.EndUser;
import com.app.model.UserLocation;
import com.app.service.EndUserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class EndUserController {

	
		
	
	@Autowired
	private EndUserService endUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	---------------------------------------------------
	
	
	

	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	/*
	
	 {
        "name": "ram",
        "email": "ram@gmail.com",
        "password": "1234",
        "authorities":[
            {
                "name": "ROLE_READER"
            },
            {
                "name": "ROLE_ADMIN"
            }
        ]
    }
	
	
	
	*/
	
	@PostMapping("/signUp")
	public ResponseEntity<EndUser> savePatientHandler(@RequestBody EndUser endUser) throws EndUserException{

		
		endUser.setPassword(passwordEncoder.encode(endUser.getPassword()));
		
		EndUser registeredEndUser= endUserService.registerEndUser(endUser);
		
		return new ResponseEntity<>(registeredEndUser,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getPatient/{email}")
	public ResponseEntity<EndUser> getPatientByEmailHandler(@PathVariable("email") String email) throws EndUserException{
		
		
		EndUser endUser= endUserService.getUserDetailsByEmail(email);
		
		return new ResponseEntity<>(endUser,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/getMyDetails")
	public ResponseEntity<EndUser> getMyDetails() throws EndUserException{
		
		EndUser myDetails = endUserService.getMyDetails();
		return new ResponseEntity<EndUser>(myDetails,HttpStatus.OK);
	}
	
	@PatchMapping("/updateEndUserName/{name}")
	public ResponseEntity<EndUser> updateEdnUserNamehandler(@PathVariable("name") String name) throws EndUserException{
		
		EndUser updated = endUserService.updateName(name);
		return new ResponseEntity<EndUser>(updated,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updateUserLocationName/{name}")
	public ResponseEntity<UserLocation> updateUserLocationnameHandler(@PathVariable("name") String name) throws UserLocationException, EndUserException{
		
		UserLocation updated = endUserService.updateData(name);
		return new ResponseEntity<UserLocation>(updated,HttpStatus.ACCEPTED);
	}
	
	
}
