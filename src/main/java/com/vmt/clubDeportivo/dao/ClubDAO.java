package com.vmt.clubDeportivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Club;

@Repository
public interface ClubDAO extends JpaRepository<Club, Integer>{

}
