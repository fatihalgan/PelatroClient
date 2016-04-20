package com.bridge.pelatro.command;

public class CommandFactory {

	private String targetUrl;
	private String pelatroHost;
	
	public CommandFactory(String targetUrl, String pelatroHost) {
		this.targetUrl = targetUrl;
		this.pelatroHost = pelatroHost;
	}
	
	public RetrieveOffersCommand getRetrieveOffersCommand(String msisdn, int fetchDepth) {
		RetrieveOffersCommand cmd = new RetrieveOffersCommand(targetUrl, pelatroHost, msisdn, fetchDepth);
		return cmd;
	}
	
	public RetrieveOptInOffersCommand getRetrieveOptInOffersCommand(String msisdn) {
		RetrieveOptInOffersCommand cmd = new RetrieveOptInOffersCommand(targetUrl, pelatroHost, msisdn);
		return cmd;
	}
}
