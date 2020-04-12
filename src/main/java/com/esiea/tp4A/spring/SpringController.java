package com.esiea.tp4A.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.esiea.tp4A.domain.LoadPlayer;

@Controller
public class SpringController {
	
	private LoadPlayer load = new LoadPlayer();

	@PostMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> create(@PathVariable String playerName, ModelMap map) throws Exception {
		if (load.createPlayer(playerName)) {
			return new ResponseEntity<String>(load.getJSONResponse().toString(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Impossible de créée le joueur", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/")
	public String first() {
		return "pages/web";
	}
	
	@GetMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> loadPlayer(@PathVariable String playerName, ModelMap map) throws Exception {
		if (load.loadingPlayer(playerName)) {
			return new ResponseEntity<String>(load.getJSONResponse().toString(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Joueur inexistant", HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "/api/player/{playerName}/{command}")
	public ResponseEntity<String> action(@PathVariable String playerName, @PathVariable String command, ModelMap map) throws Exception {
		if (load.loadingPlayer(playerName)) {
			load.moveRover(command);
			return new ResponseEntity<String>(load.getJSONResponse().toString(), HttpStatus.ACCEPTED);
			} else {
			return new ResponseEntity<String>("aurevoir", HttpStatus.NOT_FOUND);
		}
	}
}
