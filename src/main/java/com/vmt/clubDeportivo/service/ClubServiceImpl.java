package com.vmt.clubDeportivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public Optional<Club> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Integer idClub, Club clubToUpdate) {
		dao.saveAndFlush(clubToUpdate);
	}

	@Override
	public void delete(Integer idClub) {
		dao.delete(dao.findById(idClub).orElseThrow(() -> new NotFoundException()));
	}

	@Override
	public List<Club> findAll(Pageable pagination, String name) {
		return dao.findByNameContaining(name, pagination).getContent();
	}

}
