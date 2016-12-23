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
		System.out.println("All Reims Test");
		List<Reim> list = DataFacade.getInstance().getAllReims();
		for(Reim reim: list){
			assertNotEquals(null, reim);
		}
	}
	
	@Test
	public void getAllAcceptedReims() throws Exception{
		System.out.println("All Accepted Reims Test");
		List<Reim> list = DataFacade.getInstance().getAllAcceptedReims();
		for(Reim reim: list){
			assertEquals("Accepted", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getAllDeniedReims() throws Exception{
		System.out.println("All Denied Reims Test");
		List<Reim> list = DataFacade.getInstance().getAllDeniedReims();
		for(Reim reim: list){
			assertEquals("Denied", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getAllPendingReims() throws Exception{
		System.out.println("All Pending Reims Test");
		List<Reim> list = DataFacade.getInstance().getAllPendingReims();
		for(Reim reim: list){
			assertEquals("Pending", reim.getStatus().getStatus());
		}
	}
	
	@Test
	public void getUserAcceptedReims() throws Exception{
		System.out.println("User Accepted Reims Test");
		List<Reim> list = DataFacade.getInstance().getUserAcceptedReims(3);
		for(Reim reim: list){
			assertEquals("Accepted", reim.getStatus().getStatus());
		}
		System.out.println(list);
	}
	
	@Test
	public void getUserDeniedReims() throws Exception{
		System.out.println("User Denied Reims Test");
		List<Reim> list = DataFacade.getInstance().getUserDeniedReims(3);
		for(Reim reim: list){
			assertEquals("Denied", reim.getStatus().getStatus());
		}
		System.out.println(list);
	}
	
	@Test
	public void getUserPendingReims() throws Exception{
		System.out.println("User Pending Reims Test");
		List<Reim> list = DataFacade.getInstance().getUserPendingReims(3);
		for(Reim reim: list){
			assertEquals("Pending", reim.getStatus().getStatus());
		}
		System.out.println(list);
	}
	
	
	
	
}