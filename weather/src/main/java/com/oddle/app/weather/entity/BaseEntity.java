package com.oddle.app.weather.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity {
	
	@JsonIgnore
	@Column(nullable = false, updatable = false)
	protected LocalDateTime createdDate;
	@JsonIgnore
	@Column(nullable = false)
	protected LocalDateTime modifiedDate;
	
	@PrePersist
	public void prePersist() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}
}
