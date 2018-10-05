package com.vmt.clubDeportivo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer position;
	
	private Integer puntuation;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "points")
	private List<Trial> trials;
}
