package EmployeePay.GlobalTechCorp;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalTechCorpApplicationTests
{
	@MockBean
	EmployeedetailsRepository repo;

	@Autowired
	EmployeeDetailsService service;

	@Test
	public void testingAll()
	{
		EmployeeDetails emp1=new EmployeeDetails(1,"Manoj","Maddy434","Password1","Java Stack developer",2,2.8,null);
		EmployeeDetails emp2=new EmployeeDetails(2,"Tamil","Tamill420","Password1","Pythom Stack developer",1,1.8,null);

		when(repo.findAll()).thenReturn(Stream.of(emp1,emp2).collect(Collectors.toList()));

		assertSame("Tamil",service.viewall().get(1).getEmpName());
		assertNotSame(3,service.viewall().size());
	}

//	@Test
//	public void testingDelete()
//	{
//		EmployeeDetails emp1=new EmployeeDetails(1,"Manoj","Maddy434","Password1","Java Stack developer",2,2.8,null);
//		repo.delete(emp1);
//		verify(repo,times(1)).delete(emp1);
//	}

	@Test
	public void testingcreate()
	{
		EmployeeDetails emp1=new EmployeeDetails(1,"Manoj","Maddy434","Password123","Java Stack developer",2,2.8,null);
		EmployeeDetails emp2=new EmployeeDetails(2,"Tamil","Tamill420","Password112","Pythom Stack developer",1,1.8,null);

		when(repo.save(emp1)).thenReturn(emp1);
		when(repo.save(emp2)).thenReturn(emp2);
		assertTrue(service.create(emp1).getEmpPassword().equals("Pas2sword1233443"));
	}

	@Test
	public void testReading()
	{
		Optional<EmployeeDetails> emp1=Optional.of(new EmployeeDetails(1001,"Manoj","Maddy434","Password1","Java Stack developer",2,2.8,null));
		Optional<EmployeeDetails> emp2=Optional.of(new EmployeeDetails(2001,"Tamil","Tamill420","Password1","Pythom Stack developer",1,1.8,null));

		when(repo.findById(1001)).thenReturn(emp1);
		when(repo.findById(2001)).thenReturn(emp2);

//		assertEquals(emp2,service.readone(2001));

		assertTrue(service.readone(1001).get().getEmpDesignation().equals(emp1.get().getEmpDesignation()));
	}

//	@Test
//	public void testingAllbyNames()
//	{
//		String name1="Manoj";
//		String name2="Tamil";
//		String name3="Surenthiran";
//		String name4="Pavithra";
//
//		List<String> check1=Stream.of(name1,name4,name2).collect(Collectors.toList());
//		List<String> check2=Stream.of(name3,name2,name4).collect(Collectors.toList());
//
//
//		when(repo.findAllByEmpName("Manoj")).thenReturn((EmployeeDetails) Stream.of(name1,name4,name2).collect(Collectors.toList()));
//
//		Assertions.assertLinesMatch(check2, (List<String>) service.readbyname("Manoj"));
//	}

}
