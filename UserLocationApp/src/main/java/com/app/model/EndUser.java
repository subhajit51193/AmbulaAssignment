package com.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndUser {

	@Id
	private String userId;
	
	private String name;
	@Column(unique = true)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "endUser",fetch=FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<>();
}
