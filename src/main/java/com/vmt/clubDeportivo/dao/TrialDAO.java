package com.vmt.clubDeportivo.dao;

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
			+ "WHERE res.trial = ?1 AND run.year<(?2 - 40) "
			+ "ORDER BY res.seconds")
	List<Result> getMaster40(Trial trial, Integer trialYear);
	
	@Query("SELECT res "
			+ "FROM Result res "
			+ "JOIN res.runner run "
			+ "GROUP BY run.year "
			+ "HAVING run.year BETWEEN (?2 - 40) AND (?2 - 30) AND res.trial = ?1 "
			+ "ORDER BY res.seconds")
	List<Result> getMaster30(Trial trial, Integer trialYear);
	
	@Query("SELECT res "
			+ "FROM Result res "
			+ "JOIN res.runner run "
			+ "GROUP BY run.year "
			+ "HAVING run.year BETWEEN (?2 - 29) AND (?2 - 20) AND res.trial = ?1 "
			+ "ORDER BY res.seconds")
	List<Result> getMaster20(Trial trial, Integer trialYear);
}
