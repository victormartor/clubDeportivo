package com.vmt.clubDeportivo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
		
		assertEquals("Nuevo nombre", newRunner.getName());
		assertEquals(new Integer(1995), newRunner.getYear());
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
		when(runnerDAO.findByNameContaining("", pagination)).thenReturn(new Page<Runner>() {
			@Override
			public List<Runner> getContent() {
				// TODO Auto-generated method stub
				return Arrays.asList(runnerA, runnerB);
			}

			@Override
			public int getNumber() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getNumberOfElements() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean hasContent() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Sort getSort() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isFirst() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isLast() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Pageable nextPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Pageable previousPageable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<Runner> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getTotalPages() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getTotalElements() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <U> Page<U> map(Function<? super Runner, ? extends U> converter) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
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
	}
	
	@Test(expected = NotFoundException.class)
	public void throwNotFoundWhenRunnerDoesntExistOnUpdateClub() {
		//WHEN
		when(runnerDAO.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		//THEN
		runnerServiceImpl.updateClub(1, 1);
	}

}
