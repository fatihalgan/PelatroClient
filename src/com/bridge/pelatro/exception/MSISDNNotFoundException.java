package com.bridge.pelatro.exception;

public class MSISDNNotFoundException extends PelatroCommandException {

	private static final long serialVersionUID = -3428742702817273024L;

	public MSISDNNotFoundException() {
		super();
	}
	
	public MSISDNNotFoundException(String message) {
		super(message);
	}
}
