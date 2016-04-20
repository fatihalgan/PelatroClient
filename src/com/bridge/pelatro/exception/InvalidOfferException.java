package com.bridge.pelatro.exception;

public class InvalidOfferException extends PelatroCommandException {

	private static final long serialVersionUID = 2958312262104790658L;

	public InvalidOfferException() {
		super();
	}
	
	public InvalidOfferException(String message) {
		super(message);
	}
}
