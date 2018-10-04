package com.vmt.clubDeportivo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.PointDAO;
import com.vmt.clubDeportivo.model.Point;

@Service
public class PointServiceImpl implements PointService{

	@Autowired
	PointDAO dao;

	@Override
	public List<Point> findAll() {
		return dao.findAll();
	}
	
	
	
}
