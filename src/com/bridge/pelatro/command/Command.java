package com.bridge.pelatro.command;

import com.bridge.pelatro.exception.PelatroCommandException;
import com.bridge.pelatro.exception.ConnectionException;

public interface Command {

	public void execute() throws ConnectionException, PelatroCommandException;
	
}
