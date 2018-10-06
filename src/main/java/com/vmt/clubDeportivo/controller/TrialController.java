package com.vmt.clubDeportivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmt.clubDeportivo.dto.ClubPointsDTO;
import com.vmt.clubDeportivo.dto.ResultDTO;
import com.vmt.clubDeportivo.dto.TrialDTO;
import com.vmt.clubDeportivo.mapper.ResultMapper;
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
	@Autowired
	ResultMapper mapperResult;
	
	@GetMapping
	public List<TrialDTO> findAll(){
		return mapper.mapToDTO(trialService.findAll());
	}
	
	@PostMapping
	public TrialDTO create(@RequestBody TrialDTO trialToCreate) {
		final Trial trial = mapper.mapToModel(trialToCreate);
		return mapper.mapToDTO(trialService.create(trial));
	}
	
	@GetMapping("/{idTrial}/result")
	public List<ResultDTO> getResults(@PathVariable Integer idTrial){
		return mapperResult.mapToDTO(trialService.getResults(idTrial));
	}
	
	@PostMapping("/{idTrial}/result")
	public ResultDTO insertResult(@PathVariable Integer idTrial, @RequestBody ResultDTO result) {
		return mapperResult.mapToDTO(trialService.insertResult(idTrial, mapperResult.mapToModel(result)));
	}
	
	//Clasificaciones por categorias
	@GetMapping("/{idTrial}/master40")
	public List<ResultDTO> getMaster40(@PathVariable Integer idTrial){
		return mapperResult.mapToDTO(trialService.getMaster(idTrial, 40));
	}
	
	@GetMapping("/{idTrial}/master30")
	public List<ResultDTO> getMaster30(@PathVariable Integer idTrial){
		return mapperResult.mapToDTO(trialService.getMaster(idTrial, 30));
	}
	
	@GetMapping("/{idTrial}/master20")
	public List<ResultDTO> getMaster20(@PathVariable Integer idTrial){
		return mapperResult.mapToDTO(trialService.getMaster(idTrial, 20));
	}
	
	//Clasificacion de los clubs por puntos
	@GetMapping("/{idTrial}/clubClasi")
	public List<ClubPointsDTO> getClubClasi(@PathVariable Integer idTrial){
		return trialService.getClubClasi(idTrial);
	}
	
}
