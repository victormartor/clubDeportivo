package com.vmt.clubDeportivo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.TrialDAO;
import com.vmt.clubDeportivo.dto.ClubPointsDTO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Club;
import com.vmt.clubDeportivo.model.Point;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

@Service
public class TrialServiceImpl implements TrialService{

	@Autowired
	TrialDAO dao;
	
	@Autowired
	PointServiceImpl pointService;
	
	@Autowired
	ResultService resultService;
	
	@Override
	public Trial create(Trial trial) {
		
		Trial trialCreated = dao.save(trial);
		
		//Asignar puntuaje para la prueba
		trialCreated.setPoints(pointService.findAll());
		
		return dao.save(trialCreated);
	}

	@Override
	public Result insertResult(Integer idTrial, Result result) {
		Trial trial = this.findById(idTrial);
		
		return resultService.create(trial, result);
	}

	@Override
	public Trial findById(Integer idTrial) {
		return dao.findById(idTrial).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Result> getResults(Integer idTrial) {
		final Trial trial = this.findById(idTrial);
		return trial.getResults();
	}

	@Override
	public List<Trial> findAll() {
		return dao.findAll();
	}

	//Clasificacion por categorias
	@Override
	public List<Result> getMaster(Integer idTrial, Integer category) {
		final Trial trial = this.findById(idTrial);
		
		//llamada a funcion group by segun la categoria
		List<Result> results = new ArrayList<>();
		switch(category) {
		case 40:
			results = dao.getMaster40(trial);
			break;
			
		case 30:
			results = dao.getMaster30(trial);
			break;
			
		case 20:
			results = dao.getMaster20(trial);
			break;
			
		default:
			throw new NotFoundException();
		}
		
		return results;
	}

	//Clasificacion de los clubs por puntos
	@Override
	public List<ClubPointsDTO> getClubClasi(Integer idTrial) {
		final Trial trial = this.findById(idTrial);
		
		List<Point> points = trial.getPoints();	//sistema de puntuaje de la prueba
		//Ordenar el sistema de puntuaje por la posicion
		points.sort((a,b) -> a.getPosition() < b.getPosition() ? -1 : a.getPosition() == b.getPosition() ? 0 : 1);
		
		List<Result> results = trial.getResults();
		//ordenar los resultados de la prueba por segundos
		results.sort((a,b) -> a.getSeconds() < b.getSeconds() ? -1 : a.getSeconds() == b.getSeconds() ? 0 : 1);

		//Asignar puntuacion segun la clasificacion de la prueba
		List<ClubPointsDTO> puntuation = setPuntuation(points, results);
		
		//Guardar en fichero
		
		return puntuation;
	}
	
	//Asignar puntuacion segun la clasificacion
	private List<ClubPointsDTO> setPuntuation(List<Point> points, List<Result> results){
		
		List<ClubPointsDTO> puntuation = new ArrayList<>();
		
		for(Integer i = 0; i<points.size(); i++) {
			Club club = results.get(i).getRunner().getClub();
			
			
		}
		
		return puntuation;
	}
	
	//Comprobar si un club esta añadido a la lista de clubs

}
