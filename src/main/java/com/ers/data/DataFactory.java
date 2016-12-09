package com.ers.data;

public class DataFactory {
	public static DataFacadeInterface getFacade(){
		return DataFacade.getInstance();
	}
}
