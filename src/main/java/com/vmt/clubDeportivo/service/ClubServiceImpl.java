package com.vmt.clubDeportivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmt.clubDeportivo.dao.ClubDAO;
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
	public Optional<Club> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer idClub, Club clubToUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer idClub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Club> findAll() {
		return dao.findAll();
	}

}
