package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.model.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, String>{

	/*
	 * custom method to get UserLocation from userId
	 */
	public Optional<UserLocation> findByUserId(String userId);
}
