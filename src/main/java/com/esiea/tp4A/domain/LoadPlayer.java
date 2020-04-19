package com.esiea.tp4A.domain;

import com.esiea.tp4A.spring.JSONCreator;

public class LoadPlayer {
	/**
	 * marsRover not final because it will be either created either loaded
	 */
	private  MarsRoverImp marsRover;
	private final PartySettings party = new PartySettings();
	private final JSONCreator responseJson = new JSONCreator();
	private final int lazerRange = party.setLazerRange();
	private final int mapSize =  party.generateSizeMap();

	public boolean createPlayer(String name, LocalMap localMap) {
		if (isPlayerNameFree(name, localMap) == false) {
			return false;
		}
		marsRover = new MarsRoverImp(name);
		setPartySettings(localMap);
		boolean ret = party.generatePlayerPosition(mapSize, localMap, marsRover) ? true : false;
		if(ret) localMap.fillListRover(marsRover);
		return ret;
	}
	
	public void setPartySettings(LocalMap localMap) {
		if (localMap.getSetRover().isEmpty()) {
			party.generateObstacle(mapSize, localMap);
		}
		marsRover.GenerateMap(mapSize);
		marsRover.setLocalMap(localMap);
		marsRover.configureLaserRange(lazerRange);
	}
	
	public boolean isPlayerNameFree(String name, LocalMap localMap) {
		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean loadingPlayer(String name, LocalMap localMap) {
		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				marsRover =rov;
				return true;
			} 
		}
		return false;
	}
	
	public void moveRover(String command) {
		this.marsRover.move(command);
	}

	public String getJSONResponse(LocalMap localMap) throws Exception {
		return responseJson.returnJson(marsRover,localMap).toString();
	}

}
