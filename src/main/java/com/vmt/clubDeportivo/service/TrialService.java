package com.vmt.clubDeportivo.service;

import java.util.List;

import com.vmt.clubDeportivo.model.ClubPointsDTO;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

public interface TrialService {

	Trial findById(Integer idTrial);
	
	Trial create(Trial trial);
	
	Result insertResult(Integer idTrial, Result result);
	
	List<Result> getResults(Integer idTrial);
	
	List<Trial> findAll();
	
	//Clasificaciones por categorias
	List<Result> getMaster(Integer idTrial, Integer category);
	
	//Clasificacion de clubs por puntos
	List<ClubPointsDTO> getClubClasi();
	
}
