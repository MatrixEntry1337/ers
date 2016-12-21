package com.ers.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;


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
		Reim reim1 = new Reim(1, 23.45, null, null, "Reim case #1", null, null, new Status(1, "Approved"), new Type(1, "Type A"));
		Reim reim2 = new Reim(2, 45.45, null, null, "Reim case #2", null, null, new Status(1, "Approved"), new Type(2, "Type B"));
		Reim reim3 = new Reim(3, 35.45, null, null, "Reim case #3", null, null, new Status(2, "Unapproved"), new Type(3, "Type C"));
		Reim reim4 = new Reim(4, 25.45, null, null, "Reim case #4", null, null, new Status(3, "Pending"), new Type(4, "Type D"));
		List<Reim> list = new ArrayList<Reim>();
		list.add(reim4);
		list.add(reim2);
		list.add(reim3);
		list.add(reim1);
		
		for(Reim each: list){
			System.out.println(each);
		}
		
		// status compare
		Reim.ReimStatusNatural statusNatural = new Reim.ReimStatusNatural();
		Collections.sort(list, statusNatural);
		System.out.println("Status Sort: ");
		for(Reim each: list){
			System.out.println(each);
		}
		
		Reim.ReimStatusReverse statusReverse = new Reim.ReimStatusReverse();
		Collections.sort(list, statusReverse);
		System.out.println("Status Sort Reverse: " );
		for(Reim each: list){
			System.out.println(each);
		}
		
		
		// type compare
		Reim.ReimTypeNatural typeComparator = new Reim.ReimTypeNatural();
		Collections.sort(list, typeComparator);
		System.out.println("Type Sort: ");
		for(Reim each: list){
			System.out.println(each);
		}
		
		Reim.ReimTypeReverse typeReverse = new Reim.ReimTypeReverse();
		Collections.sort(list, typeReverse);
		System.out.println("Type Sort Reverse");
		for(Reim each: list){
			System.out.println(each);
		}
	}
}
