package com.vmt.clubDeportivo.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vmt.clubDeportivo.dto.TrialDTO;
import com.vmt.clubDeportivo.model.Trial;

@Component
public class TrialMapperImpl implements TrialMapper{

	@Override
	public Trial mapToModel(TrialDTO dto) {
		Trial trial = new Trial();
		trial.setId(dto.getId());
		trial.setName(dto.getName());
		
		String date = dto.getDate().replace('/', '-');
		trial.setDate(Date.valueOf(date));
		
		return trial;
	}

	@Override
	public TrialDTO mapToDTO(Trial model) {
		return TrialDTO.builder().id(model.getId()).name(model.getName())
				.date(model.getDate().toString().substring(0, 10).replace('-', '/')).build();
	}

	@Override
	public List<TrialDTO> mapToDTO(List<Trial> models) {
		List<TrialDTO> dtos = new ArrayList<>();
		for(Trial t : models) {
			dtos.add(this.mapToDTO(t));
		}
		return dtos;
	}

}
