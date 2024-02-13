package com.demo.server.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server.exception.DfException;
import com.demo.server.service.ServerService;
import com.demo.server.service.TemplateServerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ServerController {

	private final ServerService serverService;
	
	private final TemplateServerService templateServerService;

	@GetMapping("/servers")
	public ResponseEntity<?> getServers() throws Throwable {

		try {
			return ResponseEntity.ok(serverService.getServers());

		} catch (DfException e) {
			return new ResponseEntity<>(e.getError(), HttpStatusCode.valueOf(e.getError().getStatus()));

		}

	}

	@GetMapping("/servers/{serverId}/characters/{characterId}")
	public ResponseEntity<?> getCharacter(@PathVariable("serverId") String serverId,
			@PathVariable("characterId") String characterId) throws Throwable {

		// cain, 290683687f21c16c8b76961153010519

		try {
			return ResponseEntity.ok(serverService.getCharacter(serverId, characterId));

		} catch (DfException e) {
			return new ResponseEntity<>(e.getError(), HttpStatusCode.valueOf(e.getError().getStatus()));

		}

	}
	
	@GetMapping("/template/servers")
	public ResponseEntity<?> getTemplateServers() throws Throwable {

		try {
			return ResponseEntity.ok(templateServerService.getServers());

		} catch (DfException e) {
			return new ResponseEntity<>(e.getError(), HttpStatusCode.valueOf(e.getError().getStatus()));

		}

	}
	
	@GetMapping("/template/servers/{serverId}/characters/{characterId}")
	public ResponseEntity<?> getTemplateCharacter(@PathVariable("serverId") String serverId,
			@PathVariable("characterId") String characterId) throws Throwable {
		
		try {
			return ResponseEntity.ok(templateServerService.getCharacter(serverId, characterId));

		} catch (DfException e) {
			return new ResponseEntity<>(e.getError(), HttpStatusCode.valueOf(e.getError().getStatus()));

		}
		
	}

}
