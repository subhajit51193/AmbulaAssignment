package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLocation {

	@Id
	private String userLocationId;
	private String userId;
	private String name;
	private Double latitude;
	private Double longitude;
}
