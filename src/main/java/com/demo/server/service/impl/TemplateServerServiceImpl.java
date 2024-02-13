package com.demo.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.demo.config.DfRestTemplate;
import com.demo.server.domain.DfCharacter;
import com.demo.server.domain.DfServerList;
import com.demo.server.service.TemplateServerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateServerServiceImpl implements TemplateServerService {

	private final DfRestTemplate dfRestTemplate;

	public DfCharacter getCharacter(String serverId, String characterId) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("test", "test-value");

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("serverId", serverId);
		uriVariables.put("characterId", characterId);

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("apikey", "GHki7pbeMtcZ3Ws18da6GBlM8qZWzUz6");

		DfCharacter dfCharacter = dfRestTemplate.exchange(
				HttpMethod.GET, 
				headers, 
				"https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}", 
				queryParams,
				uriVariables,
				DfCharacter.class);

		return dfCharacter;

	}

	public DfServerList getServers() {

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("apikey", "GHki7pbeMtcZ3Ws18da6GBlM8qZWzUz6");

		DfServerList dfServerList = dfRestTemplate.exchange(
				HttpMethod.GET,
				null,
				"https://api.neople.co.kr/df/servers", 
				queryParams,
				null,
				DfServerList.class);
		
		return dfServerList;

	}

}
