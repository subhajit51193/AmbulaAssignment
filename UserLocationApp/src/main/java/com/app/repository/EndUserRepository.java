package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.EndUser;

public interface EndUserRepository extends JpaRepository<EndUser, String>{

	/*
	 * custom method to get EndUser object from email
	 */
	public Optional<EndUser> findByEmail(String email);
}
