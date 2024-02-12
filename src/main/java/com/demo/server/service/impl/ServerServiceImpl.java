package com.demo.server.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import com.demo.server.domain.DfCharacter;
import com.demo.server.domain.DfServer;
import com.demo.server.service.ServerService;
import com.demo.util.ConvertUtil;

@Service
public class ServerServiceImpl implements ServerService{
	
	public List<DfServer> getServers() throws Throwable {
		
		RestClient restClient = RestClient.builder().baseUrl("https://api.neople.co.kr").build();
		
		List<DfServer> result = restClient.get()
			.uri(builder -> builder.path("/df/servers")
					.queryParam("apikey", "GHki7pbeMtcZ3Ws18da6GBlM8qZWzUz6")
					.build())
			.accept(MediaType.APPLICATION_JSON)
			.exchange((req, rep) -> {
				try {
					return ConvertUtil.HttpReponseToClassList(DfServer.class, "rows", rep);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
				}
			});
		
		return result;
		
	}
	
	public DfCharacter getCharacter(String serverId, String characterId) throws Throwable {
		
		RestClient restClient = RestClient.builder().baseUrl("https://api.neople.co.kr").build();
		
		DfCharacter result = restClient.get()
			.uri(builder -> builder.path("/df/servers/{serverId}/characters/{characterId}")
					.queryParam("apikey", "GHki7pbeMtcZ3Ws18da6GBlM8qZWzUz6")
					.build(serverId, characterId))
			.accept(MediaType.APPLICATION_JSON)
			.exchange((req, rep) -> {
				try {
					return ConvertUtil.HttpReponseToClass(DfCharacter.class, rep);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
				}
			});
		
		return result;
		
	}
	
}
