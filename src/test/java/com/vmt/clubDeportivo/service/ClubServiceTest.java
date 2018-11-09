package com.vmt.clubDeportivo.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Optional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.vmt.clubDeportivo.dao.ClubDAO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Club;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceTest {
	
	final private String NAME_CLUB = "Nombre club";
	
	@InjectMocks
	private ClubServiceImpl clubServiceImpl;
	
	@Mock
	private ClubDAO clubDAO;
	
	@Test
	public void createTest() {
		//GIVEN
		Club storedClub = new Club();
		storedClub.setId(1);
		storedClub.setName(NAME_CLUB);
		
		//WHEN
		when(clubDAO.save(Mockito.any(Club.class))).thenReturn(storedClub);
		
		//THEN
		Club newClub = new Club();
		newClub.setName(NAME_CLUB);
		final Club returnedClub = clubServiceImpl.create(newClub);
		
		assertNotNull(returnedClub);
		assertEquals(storedClub.getId(), returnedClub.getId());
	}
	
	@Test
	public void findByIdTest() {
		//GIVEN
		Club storedClub = new Club();
		storedClub.setId(1);
		
		//WHEN
		when(clubDAO.findById(1)).thenReturn(Optional.ofNullable(storedClub));
		
		//THEN
		final Club returnedClub = clubServiceImpl.findById(1);
		
		assertNotNull(returnedClub);
		assertEquals(storedClub.getId(), returnedClub.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenClubDoesntExist() {
		//WHEN
		when(clubDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		clubServiceImpl.findById(1);
	}
}
