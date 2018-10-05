package com.vmt.clubDeportivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmt.clubDeportivo.dto.TrialDTO;
import com.vmt.clubDeportivo.mapper.TrialMapper;
import com.vmt.clubDeportivo.model.Trial;
import com.vmt.clubDeportivo.service.TrialService;

@RestController
@RequestMapping("/trial")
public class TrialController {

	@Autowired
	TrialService trialService; 
	
	@Autowired
	TrialMapper mapper;
	
	@PostMapping
	public TrialDTO create(@RequestBody TrialDTO trialToCreate) {
		final Trial trial = mapper.mapToModel(trialToCreate);
		return mapper.mapToDTO(trialService.create(trial));
	}
	
}
