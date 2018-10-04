package com.vmt.clubDeportivo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrialDTO {

	private Integer id;
	private String name;
	private String date;
	
}
