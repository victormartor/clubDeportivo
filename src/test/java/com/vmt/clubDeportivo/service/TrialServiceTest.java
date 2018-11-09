package com.vmt.clubDeportivo.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vmt.clubDeportivo.dao.TrialDAO;
import com.vmt.clubDeportivo.dto.ClubPointsDTO;
import com.vmt.clubDeportivo.error.NotFoundException;
import com.vmt.clubDeportivo.mapper.ClubPointsMapper;
import com.vmt.clubDeportivo.model.Club;
import com.vmt.clubDeportivo.model.Point;
import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Runner;
import com.vmt.clubDeportivo.model.Trial;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrialServiceTest {
	final private String TRIAL_NAME = "Nombre prueba";
	final private String RUNNER_NAME = "Nombre corredor";
	
	private Trial storedTrial;
	private List<Point> storedPoints;
	private Runner storedRunner;
	
	@InjectMocks
	private TrialServiceImpl trialServiceImpl;
	
	@Mock
	private TrialDAO trialDAO;
	
	@Mock
	private PointServiceImpl pointServiceImpl;
	
	@Mock
	private ResultServiceImpl resultServiceImpl;
	
	@Mock
	private ClubPointsMapper clubPointsMapper;
	
	@Before
	public void setUp() {
		storedTrial = new Trial();
		storedTrial.setId(1);
		storedTrial.setName(TRIAL_NAME);
		storedTrial.setDate(Date.valueOf("2018-11-09"));
		
		Point pointA = new Point();
		pointA.setId(1);
		pointA.setPosition(1);
		pointA.setPuntuation(12);
		
		Point pointB = new Point();
		pointB.setId(2);
		pointB.setPosition(2);
		pointB.setPuntuation(10);
		
		Point pointC = new Point();
		pointC.setId(3);
		pointC.setPosition(3);
		pointC.setPuntuation(8);
		
		storedPoints = Arrays.asList(pointA, pointB, pointC);
		storedTrial.setPoints(storedPoints);
		
		storedRunner = new Runner();
		storedRunner.setId(1);
		storedRunner.setName(RUNNER_NAME);
		storedRunner.setYear(1990);
	}
	
	@Test
	public void createTest() {
		//GIVEN
		Trial newTrial = new Trial();
		newTrial.setName(TRIAL_NAME);
		newTrial.setDate(Date.valueOf("2018-11-09"));
		
		Trial trialCreated = newTrial;
		trialCreated.setPoints(storedPoints);
		
		Trial trialSaved = trialCreated;
		trialSaved.setId(1);
		
		
		//WHEN
		when(trialDAO.save(newTrial)).thenReturn(storedTrial);
		when(pointServiceImpl.findAll()).thenReturn(storedPoints);
		when(trialDAO.save(trialCreated)).thenReturn(trialSaved);
		
		//THEN
		Trial returnedTrial = trialServiceImpl.create(newTrial);
		
		assertNotNull(returnedTrial);
		assertEquals(trialSaved.getId(), returnedTrial.getId());
		assertEquals(trialSaved.getName(), returnedTrial.getName());
		assertEquals(trialSaved.getDate(), returnedTrial.getDate());
		assertEquals(trialSaved.getPoints(), returnedTrial.getPoints());
	}
	
	@Test
	public void insertResultTest() {
		//GIVEN
		Result newResult = new Result();
		newResult.setSeconds(100f);
		newResult.setRunner(storedRunner);
		
		Result resultCreated = newResult;
		resultCreated.setTrial(storedTrial);
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		when(resultServiceImpl.create(storedTrial, newResult)).thenReturn(resultCreated);
		
		//THEN
		Result returnedResult = trialServiceImpl.insertResult(1, newResult);
		
		assertNotNull(returnedResult);
		assertEquals(resultCreated.getTrial(), returnedResult.getTrial());
	}
	
	@Test
	public void findByIdTest() {
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		
		//THEN
		Trial returnedTrial = trialServiceImpl.findById(1);
		
		assertNotNull(returnedTrial);
		assertEquals(storedTrial.getId(), returnedTrial.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenTrialDoesntExists() {
		//WHEN
		when(trialDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		trialServiceImpl.findById(1);
	}
	
	@Test
	public void getResultsTest() {
		//GIVEN
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(1);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1980);
		
		Result resultB = new Result();
		resultB.setSeconds(120f);
		resultB.setRunner(storedRunnerB);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB));
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		
		//THEN
		List<Result> results = trialServiceImpl.getResults(1);
		
		assertNotNull(results);
		assertEquals(resultA, results.get(0));
		assertEquals(resultB, results.get(1));
	}
	
	@Test
	public void findAllTest() {
		//WHEN
		when(trialDAO.findAll()).thenReturn(Arrays.asList(storedTrial));
		
		//THEN
		List<Trial> trials = trialServiceImpl.findAll();
		
		assertNotNull(trials);
		assertEquals(storedTrial, trials.get(0));
	}
	
	@Test
	public void getMaster40Test() {
		//GIVEN
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(2);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1975);
		
		Result resultB = new Result();
		resultB.setSeconds(150f);
		resultB.setRunner(storedRunnerB);
		
		Runner storedRunnerC = new Runner();
		storedRunnerC.setId(3);
		storedRunnerC.setName("Nombre corredor 3");
		storedRunnerC.setYear(1970);
		
		Result resultC = new Result();
		resultC.setSeconds(130f);
		resultC.setRunner(storedRunnerC);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB, resultC));
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		when(trialDAO.getMaster40(trialWithResults, 2018)).thenReturn(Arrays.asList(resultC, resultB));
		
		//THEN
		List<Result> results = trialServiceImpl.getMaster(1, 40);
		
		assertNotNull(results);
		assertEquals(resultC, results.get(0));
		assertEquals(resultB, results.get(1));
	}
	
	@Test
	public void getMaster30Test() {
		//GIVEN
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(2);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1985);
		
		Result resultB = new Result();
		resultB.setSeconds(150f);
		resultB.setRunner(storedRunnerB);
		
		Runner storedRunnerC = new Runner();
		storedRunnerC.setId(3);
		storedRunnerC.setName("Nombre corredor 2");
		storedRunnerC.setYear(1980);
		
		Result resultC = new Result();
		resultC.setSeconds(130f);
		resultC.setRunner(storedRunnerC);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB, resultC));
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		when(trialDAO.getMaster30(trialWithResults, 2018)).thenReturn(Arrays.asList(resultC, resultB));
		
		//THEN
		List<Result> results = trialServiceImpl.getMaster(1, 30);
		
		assertNotNull(results);
		assertEquals(resultC, results.get(0));
		assertEquals(resultB, results.get(1));
	}
	
	@Test
	public void getMaster20Test() {
		//GIVEN
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(2);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1995);
		
		Result resultB = new Result();
		resultB.setSeconds(150f);
		resultB.setRunner(storedRunnerB);
		
		Runner storedRunnerC = new Runner();
		storedRunnerC.setId(3);
		storedRunnerC.setName("Nombre corredor 3");
		storedRunnerC.setYear(1990);
		
		Result resultC = new Result();
		resultC.setSeconds(130f);
		resultC.setRunner(storedRunnerC);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB, resultC));
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		when(trialDAO.getMaster20(trialWithResults, 2018)).thenReturn(Arrays.asList(resultA, resultC, resultB));
		
		//THEN
		List<Result> results = trialServiceImpl.getMaster(1, 20);
		
		assertNotNull(results);
		assertEquals(resultA, results.get(0));
		assertEquals(resultC, results.get(1));
		assertEquals(resultB, results.get(2));
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenCategoryDoesntExists() {
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		
		//THEN
		trialServiceImpl.getMaster(1, 10);
	}
	
	@Test
	public void getClubClasiWithNoCat() {
		//GIVEN
		Club clubA = new Club();
		clubA.setId(1);
		clubA.setName("ClubA");
		
		Club clubB = new Club();
		clubB.setId(2);
		clubB.setName("ClubB");
		
		storedRunner.setClub(clubA);
		
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(2);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1995);
		storedRunnerB.setClub(clubA);
		
		Result resultB = new Result();
		resultB.setSeconds(150f);
		resultB.setRunner(storedRunnerB);
		
		Runner storedRunnerC = new Runner();
		storedRunnerC.setId(3);
		storedRunnerC.setName("Nombre corredor 3");
		storedRunnerC.setYear(1990);
		storedRunnerC.setClub(clubB);
		
		Result resultC = new Result();
		resultC.setSeconds(130f);
		resultC.setRunner(storedRunnerC);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB, resultC));
		
		ClubPointsDTO puntuationA = ClubPointsDTO.builder().name("ClubA").points(20).build();
		ClubPointsDTO puntuationB = ClubPointsDTO.builder().name("ClubB").points(10).build();
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		when(clubPointsMapper.mapToDTO(any(Map.class))).thenReturn(Arrays.asList(puntuationA, puntuationB));
		
		//THEN
		List<ClubPointsDTO> returnedPuntuationList = trialServiceImpl.getClubClasi(1, false);
		
		assertNotNull(returnedPuntuationList);
		assertEquals(puntuationA, returnedPuntuationList.get(0));
		assertEquals(puntuationB, returnedPuntuationList.get(1));
	}
	
	@Test
	public void getClubClasiWithCat() {
		//GIVEN
		Club clubA = new Club();
		clubA.setId(1);
		clubA.setName("ClubA");
		
		Club clubB = new Club();
		clubB.setId(2);
		clubB.setName("ClubB");
		
		storedRunner.setClub(clubA);
		
		Result resultA = new Result();
		resultA.setSeconds(100f);
		resultA.setRunner(storedRunner);
		
		Runner storedRunnerB = new Runner();
		storedRunnerB.setId(2);
		storedRunnerB.setName("Nombre corredor 2");
		storedRunnerB.setYear(1980);
		storedRunnerB.setClub(clubA);
		
		Result resultB = new Result();
		resultB.setSeconds(150f);
		resultB.setRunner(storedRunnerB);
		
		Runner storedRunnerC = new Runner();
		storedRunnerC.setId(3);
		storedRunnerC.setName("Nombre corredor 3");
		storedRunnerC.setYear(1990);
		storedRunnerC.setClub(clubB);
		
		Result resultC = new Result();
		resultC.setSeconds(130f);
		resultC.setRunner(storedRunnerC);
		
		Trial trialWithResults = storedTrial;
		trialWithResults.setResults(Arrays.asList(resultA, resultB, resultC));
		
		ClubPointsDTO puntuationA = ClubPointsDTO.builder().name("ClubA").points(24).build();
		ClubPointsDTO puntuationB = ClubPointsDTO.builder().name("ClubB").points(10).build();
		
		//WHEN
		when(trialDAO.findById(1)).thenReturn(Optional.ofNullable(trialWithResults));
		when(clubPointsMapper.mapToDTO(any(Map.class))).thenReturn(Arrays.asList(puntuationA, puntuationB));
		
		//THEN
		List<ClubPointsDTO> returnedPuntuationList = trialServiceImpl.getClubClasi(1, true);
		
		assertNotNull(returnedPuntuationList);
		assertEquals(puntuationA, returnedPuntuationList.get(0));
		assertEquals(puntuationB, returnedPuntuationList.get(1));
	}
	
}
