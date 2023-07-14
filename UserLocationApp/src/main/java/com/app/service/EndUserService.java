package com.app.service;

import java.util.List;

import com.app.exception.EndUserException;
import com.app.exception.UserLocationException;
import com.app.model.EndUser;
import com.app.model.UserLocation;

public interface EndUserService {

	/*
	 * Register new User into database
	 */
	public EndUser registerEndUser(EndUser endUser)throws EndUserException;
	
	
	/*
	 * Get user details by email
	 */
	public EndUser getUserDetailsByEmail(String email) throws EndUserException;
	
	
	/*
	 * Get user details by Id
	 */
	public EndUser getUserdetailsById(String userId)throws EndUserException;
	
	/*
	 * Get logged in user details
	 */
	public EndUser getMyDetails() throws EndUserException;
	
	
	/*
	 * Update name of logged in user
	 */
	public EndUser updateName(String name)throws EndUserException;
	
	
	/*
	 * Delete user from database
	 */
	public EndUser deleteUser(EndUser endUser)throws EndUserException;
	
	
	/*
	 * Get all users from database
	 */
	public List<EndUser> getAllUsers()throws EndUserException;
	
	
	/*
	 * Register new UserLocation object into database
	 */
	public UserLocation createData(UserLocation userLocation) throws UserLocationException;
	
	
	/*
	 * Get userLocation by userID
	 */
	public UserLocation getuserLocationByUserId(String userId)throws UserLocationException;
	
	
	/*
	 * Update name of UserLocation object in database
	 */
	public UserLocation updateData(String name)throws UserLocationException, EndUserException;
	
	
	/*
	 * Update any content or whole content of UserLocation 
	 * object in database
	 */
	public UserLocation updateFullData(UserLocation userLocation)throws UserLocationException;
	
	/*
	 * Get n nearest users from point (0,0)
	 */
	public List<EndUser> getUsers(int n);
	
}
