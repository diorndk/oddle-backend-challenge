package com.oddle.app.weather.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
	
	@Autowired
	protected ModelMapper modelMapper;
	
	protected <T> T convertObject(Object source, Class<T> target) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, target);
	}
	
	protected <S, T> List<T> convertObject(List<S> source, Class<T> target) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return source.stream()
				.map(e -> modelMapper.map(e, target))
			    .collect(Collectors.toList());
	}
	
	protected Long convertTimeToEpoch(LocalDateTime time) {
		return time.toEpochSecond(ZoneOffset.UTC);
	}
}
