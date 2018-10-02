package com.vmt.clubDeportivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Runner;

@Repository
public interface RunnerDAO extends JpaRepository<Runner, Integer>{

}
