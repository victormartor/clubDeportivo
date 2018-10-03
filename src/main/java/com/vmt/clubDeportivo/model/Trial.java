package com.vmt.clubDeportivo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trial")
	private List<Result> results;
}
