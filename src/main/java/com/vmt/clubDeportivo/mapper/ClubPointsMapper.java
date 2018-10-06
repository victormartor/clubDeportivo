package com.vmt.clubDeportivo.mapper;

import java.util.List;
import java.util.Map;

import com.vmt.clubDeportivo.dto.ClubPointsDTO;

public interface ClubPointsMapper {

	List<ClubPointsDTO> mapToDTO(Map<String, Integer> clubPoints);
	
}
