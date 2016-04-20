package com.bridge.pelatro.exception;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = 2110511265216242475L;
	private int statusCode;
    
    public ConnectionException() {
        super();
    }
    
    public ConnectionException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
}
