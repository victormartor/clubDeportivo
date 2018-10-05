package com.vmt.clubDeportivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.ResultDAO;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	ResultDAO dao;
	
	@Override
	public Result create(Trial trial, Result result) {
		Result resultCreated = dao.save(result);
		
		//Asignarle la prueba a la que pertenece el resultado
		resultCreated.setTrial(trial);
		
		return dao.save(resultCreated);
	}

}
