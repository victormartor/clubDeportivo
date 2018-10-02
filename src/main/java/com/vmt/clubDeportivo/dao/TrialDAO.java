package com.vmt.clubDeportivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Trial;

@Repository
public interface TrialDAO extends JpaRepository<Trial, Integer>{

}
