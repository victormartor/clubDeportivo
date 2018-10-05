package com.vmt.clubDeportivo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vmt.clubDeportivo.dto.RunnerDTO;
import com.vmt.clubDeportivo.model.Runner;

@Component
public class RunnerMapperImpl implements RunnerMapper{

	@Override
	public Runner mapToModel(RunnerDTO dto) {
		Runner runner = new Runner();
		runner.setId(dto.getId());
		runner.setName(dto.getName());
		runner.setYear(dto.getYear());
		
		return runner;
	}

	@Override
	public RunnerDTO mapToDTO(Runner model) {
		return RunnerDTO.builder().id(model.getId()).name(model.getName()).year(model.getYear()).build();
	}

	@Override
	public List<RunnerDTO> mapToDTO(List<Runner> models) {
		List<RunnerDTO> dtos = new ArrayList<>();
		for(Runner r : models) {
			dtos.add(this.mapToDTO(r));
		}
		return dtos;
	}

}
