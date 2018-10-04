package com.vmt.clubDeportivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmt.clubDeportivo.dto.ClubDTO;
import com.vmt.clubDeportivo.error.NotFoundException;
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
	
	@GetMapping
	public List<ClubDTO> findAll(
			@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size,
			@RequestParam(defaultValue = "", value = "name", required = false) String name){
		return mapper.mapToDTO(clubService.findAll(PageRequest.of(page, size), name));
	}
	
	@GetMapping("/{idClub}")
	public ClubDTO findById(@PathVariable Integer idClub) {	
		return mapper.mapToDTO(clubService.findById(idClub)
				.orElseThrow(() -> new NotFoundException()));
	}
	
	@PostMapping
	public ClubDTO create(@RequestBody ClubDTO clubToCreate) {
		Club club = mapper.mapToModel(clubToCreate);
		return mapper.mapToDTO(clubService.create(club));
	}
	
	@PutMapping("/{idClub}")
	public void update(@RequestBody ClubDTO clubToUpdate, @PathVariable Integer idClub) {
		Club club = mapper.mapToModel(clubToUpdate);
		//club.setIdClub(idClub);
		clubService.update(idClub, club);
	}
	
	@DeleteMapping("/{idClub}")
	public void delete(@PathVariable Integer idClub) {
		clubService.delete(idClub);
	}
	
}
