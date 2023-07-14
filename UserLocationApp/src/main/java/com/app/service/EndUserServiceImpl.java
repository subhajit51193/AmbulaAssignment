package com.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.EndUserException;
import com.app.exception.UserLocationException;
import com.app.model.Authority;
import com.app.model.EndUser;
import com.app.model.UserLocation;
import com.app.repository.EndUserRepository;
import com.app.repository.UserLocationRepository;

@Service
public class EndUserServiceImpl implements EndUserService{

	@Autowired
	private EndUserRepository endUserRepository;
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	/*
	 * Save User in database
	 * throws exception if null value found
	 */
	@Override
	public EndUser registerEndUser(EndUser endUser) throws EndUserException {
		
		//creating random string for unique id
		String randomId = UUID.randomUUID().toString();
		endUser.setUserId(randomId);
		
		//getting all authorities the use has
		List<Authority> authorities = endUser.getAuthorities();
		
		//setting all one by one with user
		for (Authority authority: authorities) {
			authority.setEndUser(endUser);
		}
		
		//saving user in database and return exception if null found
		EndUser newUser = endUserRepository.save(endUser);
		if (newUser != null) {
			return newUser;
		}
		else {
			throw new EndUserException("Error Occured");
		}
	}

	/*
	 * Returns user details from email
	 * throws exception if no value found
	 */
	@Override
	public EndUser getUserDetailsByEmail(String email) throws EndUserException {
		
		return endUserRepository.findByEmail(email).orElseThrow(() -> new EndUserException("User not found with email: "+email));
	}

	
	/*
	 * Returns user details for logged in user
	 * throws exception if no value found
	 */
	@Override
	public EndUser getMyDetails() throws EndUserException {

		//checking logged in user details from security context holder
		Optional<EndUser> opt = endUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		
		//checking for valid object or null value
		if (opt.isEmpty()) {
			throw new EndUserException("Not found");
		}
		else {
			return opt.get();
		}
	}

	/*
	 * Returns updated object after updating name
	 * throws exception if no user found
	 */
	@Override
	public EndUser updateName(String name) throws EndUserException {
		
		//checking logged in user details from security context holder
		Optional<EndUser> opt = endUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		
		//checking if valid object found so we can work on it
		//otherwise return exception
		if (opt.isEmpty()) {
			throw new EndUserException("Not found");
		}
		else {
			
			//obj found hence doing changes and save in database
			EndUser endUser = opt.get();
			endUser.setName(name);
			return endUserRepository.save(endUser);
			
		}
	}

	/*
	 * Returns user after deleteing the user from database
	 * throws exception if no value found
	 */
	@Override
	public EndUser deleteUser(EndUser endUser) throws EndUserException {
		
		//checking for null value if found then return exception
		//otherwise delete object from database
		if (endUser == null) {
			throw new EndUserException("Not found");
		}
		else {
			endUserRepository.delete(endUser);
			return endUser;
		}
	}

	/*
	 * Returns list of all users that are in database
	 * currently
	 * throws exception if list is empty
	 */
	@Override
	public List<EndUser> getAllUsers() throws EndUserException {
		
		//checking list of all users in database
		//if empty list found then return exception
		List<EndUser> list = endUserRepository.findAll();
		if (list.isEmpty()) {
			throw new EndUserException("Empty");
		}
		else {
			return list;
		}
	}

	
	/*
	 * Returns UserLocation object after saving the object
	 * in database
	 * throws exception if null value found
	 */
	@Override
	public UserLocation createData(UserLocation userLocation) throws UserLocationException {
		
		//creating random string for unique id
		String randomId = UUID.randomUUID().toString();
		
		//setting id
		userLocation.setUserLocationId(randomId);
		
		//checking for null so that exception can be thrown if found
		UserLocation newUserLocation = userLocationRepository.save(userLocation);
		if (newUserLocation != null) {
			return newUserLocation;
		}
		else {
			throw new UserLocationException("Error Occured");
		}
	}

	/*
	 * Returns UserLocation object from given id
	 * throws exception if no value found
	 */
	@Override
	public UserLocation getuserLocationByUserId(String userId) throws UserLocationException {
		
		//checking for UserLocation obj from userId
		//if not found then throw exception else move to next step
		Optional<UserLocation> opt = userLocationRepository.findByUserId(userId);
		if (opt == null) {
			throw new UserLocationException("Not found");
		}
		else {
			if (opt.isPresent()) {
				UserLocation userLocation = opt.get();
				return userLocation;
			}
			else {
				throw new UserLocationException("Not found");
			}
			
		}
	}

	
	/*
	 * Returns UserLocation object after updating name
	 * in database
	 * throws exception if no value found
	 */
	@Override
	public UserLocation updateData(String name) throws UserLocationException, EndUserException {
		
		//checking logged in user details from security context holder
		Optional<EndUser> opt = endUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		
		//if any obj not found then throw exception otherwise move to next step
		if (opt.isEmpty()) {
			throw new EndUserException("Not found");
		}
		else {
			EndUser endUser = opt.get();
			
			//now checking if any UserLocation obj found with given userId
			//if found move to next step or else throw exception
			Optional<UserLocation> opt2 = userLocationRepository.findByUserId(endUser.getUserId());
			if (opt2 == null) {
				throw new UserLocationException("Not Found");
			}
			else {
				UserLocation found = opt2.get();
				found.setName(name);
				return userLocationRepository.save(found);
			}
			
		}
	}

	
	/*
	 * Returns user details from given Id
	 * throws exception if no value found
	 */
	@Override
	public EndUser getUserdetailsById(String userId) throws EndUserException {
		
		//checking for EndUser obj from given id
		//if not found then throw exception
		Optional<EndUser> opt = endUserRepository.findById(userId);
		if (opt == null) {
			throw new EndUserException("Not Found");
		}
		else {
			if (opt.isPresent()) {
				EndUser endUser = opt.get();
				return endUser;
			}
			else {
				throw new EndUserException("Not Found");
			}
		}
	}

	/*
	 * Returns UserLocation object after updation is done in database
	 * throws exception if no value found
	 */
	@Override
	public UserLocation updateFullData(UserLocation userLocation) throws UserLocationException {
		
		//checking UserLocation obj from given id from object to validate 
		//if given obj is correct
		//otherwise throw exception or else move to next step
		Optional<UserLocation> opt = userLocationRepository.findById(userLocation.getUserLocationId());
		if (opt.isEmpty()) {
			throw new UserLocationException("Not Found");
		}
		else {
			UserLocation found = opt.get();
			found.setName(userLocation.getName());
			found.setLatitude(userLocation.getLatitude());
			found.setLongitude(userLocation.getLongitude());
			return userLocationRepository.save(found);
		}
	}

	/*
	 * This method returns list of n users who are closest
	 * from point(0,0) 
	 */
	
	@Override
	public List<EndUser> getUsers(int n) {
		
		double latitude = 0;
        double longitude = 0;
		
		List<UserLocation> list = userLocationRepository.findAll();
		list.sort(Comparator.comparingDouble(location ->
        distance(latitude, longitude, location.getLatitude(), location.getLongitude())
		));
		
		List<UserLocation> sortedList =  list.subList(0, Math.min(n, list.size()));
		List<EndUser> allUsers = new ArrayList<>();
		
		for (UserLocation userLocation: sortedList) {
			allUsers.add(endUserRepository.findById(userLocation.getUserId()).get());
		}
		return allUsers;
	}
	
	/*
	 * This mthod is for calculating distance between two lats and longs
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		
		
		// The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
		lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(a));
        
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;
 
        // calculate the result
        return(c * r);
	}

	

}
