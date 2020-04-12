package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PlanetMapImp implements PlanetMap {

	private Set<Position> set = new HashSet<Position>();
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
		return set;
	}

	public void setObstacleNumber(int obstacleNumber, int mapSize, LocalMap localMap) {
		this.obstacleNumber = obstacleNumber;
		this.mapSize=mapSize;
		this.localMap=localMap;
		obstaclePositions();
	}
}
