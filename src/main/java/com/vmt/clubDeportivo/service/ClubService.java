package com.vmt.clubDeportivo.service;

import java.util.Optional;

import com.vmt.clubDeportivo.model.Club;

public interface ClubService {

	Club create(Club club);
	
	Club findById(Integer idClub);
	
}
