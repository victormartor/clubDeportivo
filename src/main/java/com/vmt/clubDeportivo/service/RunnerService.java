package com.vmt.clubDeportivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.vmt.clubDeportivo.model.Runner;

public interface RunnerService {
	
	Runner create(Runner runner);
	
	Optional<Runner> findById(Integer id);

	void update(Integer idRunner, Runner runnerToUpdate);
	
	void delete(Integer idRunner);
	
	List<Runner> findAll(Pageable pagination, String name);
	
	void updateClub(Integer idRunner, Integer idClub);
}
