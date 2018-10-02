package com.vmt.clubDeportivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Trial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTrial;
	
	private String name;
	
}
