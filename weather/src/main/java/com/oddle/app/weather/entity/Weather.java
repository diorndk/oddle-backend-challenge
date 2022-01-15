package com.oddle.app.weather.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Weather extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -10456314989745240L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long weatherId;
	
	private String main;
	
	@Column(columnDefinition = "TEXT")
	private String description;

}
