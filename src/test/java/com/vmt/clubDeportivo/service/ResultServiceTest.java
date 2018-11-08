package com.vmt.clubDeportivo.service;

import static org.mockito.Mockito.*;

import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vmt.clubDeportivo.dao.ResultDAO;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {
	
	@InjectMocks
	private ResultServiceImpl resultServiceImpl;
	
	@Mock
	private ResultDAO resultDAO;
	
	@Test
	public void createTest() {
		//GIVEN
		Trial storedTrial = new Trial();
		storedTrial.setId(1);
		storedTrial.setName("Nombre prueba");
		storedTrial.setDate(new Date());
		
		Result storedResult = new Result();
		storedResult.setId(1);
		storedResult.setSeconds(120f);
		
		//WHEN
		when(resultDAO.save(any(Result.class))).thenReturn(storedResult);
		
		//THEN
		Result newResult = new Result();
		newResult.setSeconds(120f);
		Result returnedResult = resultServiceImpl.create(storedTrial, newResult);
		
		assertNotNull(returnedResult);
		assertEquals(storedResult.getId(), returnedResult.getId());
		assertEquals(storedTrial, returnedResult.getTrial());
	}

}
