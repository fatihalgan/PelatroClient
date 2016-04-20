package com.bridge.pelatro.exception;

public class PelatroCommandException extends RuntimeException {

	private static final long serialVersionUID = 7721353441900323942L;

	public PelatroCommandException() {
		super();
	}
	
	public PelatroCommandException(String message) {
		super(message);
	}
}
