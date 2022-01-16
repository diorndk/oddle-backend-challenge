package com.oddle.app.weather.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.openweather.WeatherDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class CityWeatherDto {
	private String base;
	@JsonIgnore
	private double temperature;
	@JsonIgnore
	private int pressure;
	@JsonIgnore
	private int humidity;
	@JsonIgnore
	private double tempMin;
	@JsonIgnore
	private double tempMax;
	private Long dt;
	private int visibility;
	@JsonProperty("id")
	private Long cityId;
	@JsonIgnore
	private CityDto city;
	@JsonProperty("weather")
	private List<WeatherDto> weathers;
	
//	public void setBase(String base) {
//		this.base = base;
//	}
//	
//	public String getBase() {
//		return this.base;
//	}
//	
//	public void setDt(Long dt) {
//		this.dt = dt;
//	}
//	
//	public Long getDt() {
//		return this.dt;
//	}
//	
//	public void setVisibility(int visibility) {
//		this.visibility = visibility;
//	}
//	
//	public int getVisibility() {
//		return this.visibility;
//	}
	
	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		this.temperature = (double) main.get("temp");
		this.pressure = (int) main.get("pressure");
		this.humidity = (int) main.get("humidity");
		this.tempMin = (double) main.get("temp_min");
		this.tempMax = (double) main.get("temp_max");
	}
	
	@JsonProperty("main")
	public Map<String, Object> getMain() {
		Map<String, Object> main = new HashMap<>();
		main.put("temp", this.temperature);
		main.put("pressure", this.pressure);
		main.put("humidity", this.humidity);
		main.put("temp_min", this.tempMin);
		main.put("temp_max", this.tempMax);
		return main;
	}
	
//	public void setCityId(Long cityId) {
//		this.cityId = cityId;
//	}
//	
//	public Long getCityId() {
//		return this.cityId;
//	}
//	
//	public void setWeathers(List<WeatherDto> weathers) {
//		this.weathers = weathers;
//	}
//	
//	public List<WeatherDto> getWeathers() {
//		return this.weathers;
//	}
	
	@JsonProperty("name")
	public String getCityName() {
		return this.city.getCityName();
	}
}
