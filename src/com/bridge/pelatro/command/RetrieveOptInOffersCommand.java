package com.bridge.pelatro.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bridge.pelatro.exception.PelatroCommandException;
import com.bridge.pelatro.model.OfferBouquet;
import com.bridge.pelatro.serializer.PelatroRequestParam;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RetrieveOptInOffersCommand extends AbstractPelatroCommand {

	private static Logger logger = Logger.getLogger(RetrieveOptInOffersCommand.class);
	
	private String msisdn;
	private OfferBouquet offerBouquet;
	
	RetrieveOptInOffersCommand(String targetUrl, String pelatroHost, String msisdn) {
		super(targetUrl, pelatroHost);
		this.msisdn = msisdn;
	}
	
	@Override
	public void prepareRequest() {
		getRequest().setMethod(PelatroRequestMethod.pelatroBouquetMethod.toString());
		getRequest().addParam(new PelatroRequestParam(PelatroTokens.msisdn.toString(), msisdn));	
	}
	
	@Override
	public void prepareResponse() throws PelatroCommandException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			offerBouquet = mapper.readValue(jsonResponse, OfferBouquet.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OfferBouquet getOfferBouquet() {
		return offerBouquet;
	}
}
