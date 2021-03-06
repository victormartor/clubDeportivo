package com.vmt.clubDeportivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Result;

@Repository
public interface ResultDAO extends JpaRepository<Result, Integer>{

}
