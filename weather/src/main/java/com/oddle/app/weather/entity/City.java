package com.oddle.app.weather.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class City extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 6303119116142981344L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;
	
	private String cityName;
	
	private String country;
	
	private double longitude;
	
	private double latitude;
}
