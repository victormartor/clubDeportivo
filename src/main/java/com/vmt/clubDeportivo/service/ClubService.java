package com.vmt.clubDeportivo.service;

import com.vmt.clubDeportivo.model.Club;

public interface ClubService {

	/**
	 * Crea un club nuevo.
	 * @param club El club que se va a crear.
	 * @return El club creado.
	 */
	Club create(Club club);
	
	/**
	 * Encuentra un club en la base de datos segun el id.
	 * @param idClub El id del club a buscar.
	 * @return El club encontrado.
	 */
	Club findById(Integer idClub);
	
}
