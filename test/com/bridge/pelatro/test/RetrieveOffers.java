package com.bridge.pelatro.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bridge.pelatro.command.CommandFactory;
import com.bridge.pelatro.command.RetrieveOffersCommand;

public class RetrieveOffers {

	private CommandFactory commandFactory;
	private String msisdn = "258827788383";
	
	private static Logger logger = Logger.getLogger(RetrieveOffers.class);
	
	@Before
	public void setUp() throws Exception {
		commandFactory = new CommandFactory("http://10.100.58.15:8282", "10.100.58.15");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieveOffers() throws Exception {
		try {
			RetrieveOffersCommand cmd = commandFactory.getRetrieveOffersCommand(msisdn, 1);
			cmd.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
