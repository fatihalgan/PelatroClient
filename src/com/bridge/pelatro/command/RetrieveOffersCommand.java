package com.bridge.pelatro.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bridge.pelatro.exception.PelatroCommandException;
import com.bridge.pelatro.model.RecentSubscriberActivity;
import com.bridge.pelatro.model.RecentSubscriberActivityResponse;
import com.bridge.pelatro.serializer.PelatroRequestParam;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RetrieveOffersCommand extends AbstractPelatroCommand {

	private static Logger logger = Logger.getLogger(RetrieveOffersCommand.class);
	
	private String msisdn;
	private int fetchDepth;
	private RecentSubscriberActivity recentSubscriberActivity;
	
	RetrieveOffersCommand(String targetUrl, String pelatroHost, String msisdn, int fetchDepth) {
		super(targetUrl, pelatroHost);
		this.msisdn = msisdn;
		this.fetchDepth = fetchDepth;
	}
	
	@Override
	public void prepareRequest() {
		getRequest().setMethod(PelatroRequestMethod.pelatroRsaMethod.toString());
		getRequest().addParam(new PelatroRequestParam(PelatroTokens.msisdn.toString(), msisdn));
		getRequest().addParam(new PelatroRequestParam(PelatroTokens.fetchDepth.toString(), String.valueOf(fetchDepth)));
	}

	@Override
	public void prepareResponse() throws PelatroCommandException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			RecentSubscriberActivityResponse resp = mapper.readValue(jsonResponse, RecentSubscriberActivityResponse.class); 
			recentSubscriberActivity = resp.getRecentSubscriberActivity();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RecentSubscriberActivity getRecentSubscriberActivity() {
		return recentSubscriberActivity;
	}
	
}
