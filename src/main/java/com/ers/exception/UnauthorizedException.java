package com.ers.exception;

public class UnauthorizedException extends Exception{
	public UnauthorizedException(){
		super();
	}
	public UnauthorizedException(String message){
		super(message);
	}
}
