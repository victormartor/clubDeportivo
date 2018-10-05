package com.vmt.clubDeportivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.TrialDAO;
import com.vmt.clubDeportivo.model.Trial;

@Service
public class TrialServiceImpl implements TrialService{

	@Autowired
	TrialDAO dao;
	
	@Autowired
	PointServiceImpl pointService;
	
	@Override
	public Trial create(Trial trial) {
		
		Trial trialCreated = dao.save(trial);
		
		//Asignar puntuaje para la prueba
		trialCreated.setPoints(pointService.findAll());
		
		return dao.save(trialCreated);
	}

}
