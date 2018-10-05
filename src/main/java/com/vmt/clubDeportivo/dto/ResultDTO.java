package com.vmt.clubDeportivo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO {

	private Integer id;
	private Integer id_runner;
	private Float seconds;
	
}
