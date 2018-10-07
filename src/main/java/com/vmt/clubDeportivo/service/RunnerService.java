package com.vmt.clubDeportivo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vmt.clubDeportivo.model.Runner;

public interface RunnerService {
	
	/**
	 * Crea un nuevo corredor.
	 * @param runner El corredor a crear.
	 * @return El corredor creado.
	 */
	Runner create(Runner runner);
	
	/**
	 * Encontrar un corredor segun un id.
	 * @param id El id del corredor a buscar.
	 * @return El corredor encontrado.
	 */
	Runner findById(Integer id);

	/**
	 * Actualizar los datos de un corredor.
	 * @param idRunner El id del corredor a actualizar.
	 * @param runnerToUpdate Los datos a actualizar del corredor.
	 */
	void update(Integer idRunner, Runner runnerToUpdate);
	
	/**
	 * Eliminar un corredor.
	 * @param idRunner El id del corredor a eliminar.
	 */
	void delete(Integer idRunner);
	
	/**
	 * Encontrar todos los corredores.
	 * @param pagination La forma de paginacion de los corredores.
	 * @param name Buscar a los corredores por nombre. Puede ser vacio pero no nulo.
	 * @return La lista de los corredores encontrados.
	 */
	List<Runner> findAll(Pageable pagination, String name);
	
	/**
	 * Actualizar el club al que pertenece un corredor.
	 * @param idRunner EL id del corredor.
	 * @param idClub El id del club.
	 */
	void updateClub(Integer idRunner, Integer idClub);
}
