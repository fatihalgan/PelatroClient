package com.bridge.pelatro.command;

public enum PelatroTokens {

	msisdn("msisdn"),
	fetchDepth("fetch_depth"),
	offerId("offer_id");
	
	private final String text;
    
    private PelatroTokens(String text) {
        this.text = text;
    }
    
    public String toString() {
        return text;
    }
}
