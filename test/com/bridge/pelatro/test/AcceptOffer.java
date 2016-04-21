package com.bridge.pelatro.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bridge.pelatro.command.AcceptOfferCommand;
import com.bridge.pelatro.command.CommandFactory;

public class AcceptOffer {

	private CommandFactory commandFactory;
	private String msisdn = "258827788343";
	private String offerId = "";
	
	private static Logger logger = Logger.getLogger(AcceptOffer.class);
	
	@Before
	public void setUp() throws Exception {
		commandFactory = new CommandFactory("http://10.100.58.15:8283", "10.100.58.15");
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void acceptOffer() throws Exception {
		try {
			AcceptOfferCommand cmd = commandFactory.getAcceptOfferCommand(msisdn, offerId);
			cmd.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
