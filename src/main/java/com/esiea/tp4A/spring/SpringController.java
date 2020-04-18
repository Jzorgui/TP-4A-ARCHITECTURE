package com.esiea.tp4A.spring;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.esiea.tp4A.domain.LoadPlayer;
import com.esiea.tp4A.domain.LocalMap;

@Controller
public class SpringController {
	
	private final LoadPlayer load = new LoadPlayer();
	private final LocalMap localMap = new LocalMap();

	@PostMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> create(@PathVariable String playerName) throws Exception {
		if (load.createPlayer(playerName, localMap)) {
			return new ResponseEntity<String>(load.getJSONResponse(localMap).toString(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Impossible de créée le joueur", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/")
	public String first() {
		return "pages/web";
	}
	
	@GetMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> loadPlayer(@PathVariable String playerName) throws Exception {
		if (load.loadingPlayer(playerName, localMap)) {
			return new ResponseEntity<String>(load.getJSONResponse(localMap).toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Joueur inexistant", HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "/api/player/{playerName}/{command}", produces="application/json")
	public ResponseEntity<String> action(@PathVariable String playerName, @PathVariable String command) throws Exception {
		if (load.loadingPlayer(playerName, localMap)) {
			load.moveRover(command);
			return new ResponseEntity<String>(load.getJSONResponse(localMap).toString(), HttpStatus.OK);
			} else {
			return new ResponseEntity<String>("aurevoir", HttpStatus.NOT_FOUND);
		}
	}
}
