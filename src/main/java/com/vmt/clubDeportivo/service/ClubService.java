package com.vmt.clubDeportivo.service;

import java.util.List;
import java.util.Optional;

import com.vmt.clubDeportivo.model.Club;

public interface ClubService {

	Club create(Club club);
	
	Optional<Club> findById(Integer id);

	void update(Integer idClub, Club clubToUpdate);
	
	void delete(Integer idClub);
	
	List<Club> findAll();
	
}
