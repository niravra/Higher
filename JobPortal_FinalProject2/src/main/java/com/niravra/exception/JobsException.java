package com.niravra.exception;

public class JobsException extends Exception {

	public JobsException(String message)
	{
		super("JobsException-"+message);
	}
	
	public JobsException(String message, Throwable cause)
	{
		super("JobsException-"+message,cause);
	}
	
}
