package com.ers.service;

public class BusinessFactory {
	public static BusinessDelegateInterface getDelegate(){
		 return BusinessDelegate.getInstance();
	}
}
