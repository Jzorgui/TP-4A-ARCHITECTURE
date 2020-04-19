package com.esiea.tp4A.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartySettings {
	
	public int setLazerRange() {
		Random rand = new Random();
		int randLazer = rand.nextInt(3);
		int lazerRange=0;
		switch (randLazer) {
		case 0: lazerRange = 5; break;
		case 1: lazerRange = 30; break;
		case 2: lazerRange = 10000; break;
		}
		return lazerRange;
	}

	public int generateSizeMap() {
		int mapSize;
		List<Integer> sizePossibilities = new ArrayList<Integer>();
		sizePossibilities.add(0, 10);
		sizePossibilities.add(1, 30);
		sizePossibilities.add(2, 60);
		Random rand = new Random();
		mapSize = sizePossibilities.get(rand.nextInt(3));
		return mapSize;
	}

	public void generateObstacle(int mapSize, LocalMap localMap) {
		double dObstacleNumber = mapSize* mapSize * 0.15;
		int obstacleNumber = (int) dObstacleNumber;
		PlanetMapImp p = new PlanetMapImp();
		p.setObstacleNumber(obstacleNumber, mapSize, localMap);
	}

	public boolean generatePlayerPosition(int mapSize, LocalMap localMap, MarsRoverImp marsRover) {
		Random rand = new Random();
		boolean bCreat = false;
		int xInitial = 0;
		int yInitial = 0;
		int i = 0;
		while (i < 49) {
			xInitial = -mapSize/2 + (rand.nextInt(mapSize/2));
			yInitial = -mapSize/2 + (rand.nextInt(mapSize/2));
			if (localMap.isPlaceOccupated(xInitial, yInitial, "")) {
				i++;
				bCreat = false;
			} else if (!localMap.isPlaceOccupated(xInitial, yInitial, "")) {
				i += 49;
				bCreat = true;
			}
		}
		marsRover.setX(xInitial);
		marsRover.setY(yInitial);
		return bCreat;
	}
}