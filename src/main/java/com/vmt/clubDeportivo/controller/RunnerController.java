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
import com.vmt.clubDeportivo.dto.RunnerDTO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.mapper.ClubMapper;
import com.vmt.clubDeportivo.mapper.RunnerMapper;
import com.vmt.clubDeportivo.model.Runner;
import com.vmt.clubDeportivo.service.RunnerService;

@RestController
@RequestMapping(value = "/runner")
public class RunnerController {

	@Autowired
	RunnerService runnerService;
	
	@Autowired
	RunnerMapper mapper;
	@Autowired
	ClubMapper mapperClub;
	
	@GetMapping
	public List<RunnerDTO> findAll(
			@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size,
			@RequestParam(defaultValue = "", value = "name", required = false) String name){
		
		return mapper.mapToDTO(runnerService.findAll(PageRequest.of(page, size), name));
	}
	
	@GetMapping("/{idRunner}")
	public RunnerDTO findById(@PathVariable Integer idRunner) {
		return mapper.mapToDTO(runnerService.findById(idRunner).orElseThrow(NotFoundException::new));
	}
	
	@PostMapping
	public RunnerDTO create(@RequestBody RunnerDTO runnerToCreate) {
		final Runner runner = mapper.mapToModel(runnerToCreate);
		return mapper.mapToDTO(runnerService.create(runner));
	}
	
	@PutMapping("/{idRunner}")
	public void update(@RequestBody RunnerDTO runnerToUpdate, @PathVariable Integer idRunner) {
		final Runner runner = mapper.mapToModel(runnerToUpdate);
		runner.setId(idRunner);
		runnerService.update(idRunner, runner);
	}
	
	@DeleteMapping("/{idRunner}")
	public void delete(@PathVariable Integer idRunner) {
		runnerService.delete(idRunner);
	}
	
	@PutMapping("/{idRunner}/club/{idClub}")
	public void updateClub(@PathVariable Integer idRunner, @PathVariable Integer idClub) {
		runnerService.updateClub(idRunner, idClub);
	}
	
	@GetMapping("/{idRunner}/club")
	public ClubDTO getClubByRunner(@PathVariable Integer idRunner) {
		final Runner runner = runnerService.findById(idRunner).orElseThrow(NotFoundException::new);
		
		if(runner.getClub() == null) throw new NotFoundException(); 
			
		return mapperClub.mapToDTO(runner.getClub());
		
	}
	
}
