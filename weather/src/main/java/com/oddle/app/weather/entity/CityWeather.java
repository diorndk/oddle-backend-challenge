package com.oddle.app.weather.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class CityWeather extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 2187280266437162154L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityWeatherId;
	
	private double temperature;
	
	private int pressure;
	
	private int humidity;
	
	private double tempMin;
	
	private double tempMax;
	
	private LocalDate dt;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@Column(name = "city_id", insertable = false, updatable = false)
	private Long cityId;
	
	@ManyToMany
	@JoinTable(name = "city_weather_mapping", joinColumns=@JoinColumn(name = "city_weather_id"), inverseJoinColumns=@JoinColumn(name="weather_id"))
	private List<Weather> weathers;

}
