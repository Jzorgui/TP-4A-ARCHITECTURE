package com.esiea.tp4A.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.esiea.tp4A.domain.LocalMap;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.PlanetMapImp;

@Controller
public class SpringController {
	
	private String pAction=null;
	private static MarsRoverImp marsRover;
	private int mapSize;
	private LocalMap localMap = new LocalMap();
	private int obstacleNumber;
	private int xInitial, yInitial;

	@PostMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> create(@PathVariable String playerName, ModelMap map) throws Exception {
		if (createPlayer(playerName)) {
			JSONCreator responseJson = new JSONCreator();
			return new ResponseEntity<String>(responseJson.returnJson(localMap, marsRover).toString(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Impossible de créée le joueur", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(path = "/api/player/{playerName}", produces = "application/json")
	public ResponseEntity<String> loadPlayer(@PathVariable String playerName, ModelMap map) throws Exception {
		if (loadingPlayer(playerName)) {
			JSONCreator responseJson = new JSONCreator();
			return new ResponseEntity<String>(responseJson.returnJson(localMap, marsRover).toString(), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("aurevoir", HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "/api/player/{playerName}/{command}")
	public ResponseEntity<String> action(@PathVariable String playerName, @PathVariable String command, ModelMap map) throws Exception {
		pAction = command;
		if (loadingPlayer(playerName)) {
			marsRover.move(pAction);
			JSONCreator responseJson = new JSONCreator();
			return new ResponseEntity<String>(responseJson.returnJson(localMap, marsRover).toString(), HttpStatus.ACCEPTED);
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
			generateObstacle(mapSize);
		}
		marsRover.setLocalMap(localMap);
		setLazerRange();

		boolean ret = generatePlayerPosition(mapSize) ? true : false;
		if (ret) {
		localMap.fillListRover(marsRover);
		}
		return ret;
	}
	
	public boolean loadingPlayer(String name) {

		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				marsRover = rov;
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void setLazerRange() {
		Random rand = new Random();
			int randLazer=rand.nextInt(3);
		
		switch(randLazer) {
		case 0 : marsRover.setLazerRange(5);
		break;
		case 1 : marsRover.setLazerRange(30);
		break;
		case 2 : marsRover.setLazerRange(10000);
		break;
		}
	}
	
	public void generateSizeMap() {
		List<Integer> sizePossibilities = new ArrayList<Integer>();
		sizePossibilities.add(0, 100);
		sizePossibilities.add(1, 300);
		sizePossibilities.add(2, 600);

		Random rand = new Random();
		mapSize = sizePossibilities.get(rand.nextInt(3));
	}
	
	public void generateObstacle(int mapSize) {
		double dObstacleNumber = mapSize * 0.15;
		obstacleNumber = (int) dObstacleNumber;
		PlanetMapImp p = new PlanetMapImp();
		p.setObstacleNumber(obstacleNumber, mapSize);
	}
	
	public boolean generatePlayerPosition(int mapSize) {
		Random rand = new Random();

		boolean bCreat=false;
		int i=0;
		while (i<49) {
			xInitial = -mapSize / 2 + (rand.nextInt(mapSize));
			yInitial = -mapSize / 2 + (rand.nextInt(mapSize));
			System.out.println("Tentative de placement de x : "+xInitial+" y :"+yInitial);
			System.out.println("La place est occupé : "+localMap.isPlaceOccupated(xInitial, xInitial));
			
			if (localMap.isPlaceOccupated(xInitial, yInitial)) {
				i++;
				bCreat=false;
			} else if (!localMap.isPlaceOccupated(xInitial, yInitial)) {
				i+=49;
				bCreat=true;
			}
		}
		
		marsRover.setX(xInitial);
		marsRover.setY(yInitial);
		return bCreat;

	}
		
}

