package com.vmt.clubDeportivo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vmt.clubDeportivo.dto.ClubPointsDTO;

@Component
public class ClubPointsMapperImpl implements ClubPointsMapper{

	@Override
	public List<ClubPointsDTO> mapToDTO(Map<String, Integer> clubPoints) {
		List<ClubPointsDTO> puntuationList = new ArrayList<>();
		
		clubPoints.forEach((k, v) -> puntuationList.add(ClubPointsDTO.builder().name(k).points(v).build()));
		
		return puntuationList;
	}

}
