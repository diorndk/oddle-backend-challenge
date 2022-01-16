package com.oddle.app.weather.dto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.oddle.app.weather.dto.openweather.WeatherDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
	"coord",
	"weathers",
	"base",
	"main",
	"visibility",
	"dt"
})
@JsonInclude(Include.NON_EMPTY)
public class CityWeatherDto {
	@JsonProperty("city_weather_id")
	private Long cityWeatherId;
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
	private int visibility;
	@Getter(AccessLevel.NONE)
	private LocalDateTime weatherDate;
	@JsonProperty("id")
	private Long cityId;
	@JsonIgnore
	private CityDto city;
	@JsonProperty("weather")
	private List<WeatherDto> weathers;
	
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
	
	@JsonProperty("coord")
	public Map<String, Object> getCoordinate() {
		Map<String, Object> coord = new HashMap<>();
		coord.put("lon", city.getLongitude());
		coord.put("lat", city.getLatitude());
		return coord;
	}
	
	@JsonProperty("name")
	public String getCityName() {
		return this.city.getCityName();
	}
	
	@JsonProperty("country")
	public String getCountry() {
		return this.city.getCountry();
	}
	
	@JsonProperty("dt")
	public long getDt() {
		return convertTimeToEpoch(this.weatherDate);
	}
	
	protected long convertTimeToEpoch(LocalDateTime time) {
		return time.toEpochSecond(ZoneOffset.UTC);
	}
}
