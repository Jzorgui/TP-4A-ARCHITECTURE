package com.esiea.tp4A.domain;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class MarsRoverImp implements MarsRover {

	// Attribute
	private String name;
	private int lazerRange = 0;
	private boolean status = true;
	private int x;
	private int y;
	private Position position = Position.of(x, y, Direction.NORTH);
	private Direction direction = position.getDirection();
	private LocalMap localMap;

	// MapRange
	private int xMaxMap, yMaxMap, xMinMap, yMinMap;

	// Constructors
	public MarsRoverImp(String name) {
		this.name = name;
	}

	public MarsRoverImp() {
	}

	// Getters and Setters

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
			if (direction.equals(Direction.NORTH)) {
				if (this.y < yMaxMap) {
					this.y += 1;
				} else if (this.y == yMaxMap) {
					this.y = yMinMap;
				}
			} else if (direction.equals(Direction.SOUTH)) {
				if (this.y > yMinMap) {
					this.y -= 1;
				} else if (this.y == yMinMap) {
					this.y = yMaxMap;
				}
			} else if (direction.equals(Direction.EAST)) {
				if (this.x < xMaxMap) {
					this.x += 1;
				} else if (this.x == xMaxMap) {
					this.x = xMinMap;
				}
			} else if (direction.equals(Direction.WEST)) {
				if (this.x > xMinMap) {
					this.x -= 1;
				} else if (this.x == xMinMap) {
					this.x = xMaxMap;
				}

			}
			break;
		case "b":
			if (direction.equals(Direction.NORTH)) {
				if (this.y > yMinMap) {
					this.y -= 1;
				} else if (this.y == yMinMap) {
					this.y = yMaxMap;
				}
			} else if (direction.equals(Direction.SOUTH)) {
				if (this.y < yMaxMap) {
					this.y += 1;
				} else if (this.y == yMaxMap) {
					this.y = yMinMap;
				}
			} else if (direction.equals(Direction.EAST)) {
				if (this.x > xMinMap) {
					this.x -= 1;
				} else if (this.x == xMinMap) {
					this.x = xMaxMap;
				}
			} else if (direction.equals(Direction.WEST)) {
				if (this.x < xMaxMap) {
					this.x += 1;
				} else if (this.x == xMaxMap) {
					this.x = xMinMap;
				}
			}
			break;

		case "l":
			if (direction.equals(Direction.NORTH)) {
				this.direction = Direction.WEST;
			} else if (direction.equals(Direction.SOUTH)) {
				this.direction = Direction.EAST;
			} else if (direction.equals(Direction.EAST)) {
				this.direction = Direction.NORTH;
			} else if (direction.equals(Direction.WEST)) {
				this.direction = Direction.SOUTH;
			}

			break;
		case "r":
			if (direction.equals(Direction.NORTH)) {
				this.direction = Direction.EAST;
			} else if (direction.equals(Direction.SOUTH)) {
				this.direction = Direction.WEST;
			} else if (direction.equals(Direction.EAST)) {
				this.direction = Direction.SOUTH;
			} else if (direction.equals(Direction.WEST)) {
				this.direction = Direction.NORTH;
			}
			break;

		case "s":
			lazerAttak();
			break;
		}
		if (localMap.isPlaceOccupated(x, y, name) == true || this.getStatus() == false) {
			x = initialX;
			y = initialY;
		}
		position = Position.of(x, y, direction);

		return position;
	}

	public void lazerAttak() {
		switch (direction) {
		case NORTH:
			for (int i = y + 1; i <= y + lazerRange; i++) {
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == x && pos.getY() == i) {
						localMap.getSetPos().remove(pos);
						break;
					}
				}
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getX() == x && rov.getY() == i && rov.getStatus() == true) {
						rov.setStatus(false);
						break;
					}
				}
			}
		case SOUTH:
			for (int i = y - 1; i >= y - lazerRange; i--) {
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == x && pos.getY() == i) {
						localMap.getSetPos().remove(pos);
						break;
					}
				}
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getX() == x && rov.getY() == i && rov.getStatus() == true) {
						rov.setStatus(false);
						break;
					}
				}
			}
		case WEST:
			for (int i = x - 1; i >= x - lazerRange; i--) {
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == i && pos.getY() == y) {
						localMap.getSetPos().remove(pos);
						break;
					}
				}
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getX() == i && rov.getY() == y  && rov.getStatus() == true) {
						rov.setStatus(false);
						break;
					}
				}
			}
		case EAST:
			for (int i = x + 1; i <= x + lazerRange; i++) {
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == i && pos.getY() == y) {
						localMap.getSetPos().remove(pos);
						break;
					}
				}
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getX() == i && rov.getY() == y  && rov.getStatus() == true) {
						rov.setStatus(false);
						break;
					}
				}
			}
		}
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
						mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name", name));
					}
				}
			}
		}
		return mapRadar;
	}

	@Override
	public MarsRover updateMap(PlanetMap map) {
		return this;
	}

	@Override
	public MarsRover configureLaserRange(int range) {
		lazerRange = range;
		return this;
	}
}
