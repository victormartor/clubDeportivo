package com.vmt.clubDeportivo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubPointsDTO {

	private String name;
	private Integer points;
	
}
