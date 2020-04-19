package com.esiea.tp4A.domain;

import java.util.HashSet;
 
import java.util.Random;
import java.util.Set;

public class PlanetMapImp implements PlanetMap {

	private int obstacleNumber;
	private Random rand = new Random();
	private LocalMap localMap;
	private boolean bCreat;
	private int mapSize;

	@Override
	public Set<Position> obstaclePositions() {
		for (int i = 0; i < obstacleNumber; i++) {
			bCreat=false;
			while (bCreat == false) {
				int x = -mapSize/2 + (rand.nextInt(mapSize));
				int y = -mapSize/2 + (rand.nextInt(mapSize));
				bCreat= localMap.fillListObstacle(x, y) ? true : false;
			}
		}
		return null;
	}
	
	public void setObstacleForTest(int x, int y,LocalMap localMap2) {
		localMap=localMap2;
		localMap.fillListObstacle(x, y);
	}

	public void setObstacleNumber(int obstacleNumber, int mapSize, LocalMap localMap) {
		this.obstacleNumber = obstacleNumber;
		this.mapSize=mapSize;
		this.localMap=localMap;
		obstaclePositions();
	}
}
