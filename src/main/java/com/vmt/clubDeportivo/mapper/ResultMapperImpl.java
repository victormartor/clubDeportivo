package com.vmt.clubDeportivo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmt.clubDeportivo.dto.ResultDTO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.service.RunnerService;

@Component
public class ResultMapperImpl implements ResultMapper{

	@Autowired
	RunnerService runnerService;
	
	@Override
	public Result mapToModel(ResultDTO dto) {
		Result result = new Result();
		result.setId(dto.getId());
		result.setRunner(runnerService.findById(dto.getId_runner()).orElseThrow(NotFoundException::new));
		result.setSeconds(dto.getSeconds());
		return result;
	}

	@Override
	public ResultDTO mapToDTO(Result model) {
		return ResultDTO.builder().id(model.getId()).id_runner(model.getRunner().getId())
				.seconds(model.getSeconds()).build();
	}

	@Override
	public List<ResultDTO> mapToDTO(List<Result> models) {
		List<ResultDTO> dtos = new ArrayList<>();
		for(Result r : models) {
			dtos.add(this.mapToDTO(r));
		}
		return dtos;
	}

}
