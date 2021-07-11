package com.manning.tdd;

public class MissingValueException extends RuntimeException{

	public MissingValueException(String message) {
		super(message);
	}
}
