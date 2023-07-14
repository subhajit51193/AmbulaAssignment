package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.EndUserException;
import com.app.exception.UserLocationException;
import com.app.model.EndUser;
import com.app.model.UserLocation;
import com.app.service.EndUserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EndUserService endUserService;
	
	@PostMapping("/createData")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserLocation> createDatahandler(@RequestBody UserLocation userLocation) throws UserLocationException{
		
		UserLocation newUserLocation = endUserService.createData(userLocation);
		return new ResponseEntity<UserLocation>(newUserLocation,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateData")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserLocation> updateData(@RequestBody UserLocation userLocation) throws UserLocationException{
		
		UserLocation updated =endUserService.updateFullData(userLocation);
		return new ResponseEntity<UserLocation>(updated,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EndUser> deleteUserHandler(@PathVariable("userId") String userId) throws EndUserException{
		
		EndUser endUser = endUserService.getUserdetailsById(userId);
		EndUser deleted = endUserService.deleteUser(endUser);
		return new ResponseEntity<EndUser>(deleted,HttpStatus.ACCEPTED);
	}
}
