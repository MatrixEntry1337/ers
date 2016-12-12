package com.ers.business;

public class BusinessFactory {
	public static BusinessDelegateInterface getDelegate(){
		 return BusinessDelegate.getInstance();
	}
}
