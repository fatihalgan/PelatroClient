package com.bridge.pelatro.test;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.bridge.pelatro.model.RecentSubscriberActivityResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecentSubscriberActivityTest {

	private static final String jsonString = "{\"recentSubscriberActivity\":{\"lookbackPeriodInDays\":60, \"offers\": {\"recentOffers\": [{\"campaign\":\"Increase Recharge\",\"timestamp\":\"20160403000000\",\"offer\":\"Recharge for 100 MT and get 100 free on-net SMS. Offer valid for 2 days.\",\"status\":1,\"validity\":2},{\"campaign\":\"Bundle of Joy\",\"timestamp\":\"20160325000000\",\"offer\":\"Dial USSD *1252# and subscribe for 400 MT, unlimited on-net bundle for next 7 days. Offer valid for 5 days.\",\"status\":2,\"validity\":5},{\"campaign\":\"Double Talk-time\",\"timestamp\":\"20160301000000\",\"offer\":\"Recharge today and get double talk-time. Offer valid for 1 day.\",\"status\":2,\"validity\":1}]},\"rewards\":{\"recentRewards\":[{\"campaign\":\"Increase Recharge\",\"timestamp\":\"20160403143529\",\"type\":\"On-net SMS\",\"unit\":\"count\",\"quantity\":100,\"expiry\":\"20160404000000\"}]}}}";
	
	
	@Test
	public void testRecentSubscriberActivityDeserializer() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			RecentSubscriberActivityResponse recentSubscriberActivityResponse = 
				mapper.readValue(jsonString, RecentSubscriberActivityResponse.class);
			Assert.assertNotNull(recentSubscriberActivityResponse.getRecentSubscriberActivity());
			Assert.assertNotNull(recentSubscriberActivityResponse.getRecentSubscriberActivity().getOffers());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
