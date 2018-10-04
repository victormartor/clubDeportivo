package com.vmt.clubDeportivo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vmt.clubDeportivo.dto.ClubDTO;
import com.vmt.clubDeportivo.model.Club;

@Component
public class ClubMapperImpl implements ClubMapper{

	@Override
	public Club mapToModel(ClubDTO dto) {
		Club club = new Club();
		//club.setIdClub(dto.getIdClub());
		club.setName(dto.getName());
		return club;
	}

	@Override
	public ClubDTO mapToDTO(Club model) {
		return ClubDTO.builder().idClub(model.getId()).name(model.getName()).build();
	}

	@Override
	public List<ClubDTO> mapToDTO(List<Club> models) {
		List<ClubDTO> dtos = new ArrayList<>();
		for(Club c : models) {
			dtos.add(this.mapToDTO(c));
		}
		return dtos;
	}

}
