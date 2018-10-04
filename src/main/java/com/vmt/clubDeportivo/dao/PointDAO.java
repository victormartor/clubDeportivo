package com.vmt.clubDeportivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmt.clubDeportivo.model.Point;

@Repository
public interface PointDAO extends JpaRepository<Point, Integer>{

}
