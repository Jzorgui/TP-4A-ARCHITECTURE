package com.esiea.tp4A.spring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.esiea.tp4A.domain.LocalMap;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.Position;

@Controller
public class SpringController {
	
	private String pAction=null;
	private static MarsRoverImp marsRover;
	private static Set<String> setStringPlayer= new HashSet<String>();
	private static Set<MarsRoverImp> setPlayer= new HashSet<MarsRoverImp>();
	private static int x,y;
	private String direction;
	private int randomId = (int) (Math.random() * ( 01 - 1000 ));
	private String randomName= "random"+randomId;
	private String roverName;
	private int lazerRange;
	private boolean status;
	private int mapSize;
	private LocalMap localMap = new LocalMap();

	@PostMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> create(@PathVariable String playerName, ModelMap map) throws Exception {
		if (createPlayer(playerName)) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Impossible de créée le joueur", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> loadPlayer(@PathVariable String playerName, ModelMap map) throws Exception {
		if (loadingPlayer(playerName)) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("aurevoir", HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "/api/player/{playerName}/{command}")
	public ResponseEntity<String> action(@PathVariable String playerName, @PathVariable String command, ModelMap map) throws Exception {
		pAction = command;
		if (loadingPlayer(playerName)) {
			marsRover.move(pAction);
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("aurevoir", HttpStatus.NOT_FOUND);
		}
	}
	
	public boolean createPlayer(String name) {

		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				return false;
			}
		}

		marsRover = new MarsRoverImp(name);
		
		if (localMap.getSetRover().isEmpty()) {
			generateSizeMap();
			marsRover.GenerateMap(mapSize);
			//TODO GENERATE OBSTACLE
		}
		
		// GENERATE PLAYER POSITION
		localMap.fillListRover(marsRover);
		
		return true;
	}
	
	public void generateSizeMap() {
		List<Integer> sizePossibilities = new ArrayList<Integer>();
		sizePossibilities.add(0, 100);
		sizePossibilities.add(1, 300);
		sizePossibilities.add(2, 600);

		Random rand = new Random();
		mapSize = sizePossibilities.get(rand.nextInt(3));
	}
		
}

