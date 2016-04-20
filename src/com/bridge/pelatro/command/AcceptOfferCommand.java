package com.bridge.pelatro.command;

import org.apache.log4j.Logger;

import com.bridge.pelatro.serializer.PelatroRequestParam;

public class AcceptOfferCommand extends AbstractPelatroProvisionCommand {

	private static Logger logger = Logger.getLogger(AcceptOfferCommand.class);
	
	private String offerId;
	private String msisdn;
	
	AcceptOfferCommand(String targetUrl, String pelatroHost, String offerId, String msisdn) {
		super(targetUrl, pelatroHost);
		this.msisdn = msisdn;
		this.offerId = offerId;
	}
	
	@Override
	public void prepareRequest() {
		getRequest().setMethod(PelatroRequestMethod.pelatroProvisionMethod.toString());
		getRequest().addParam(new PelatroRequestParam(PelatroTokens.msisdn.toString(), msisdn));
		getRequest().addParam(new PelatroRequestParam(PelatroTokens.offerId.toString(), offerId));
	}
	
}
