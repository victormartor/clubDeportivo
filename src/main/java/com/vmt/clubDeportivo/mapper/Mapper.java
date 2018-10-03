package com.vmt.clubDeportivo.mapper;

import java.util.List;

public interface Mapper<M, DTO> {

	M mapToModel(DTO dto);

	DTO mapToDTO(M model);

	List<DTO> mapToDTO(List<M> models);

}
