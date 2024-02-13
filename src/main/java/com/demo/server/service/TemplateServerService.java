package com.demo.server.service;

import com.demo.server.domain.DfCharacter;
import com.demo.server.domain.DfServerList;

public interface TemplateServerService {

	public DfCharacter getCharacter(String serverId, String characterId);
	
	public DfServerList getServers();
	
}
