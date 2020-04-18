package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class MarsRoverImp implements MarsRover {
	
	/* Attribute */
	private String name;
	private int lazerRange = 0;
	private boolean status = true;
	private int x, y;
	private Position position = Position.of(x, y, Direction.NORTH);
	private Direction direction = position.getDirection();
	private LocalMap localMap;
	private final MarsRoverMove roverMove = new MarsRoverMove();
	private final MarsRoverFunction roverFunc = new MarsRoverFunction();
	private final PlanetMapImp mapImp = new PlanetMapImp();
	/* MapRange */
	private int xMaxMap, yMaxMap, xMinMap, yMinMap;

	/* Constructors */
	public MarsRoverImp(String name) {
		this.name = name;
	}

	/* Test Contructor */
	public MarsRoverImp() {
		localMap = new LocalMap();
		this.GenerateMap(100);
		this.setX(x);
		this.setY(y);
		this.setPosition(Position.of(x, y, direction));
		this.configureLaserRange(3);
		this.setStatus(true);
	}

	/* Getters and Setters */public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setLocalMap(LocalMap localMap) {
		this.localMap = localMap;
	}

	public LocalMap getLocalMap() {
		return this.localMap;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position pos) {
		this.position = pos;
	}

	public int getLazerRange() {
		return lazerRange;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getxMaxMap() {
		return xMaxMap;
	}

	public int getyMaxMap() {
		return yMaxMap;
	}

	public int getxMinMap() {
		return xMinMap;
	}

	public int getyMinMap() {
		return yMinMap;
	}


	// Functions
	@Override
	public Position move(String command) {
		int initialX = x;
		int initialY = y;
		switch (command) {
		case "f":
			roverMove.moveForward(direction, this);
			break;
		case "b":
			roverMove.moveBack(direction, this);
			break;
		case "l":
			roverMove.turnLeft(direction, this);
			break;
		case "r":
			roverMove.turnRight(direction, this);
			break;
		case "s":
			if (roverFunc.lazerAttak(direction, localMap, lazerRange, x, y)) {
				System.out.println("You've destroyed something !");
			} else {
				System.out.println("You hired but Nothing happened");
			}
			break;
		}
		if (localMap.isPlaceOccupated(x, y, name) == true || this.getStatus() == false) {
			x = initialX;
			y = initialY;
		}
		position = Position.of(x, y, direction);
		return position;
	}

	public void GenerateMap(int mapSize) {
		xMaxMap = mapSize / 2;
		xMinMap = xMaxMap - mapSize;
		yMaxMap = mapSize / 2;
		yMinMap = yMaxMap - mapSize;
	}

	public JSONArray radarObstacle(LocalMap localMap2) {
		JSONArray mapRadar = new JSONArray();
		for (int i = -16; i < x + 16; i++) {
			for (int p = y - 16; p < y + 16; p++) {
				for (Position pos : localMap2.getSetPos()) {
					if (pos.getX() == i && pos.getY() == p) {
						mapRadar.put(new JSONObject().put("x", pos.getX()).put("y", pos.getY()));
					}
				}
			}
		}
		return mapRadar;
	}

	public JSONArray radarEnnemie(LocalMap localMap2) {
		JSONArray mapRadar = new JSONArray();
		for (int i = -16; i < x + 16; i++) {
			for (int p = y - 16; p < y + 16; p++) {
				for (MarsRoverImp rov : localMap2.getSetRover()) {
					if (rov.getX() == i && rov.getY() == p && !rov.getName().equals(name)) {
						mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name", rov.getName()));
					}
				}
			}
		}
		return mapRadar;
	}
	
	@Override 
	public MarsRover initialize(Position pos) {
		this.setX(pos.getX());
		this.setY(pos.getY());
		this.setPosition(pos);
		return this;
	}
	
	@Override
	public MarsRover updateMap(PlanetMap map) {
		localMap = mapImp.getLocalMap();
		for (Position pos : map.obstaclePositions()) {
			localMap.fillListObstacle(pos.getX(), pos.getY());
		}
		return this;
	}

	@Override
	public MarsRover configureLaserRange(int range) {
		lazerRange = range;
		return this;
	}
}
