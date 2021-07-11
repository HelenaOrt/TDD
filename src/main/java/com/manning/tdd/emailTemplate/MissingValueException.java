package com.manning.tdd.emailTemplate;

public class MissingValueException extends RuntimeException{

	public MissingValueException(String message) {
		super(message);
	}
}
