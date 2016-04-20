package com.bridge.pelatro.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.bridge.pelatro.command.CommandFactory;
import com.bridge.pelatro.command.RetrieveOptInOffersCommand;

public class RetrieveOfferBouquet {

	private CommandFactory commandFactory;
	private String msisdn = "258827788343";
	
	private static Logger logger = Logger.getLogger(RetrieveOfferBouquet.class);
	
	@Before
	public void setUp() throws Exception {
		commandFactory = new CommandFactory("http://10.100.58.15:8283", "10.100.58.15");
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	public void testRetrieveOptionalOffers() throws Exception {
		try {
			RetrieveOptInOffersCommand cmd = commandFactory.getRetrieveOptInOffersCommand(msisdn);
			cmd.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
