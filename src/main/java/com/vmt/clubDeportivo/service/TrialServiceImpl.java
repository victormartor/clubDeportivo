package com.vmt.clubDeportivo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.TrialDAO;
import com.vmt.clubDeportivo.model.Point;
import com.vmt.clubDeportivo.model.Trial;

@Service
public class TrialServiceImpl implements TrialService{

	@Autowired
	TrialDAO dao;
	
	@Autowired
	PointServiceImpl pointService;
	
	@Override
	public Trial create(Trial trial) {
		
		//Asignar puntuaje para la prueba
		trial.setPoints(pointService.findAll());
		
		return dao.save(trial);
	}

}
