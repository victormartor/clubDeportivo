package com.vmt.clubDeportivo.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

@Repository
public interface TrialDAO extends JpaRepository<Trial, Integer>{

	@Query("SELECT res "
			+ "FROM Result res "
			+ "JOIN res.runner run "
			+ "WHERE run.year<1978 "
			+ "ORDER BY res.seconds")
	List<Result> getMaster40();
	
	@Query("SELECT res "
			+ "FROM Result res "
			+ "JOIN res.runner run "
			+ "GROUP BY run.year "
			+ "HAVING run.year BETWEEN 1978 AND 1988 "
			+ "ORDER BY res.seconds")
	List<Result> getMaster30();
	
	@Query("SELECT res "
			+ "FROM Result res "
			+ "JOIN res.runner run "
			+ "GROUP BY run.year "
			+ "HAVING run.year BETWEEN 1989 AND 1998 "
			+ "ORDER BY res.seconds")
	List<Result> getMaster20();
}
