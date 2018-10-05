package com.vmt.clubDeportivo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
	
	@Override
	public Runner create(Runner runner) {
		return dao.save(runner);
	}

	@Override
	public Optional<Runner> findById(Integer id) {
		return dao.findById(id);
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

}
