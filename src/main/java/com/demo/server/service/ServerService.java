package com.demo.server.service;

import java.util.List;

import com.demo.server.domain.DfCharacter;
import com.demo.server.domain.DfServer;

public interface ServerService {

	public List<DfServer> getServers() throws Throwable;
	
	public DfCharacter getCharacter(String serverId, String characterId) throws Throwable;
	
}
