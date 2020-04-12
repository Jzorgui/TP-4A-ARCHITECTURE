package com.esiea.tp4A.domain;

import com.esiea.tp4A.spring.JSONCreator;

public class LoadPlayer {
	private static MarsRoverImp marsRover;
	private int mapSize;
	private LocalMap localMap = new LocalMap();
	private PartySettings party = new PartySettings();
	private JSONCreator responseJson = new JSONCreator();

	public boolean createPlayer(String name) {
		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				return false;
			}
		}
		marsRover = new MarsRoverImp(name);

		if (localMap.getSetRover().isEmpty()) {
			mapSize = party.generateSizeMap(mapSize);
			marsRover.GenerateMap(mapSize);
			party.generateObstacle(mapSize, localMap);
		}
		marsRover.setLocalMap(localMap);
		int lazerRange = party.setLazerRange();
		marsRover.configureLaserRange(lazerRange);

		boolean ret = party.generatePlayerPosition(mapSize, localMap, marsRover) ? true : false;
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
	
	public void moveRover(String command) {
		this.marsRover.move(command);
	}

	public String getJSONResponse() throws Exception {
		return responseJson.returnJson(localMap, marsRover).toString();
	}

}
