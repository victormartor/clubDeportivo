package com.vmt.clubDeportivo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.ClubDAO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Club;

@Service
public class ClubServiceImpl implements ClubService{

	@Autowired
	ClubDAO dao;
	
	@Override
	public Club create(Club club) {
		return dao.save(club);
	}

	@Override
	public Club findById(Integer idClub) {
		return dao.findById(idClub).orElseThrow(NotFoundException::new);
	}
	
	

}
