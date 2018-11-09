package com.vmt.clubDeportivo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.vmt.clubDeportivo.dao.RunnerDAO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.model.Club;
import com.vmt.clubDeportivo.model.Runner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RunnerServiceTest {
	
	final private String NAME_RUNNER = "Nombre corredor";
	final private Integer YEAR_RUNNER = 1990;
	
	private Runner storageRunner;
	
	@InjectMocks
	private RunnerServiceImpl runnerServiceImpl;
	
	@Mock
	private RunnerDAO runnerDAO;
	
	@Mock
	private ClubServiceImpl clubService;
	
	@Before
	public void setUp() {
		storageRunner = new Runner();
		storageRunner.setId(1);
		storageRunner.setName(NAME_RUNNER);
		storageRunner.setYear(YEAR_RUNNER);
	}
	
	@Test
	public void createTest() {
		//GIVEN
		Runner newRunner = new Runner();
		newRunner.setName(NAME_RUNNER);
		newRunner.setYear(YEAR_RUNNER);
		
		//WHEN
		when(runnerDAO.save(any(Runner.class))).thenReturn(storageRunner);
		
		//THEN
		Runner returnedRunner = runnerServiceImpl.create(newRunner);
		
		assertNotNull(returnedRunner);
		assertEquals(storageRunner.getId(), returnedRunner.getId());		
	}
	
	@Test
	public void findByIdTest() {
		//WHEN
		when(runnerDAO.findById(1)).thenReturn(Optional.ofNullable(storageRunner));
		
		//THEN
		Runner returnedRunner = runnerServiceImpl.findById(1);
		
		assertNotNull(returnedRunner);
		assertEquals(storageRunner.getId(), returnedRunner.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenRunnerDoesntExist() {
		//WHEN
		when(runnerDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		runnerServiceImpl.findById(1);
	}
	
	@Test
	public void updateTest() {
		//GIVEN
		Runner newRunner = new Runner();
		newRunner.setId(1);
		newRunner.setName("Nuevo nombre");
		newRunner.setYear(1995);
		
		//WHEN
		when(runnerDAO.findById(1)).thenReturn(Optional.ofNullable(storageRunner));
		when(runnerDAO.save(any(Runner.class))).thenReturn(newRunner);
		
		//THEN
		runnerServiceImpl.update(1, newRunner);
		
		verify(runnerDAO).findById(1);
		verify(runnerDAO).save(newRunner);
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenRunnerDoesntExistOnUpdate() {
		//WHEN
		when(runnerDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		runnerServiceImpl.update(1, storageRunner);
	}
	
	@Test
	public void deleteTest() {
		//WHEN
		when(runnerDAO.findById(1)).thenReturn(Optional.ofNullable(storageRunner));
		
		runnerServiceImpl.delete(1);
		
		verify(runnerDAO).delete(storageRunner);
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenRunnerDoesntExistOnDelete() {
		//WHEN
		when(runnerDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		runnerServiceImpl.delete(1);
	}
	
	@Test
	public void findAllTest() {
		//GIVEN
		Runner runnerA = new Runner();
		runnerA.setId(1);
		runnerA.setName(NAME_RUNNER);
		runnerA.setYear(YEAR_RUNNER);
		
		Runner runnerB = new Runner();
		runnerB.setId(2);
		runnerB.setName(NAME_RUNNER);
		runnerB.setYear(YEAR_RUNNER);
		
		Pageable pagination = PageRequest.of(0, 5);
		
		//WHEN
		when(runnerDAO.findByNameContaining("", pagination)).thenReturn(new PageImpl<Runner>(Arrays.asList(runnerA, runnerB)));
		
		//THEN
		List<Runner> returnedList = runnerServiceImpl.findAll(pagination, "");
		
		assertNotNull(returnedList);
		assertEquals(runnerA, returnedList.get(0));
		assertEquals(runnerB, returnedList.get(1));
	}
	
	@Test
	public void updateClubTest() {
		//GIVEN
		Runner newRunner = new Runner();
		newRunner.setId(1);
		newRunner.setName(NAME_RUNNER);
		newRunner.setYear(YEAR_RUNNER);
		
		Club storageClub = new Club();
		storageClub.setId(1);
		storageClub.setName("Nombre club");
		
		//WHEN
		when(runnerDAO.findById(1)).thenReturn(Optional.ofNullable(newRunner));
		when(clubService.findById(1)).thenReturn(storageClub);
		
		//THEN
		runnerServiceImpl.updateClub(1, 1);
		newRunner.setClub(storageClub);
		
		verify(runnerDAO).findById(1);
		verify(runnerDAO).save(newRunner);
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenRunnerDoesntExistOnUpdateClub() {
		//WHEN
		when(runnerDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		runnerServiceImpl.updateClub(1, 1);
	}

}
