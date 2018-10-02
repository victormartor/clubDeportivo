package com.vmt.clubDeportivo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResult;
	
	private Float seconds;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Runner runner;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Trial trial;
	
}
