package com.vmt.clubDeportivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmt.clubDeportivo.model.Runner;
import com.vmt.clubDeportivo.service.RunnerService;

@RestController
@RequestMapping(value = "/runner")
public class RunnerController {

	@Autowired
	RunnerService runnerService;
	
	@GetMapping
	public List<Runner> findAll(){
		return runnerService.findAll();
	}
	
}
