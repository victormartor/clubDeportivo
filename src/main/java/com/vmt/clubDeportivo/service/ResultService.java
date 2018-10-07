package com.vmt.clubDeportivo.service;

import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

public interface ResultService {

	/**
	 * Crea un nuevo resultado para una prueba.
	 * @param trial La prueba a la que se le asignará el resultado.
	 * @param result El resultado.
	 * @return El resultado creado.
	 */
	Result create(Trial trial, Result result);
	
}
