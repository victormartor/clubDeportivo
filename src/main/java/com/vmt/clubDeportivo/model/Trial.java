package com.vmt.clubDeportivo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Trial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trial")
	private List<Result> results;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Point> points;
}
