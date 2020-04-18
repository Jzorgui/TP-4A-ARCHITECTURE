package com.esiea.tp4A.domain;

import com.esiea.tp4A.spring.JSONCreator;

public class LoadPlayer {
	
	/**
	 * MarsRover object is not final because we will either create it or load it
	 */
	private  MarsRoverImp marsRover = new MarsRoverImp();
	private final PartySettings party = new PartySettings();
	private final JSONCreator responseJson = new JSONCreator();
	private final int lazerRange = party.setLazerRange();
	private final int mapSize =  party.generateSizeMap();
	private final PlanetMap map = new PlanetMapImp();
	private final PlanetMapImp mapImp = new PlanetMapImp();
	private final LocalMap localMap = mapImp.getLocalMap();

	public boolean createPlayer(String name) {
		marsRover = new MarsRoverImp(name);
		if (isPlayerNameFree(name) == false) {
			return false;
		}
		setPartySettings();
		boolean ret = party.generatePlayerPosition(mapSize, localMap, marsRover) ? true : false;
		if (ret) {
			localMap.fillListRover(marsRover);
		}
		return ret;
	}
	
	public void setPartySettings() {
		map.obstaclePositions();
		marsRover.updateMap(map);
		marsRover.GenerateMap(mapSize);
		marsRover.configureLaserRange(lazerRange);
	}
	
	public boolean isPlayerNameFree(String name) {
		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean loadingPlayer(String name) {
		boolean bReturn=false;
		for (MarsRoverImp rov : localMap.getSetRover()) {
			if (rov.getName().equals(name)) {
				marsRover = rov;
				return true;
			} else {
				bReturn = false;
			}
		}
		return bReturn;
	}
	
	public void moveRover(String command) {
		this.marsRover.move(command);
	}

	public String getJSONResponse() throws Exception {
		return responseJson.returnJson(marsRover,localMap).toString();
	}

}
