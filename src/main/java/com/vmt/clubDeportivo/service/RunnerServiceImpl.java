package com.vmt.clubDeportivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.RunnerDAO;
import com.vmt.clubDeportivo.model.Runner;

@Service
public class RunnerServiceImpl implements RunnerService{

	@Autowired
	RunnerDAO dao;
	
	@Override
	public Runner create(Runner runner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Runner> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer idRunner, Runner runnerToUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer idRunner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Runner> findAll() {
		return dao.findAll();
	}

}
