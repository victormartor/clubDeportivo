package com.vmt.clubDeportivo.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vmt.clubDeportivo.dao.PointDAO;
import com.vmt.clubDeportivo.model.Point;

@RunWith(MockitoJUnitRunner.class)
public class PointServiceTest {

	@InjectMocks
	private PointServiceImpl pointServiceImpl;
	
	@Mock
	private PointDAO pointDAO;
	
	@Test
	public void findAllTest() {
		//GIVEN
		Point pointA = new Point();
		pointA.setId(1);
		pointA.setPosition(1);
		pointA.setPuntuation(12);
		
		Point pointB = new Point();
		pointB.setId(2);
		pointB.setPosition(2);
		pointB.setPuntuation(10);
		
		//WHEN
		when(pointDAO.findAll()).thenReturn(Arrays.asList(pointA, pointB));
		
		//THEN
		List<Point> returnedList = pointServiceImpl.findAll();
		
		assertNotNull(returnedList);
		assertEquals(pointA, returnedList.get(0));
		assertEquals(pointB, returnedList.get(1));
	}
	
}
