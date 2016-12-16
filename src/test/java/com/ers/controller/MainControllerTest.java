package com.ers.controller;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.beans.Reim;
import com.ers.exception.ValidateException;
import com.ers.web.DataChangeController;


public class MainControllerTest {

	// executes once before all tests
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
	public void  test() throws ValidateException{
		List<Reim> list = new ArrayList<Reim>();
		Reim reim1 = new Reim(1, 34.56, null, null, null, null, null, null, null);
		list.add(reim1);
		Reim reim2 = new Reim(2, 45.67, null, new Timestamp(System.currentTimeMillis()), null, null, null, null, null);
		list.add(reim2);
		assertEquals(reim1, DataChangeController.getInstance().validateReim(list, 1));
		assertNotEquals(reim2, DataChangeController.getInstance().validateReim(list, 1));
	}
	
	@Test(expected=ValidateException.class)
	public void abc() throws ValidateException {
		List<Reim> list = new ArrayList<Reim>();
		Reim reim1 = new Reim(1, 34.56, null, null, null, null, null, null, null);
		list.add(reim1);
		Reim reim2 = new Reim(2, 45.67, null, new Timestamp(System.currentTimeMillis()), null, null, null, null, null);
		list.add(reim2);
		DataChangeController.getInstance().validateReim(list, 2);
	}
}
