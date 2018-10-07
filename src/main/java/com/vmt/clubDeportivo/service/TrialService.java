package com.vmt.clubDeportivo.service;

import java.util.List;

import com.vmt.clubDeportivo.dto.ClubPointsDTO;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

public interface TrialService {

	/**
	 * Encontrar una prueba segun un id.
	 * @param idTrial El id de la prueba a buscar.
	 * @return La prueba encontrada.
	 */
	Trial findById(Integer idTrial);
	
	/**
	 * Crear una prueba nueva.
	 * @param trial La prueba a crear.
	 * @return La prueba creada.
	 */
	Trial create(Trial trial);
	
	/**
	 * Insertar un nuevo resultado para la prueba.
	 * @param idTrial El id de la prueba.
	 * @param result El resultado a insertar.
	 * @return El resultado insertado.
	 */
	Result insertResult(Integer idTrial, Result result);
	
	/**
	 * Obtener los resultados de una prueba sin ordenar.
	 * @param idTrial El id de la prueba.
	 * @return Lista de resultados sin ordenar.
	 */
	List<Result> getResults(Integer idTrial);
	
	/**
	 * Obtener la lista de todas las pruebas.
	 * @return Lista de todas las pruebas.
	 */
	List<Trial> findAll();
	
	/**
	 * Obtener la clasificacion de una prueba segun una categoria.
	 * @param idTrial El id de la prueba.
	 * @param category La categoria de la que se quiere obtener la clasificacion. Puede ser: 40, 30 o 20.
	 * @return Lista de resultados ordenados.
	 */
	List<Result> getMaster(Integer idTrial, Integer category);
	
	/**
	 * Obtener la clasificacion de los clubs por puntos segun una prueba.
	 * @param idTrial El id de la prueba.
	 * @param useCat Obtener la clasificacion dividiendo por categorias o no.
	 * @return Lista de clubs y sus puntos obtenidos.
	 */
	List<ClubPointsDTO> getClubClasi(Integer idTrial, Boolean useCat);
	
}
