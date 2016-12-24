package com.ers.data;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.beans.Reim;

public class ReimFacadeTest {
	
	@BeforeClass
	public static void setUpBeforeClass() 
			throws Exception{
		System.out.println("BeforeClass");
	}	
	
	// executes once after all tests
	@AfterClass
	public static void tearDownAfterClass() 
			throws Exception{
		System.out.println("AfterClass");
	}
	
	// execute before each test
	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
	}
	
	// execute after each test
	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}
	
	@Test
	public void getAllReims() throws Exception{
		System.out.println("Test -- All Reims");
		List<Reim> list = DataFacade.getInstance().getAllReims();
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertNotEquals(null, reim);
		}
	}
	
	@Test
	public void getAllAcceptedReims() throws Exception{
		System.out.println("Test -- All Reims by Accepted");
		List<Reim> list = DataFacade.getInstance().getAllReimsByStatus("Accepted");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Accepted", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getAllDeniedReims() throws Exception{
		System.out.println("Test -- All Reims by Denied");
		List<Reim> list = DataFacade.getInstance().getAllReimsByStatus("Denied");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Denied", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getAllPendingReims() throws Exception{
		System.out.println("Test -- All Reims by Pending");
		List<Reim> list = DataFacade.getInstance().getAllReimsByStatus("Pending");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Pending", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getAllFoodReims() throws Exception{
		System.out.println("Test -- All Reims by Food");
		List<Reim> list = DataFacade.getInstance().getAllReimsByType("Food");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Food", reim.getType().getType());
		}
	}
	
	@Test
	public void getAllLodgingReims() throws Exception{
		System.out.println("Test -- All Reims by Lodging");
		List<Reim> list = DataFacade.getInstance().getAllReimsByType("Lodging");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Lodging", reim.getType().getType());
		}
	}
	
	@Test
	public void getAllTravelReims() throws Exception{
		System.out.println("Test -- All Reims by Travel");
		List<Reim> list = DataFacade.getInstance().getAllReimsByType("Travel");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Travel", reim.getType().getType());
		}
	}
	
	@Test
	public void getAllOtherReims() throws Exception{
		System.out.println("Test -- All Reims by Other");
		List<Reim> list = DataFacade.getInstance().getAllReimsByType("Other");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Other", reim.getType().getType());
		}
	}
	
	@Test
	public void getUserAcceptedReims() throws Exception{
		System.out.println("Test -- Reims for User by Accepted");
		List<Reim> list = DataFacade.getInstance().getUserReimsByStatus(1, "Accepted");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Accepted", reim.getStatus().getStatus());
		}
//		System.out.println(list);
	}
	
	@Test
	public void getUserDeniedReims() throws Exception{
		System.out.println("Test -- Reims for User by Denied");
		List<Reim> list = DataFacade.getInstance().getUserReimsByStatus(1, "Denied");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Denied", reim.getStatus().getStatus());
		}
//		System.out.println(list);
	}
	
	@Test
	public void getUserPendingReims() throws Exception{
		System.out.println("Test -- Reims for User by Pending");
		List<Reim> list = DataFacade.getInstance().getUserReimsByStatus(1, "Pending");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Pending", reim.getStatus().getStatus());
		}
//		System.out.println(list);
	}
	
	@Test
	public void getUserFoodReims() throws Exception{
		System.out.println("Test -- Reims for User by Food");
		List<Reim> list = DataFacade.getInstance().getUserReimsByType(1, "Food");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Type", reim.getType().getType());
		}
//		System.out.println(list);
	}
	
	@Test
	public void getUserTravelReims() throws Exception{
		System.out.println("Test -- Reims for User by Travel");
		List<Reim> list = DataFacade.getInstance().getUserReimsByType(1, "Travel");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Travel", reim.getType().getType());
		}
//		System.out.println(list);
	}
	
	@Test
	public void getUserLodgingReims() throws Exception{
		System.out.println("Test -- Reims for User by Lodging");
		List<Reim> list = DataFacade.getInstance().getUserReimsByType(1, "Lodging");
		assertTrue(list.size() > 0);
		for(Reim reim: list){
			assertEquals("Lodging", reim.getType().getType());
		}
//		System.out.println(list);
	}
}