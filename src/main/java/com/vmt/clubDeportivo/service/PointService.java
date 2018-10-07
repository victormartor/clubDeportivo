package com.vmt.clubDeportivo.service;

import java.util.List;

import com.vmt.clubDeportivo.model.Point;

public interface PointService {

	/**
	 * Encontrar todos los sistemas de puntuacion de la base de datos.
	 * @return Todas las puntuationes.
	 */
	List<Point> findAll();
	
}
