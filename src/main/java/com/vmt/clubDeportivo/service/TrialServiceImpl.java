package com.vmt.clubDeportivo.service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.TrialDAO;
import com.vmt.clubDeportivo.dto.ClubPointsDTO;
import com.vmt.clubDeportivo.error.FileException;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.mapper.ClubPointsMapper;
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
	
	@Autowired
	ClubPointsMapper mapperClubPoints;
	
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
		final Integer trialYear = Integer.parseInt(trial.getDate().toString().substring(0, 4));
		
		//llamada a funcion group by segun la categoria
		List<Result> results = new ArrayList<>();
		switch(category) {
		case 40:
			results = dao.getMaster40(trial, trialYear);
			break;
			
		case 30:
			results = dao.getMaster30(trial, trialYear);
			break;
			
		case 20:
			results = dao.getMaster20(trial, trialYear);
			break;
			
		default:
			throw new NotFoundException();
		}
		
		return results;
	}

	//Clasificacion de los clubs por puntos
	@Override
	public List<ClubPointsDTO> getClubClasi(Integer idTrial, Boolean useCat) {
		final Trial trial = this.findById(idTrial);
		
		List<Point> points = trial.getPoints();	//sistema de puntuaje de la prueba
		//Ordenar el sistema de puntuaje por la posicion
		points.sort((a,b) -> a.getPosition() < b.getPosition() ? -1 : a.getPosition() == b.getPosition() ? 0 : 1);
		
		//Obtener puntuaciones de los clubs teniendo en cuenta la categoria o no
		Map<String, Integer> puntuation = new HashMap<>();
		
		if(useCat)
			puntuation = getPuntuationCat(points, trial);
		else
			puntuation = getPuntuation(points, trial);
		
		//Convertir en una lista
		List<ClubPointsDTO> puntuationList = mapperClubPoints.mapToDTO(puntuation);
		
		//Ordenar la lista de clubs segun la puntuacion
		puntuationList.sort((a,b) -> a.getPoints() > b.getPoints() ? -1 : a.getPoints() == b.getPoints() ? 0 : 1);
		
		//Guardar en fichero
		writeFile(puntuationList);
		
		return puntuationList;
	}
	
	//Utilizar los resultados de la prueba sin dividir por categorias
	private Map<String, Integer> getPuntuation(List<Point> points, Trial trial){
		//Resultados de la prueba sin dividir por categorias
		List<Result> results = trial.getResults();
		//ordenar los resultados de la prueba por segundos
		results.sort((a,b) -> a.getSeconds() < b.getSeconds() ? -1 : a.getSeconds() == b.getSeconds() ? 0 : 1);
		
		return setPuntuation(points, results);
	}
	
	//Utilizar los resultados de la prueba dividiendo por categorias
		private Map<String, Integer> getPuntuationCat(List<Point> points, Trial trial){
			
			//Categoria master40
			Map<String, Integer> puntuation = setPuntuation(points, this.getMaster(trial.getId(), 40));
			 
			//Categoria master30
			Map<String, Integer> puntuationMaster30 = setPuntuation(points, this.getMaster(trial.getId(), 30));
			//unir los dos maps
			puntuationMaster30.forEach((k, v) -> {
				puntuation.merge(k, v, (v1, v2) -> v1 += v2);
			});
			
			//Categoria master20
			Map<String, Integer> puntuationMaster20 = setPuntuation(points, this.getMaster(trial.getId(), 20));
			//unir los dos maps
			puntuationMaster20.forEach((k, v) -> {
				puntuation.merge(k, v, (v1, v2) -> v1 += v2);
			});
			
			
			return puntuation;
		}
	
	//Asignar puntuacion segun la clasificacion
	private Map<String, Integer> setPuntuation(List<Point> points, List<Result> results){
		
		Map<String, Integer> puntuation = new HashMap<>();
		
		for(Integer i = 0; i<points.size(); i++) {		
			if(results.size() > i) {
				String clubName = results.get(i).getRunner().getClub().getName();
				Integer clubPoints = points.get(i).getPuntuation();
				
				if(puntuation.containsKey(clubName))
					puntuation.put(clubName, puntuation.get(clubName)+clubPoints);
				else
					puntuation.put(clubName, clubPoints);
			}	
		}
		
		return puntuation;
	}
	
	//Guardar lista de clubs en un fichero
	private void writeFile(List<ClubPointsDTO> clubs) {
		FileWriter file = null;
		try {

			file = new FileWriter("clubs.txt");

			// Escribimos linea a linea en el fichero
			file.write("CLUB\tPUNTOS\n");
			for(ClubPointsDTO c : clubs)
				file.write(c.getName()+"\t"+c.getPoints()+"\n");

			file.close();

		} catch (Exception ex) {
			throw new FileException();
		}
	}
	

}
