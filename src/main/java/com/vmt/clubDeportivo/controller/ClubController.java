package com.vmt.clubDeportivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmt.clubDeportivo.dto.ClubDTO;
import com.vmt.clubDeportivo.mapper.ClubMapper;
import com.vmt.clubDeportivo.model.Club;
import com.vmt.clubDeportivo.service.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {

	@Autowired
	ClubService clubService;
	
	@Autowired
	ClubMapper mapper;
	
	@PostMapping
	public ClubDTO create(@RequestBody ClubDTO clubToCreate) {
		final Club club = mapper.mapToModel(clubToCreate);
		return mapper.mapToDTO(clubService.create(club));
	}
	
}
