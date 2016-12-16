package com.ers.data;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.exception.ValidateException;


public class ReimDataTest {

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
	public void test(){
		
	}
}
