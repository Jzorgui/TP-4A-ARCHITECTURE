package com.esiea.tp4A.domain;

import java.util.HashSet;

 
import java.util.Random;
import java.util.Set;

public class PlanetMapImp implements PlanetMap {
	
	private final PartySettings partySettings= new PartySettings();
	private final Random rand = new Random();
	private final LocalMap localMap = new LocalMap();
	private final int mapSize = partySettings.generateSizeMap();
	private final int obstacleNumber = partySettings.generateObstacle(mapSize);
	private final Set<Position> set = new HashSet<Position>();

	@Override
	public Set<Position> obstaclePositions() {
		for (int i = 0; i < obstacleNumber; i++) {
			boolean bCreat=false;
			while (bCreat == false) {
				int x = -mapSize/2 + (rand.nextInt(mapSize));
				int y = -mapSize/2 + (rand.nextInt(mapSize));
				bCreat= localMap.fillListObstacle(x, y) ? true : false;
				
				set.add(Position.of(x, y, Direction.NORTH));
			}
		}
		System.out.println("localMap" + localMap.getSetPos());
		return set;
	}
	
	public LocalMap getLocalMap() {
		return this.localMap;
	}
}
