package com.vmt.clubDeportivo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.RunnerDAO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Runner;

@Service
public class RunnerServiceImpl implements RunnerService{

	@Autowired
	RunnerDAO dao;
	
	@Autowired
	ClubServiceImpl clubService;
	
	@Override
	public Runner create(Runner runner) {
		return dao.save(runner);
	}

	@Override
	public Runner findById(Integer id) {
		return dao.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public void update(Integer idRunner, Runner runnerToUpdate) {
		dao.findById(idRunner).orElseThrow(NotFoundException::new);
		dao.save(runnerToUpdate);
		
	}

	@Override
	public void delete(Integer idRunner) {
		dao.delete(dao.findById(idRunner).orElseThrow(NotFoundException::new));
	}

	@Override
	public List<Runner> findAll(Pageable pagination, String name) {
		return dao.findByNameContaining(name, pagination).getContent();
	}

	@Override
	public void updateClub(Integer idRunner, Integer idClub) {
		
		Runner runner = dao.findById(idRunner).orElseThrow(NotFoundException::new);
		runner.setClub(clubService.findById(idClub));
		dao.save(runner);
	}

}
