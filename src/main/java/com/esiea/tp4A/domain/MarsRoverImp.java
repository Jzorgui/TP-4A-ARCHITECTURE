package com.esiea.tp4A.domain;

import java.util.HashSet;

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
	private Set<Position> set = new HashSet<Position>();
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
		Position initialPosition = Position.of(x, y, direction);
		switch (command) {
		case "f":
			if (direction.equals(Direction.NORTH)) {
				if (this.y < yMaxMap) {
					this.y += 1;
				} else if (this.y == yMaxMap) {
					this.y = yMinMap;
				}
			} else if (direction.equals(Direction.SOUTH)) {
				if (this.y < yMinMap) {
					this.y -= 1;
				} else if (this.y == yMinMap) {
					this.y = yMaxMap;
				}
			} else if (direction.equals(Direction.EAST)) {
				if (this.x < xMaxMap) {
					this.x += 1;
				} else if (this.x == xMaxMap) {
					this.x = -xMinMap;
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
			switch (direction) {
			case NORTH:
				// Try to destruct an obstacle
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == pos.getY()) {
								localMap.getSetPos().remove(pos);
								System.out.println("Obstacle eliminer");
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == rov.getPosition().getY()) {
								rov.setStatus(false);
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}
			case SOUTH:
				// Try to destruct an obstacle
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y - i == pos.getY()) {
								localMap.getSetPos().remove(pos);
							}
						}
					}
				}
				// Try to desctruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y - i == rov.getPosition().getY()) {
								rov.setStatus(false);
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}
			case WEST:

				// Try to destruct an obstacle
				for (Position pos : localMap.getSetPos()) {
					if (pos.getY() == y) {
						for (int i = 0; i < lazerRange; i++) {
							if (x - i == pos.getX()) {
								localMap.getSetPos().remove(pos);
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getY() == y) {
						for (int i = 0; i < lazerRange; i++) {
							if (x - i == rov.getPosition().getX()) {
								rov.setStatus(false);
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}
			case EAST:
				// Try to destruct an obstacle
				for (Position pos : localMap.getSetPos()) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == pos.getY()) {
								localMap.getSetPos().remove(pos);
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == rov.getPosition().getY()) {
								rov.setStatus(false);
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}

			}
		}

		position = Position.of(x, y, direction);

		if (localMap.isPlaceOccupated(position.getX(), position.getY())) {
			position = Position.of(initialPosition.getX(), initialPosition.getY(), direction);
		}
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

		// We first Verify if there is some obstacle on x+15 and y+15
		for (int i = x + 1; i < x + 17; i++) {
			for (int p = y + 1; p < y + 17; p++) {
				for (Position pos : localMap2.getSetPos()) {
					if (pos.getX() == i) {
						if (pos.getY() == p) {
							mapRadar.put(new JSONObject().put("x", pos.getX()).put("y", pos.getY()));
						}
					}
				}
			}
		}

		// We then verify x-15 and y-15
		for (int i = x - 1; i < x - 17; i--) {
			for (int p = y - 1; p < y - 17; p--) {
				for (Position pos : localMap2.getSetPos()) {
					if (pos.getX() == i) {
						if (pos.getY() == p) {
							mapRadar.put(new JSONObject().put("x", pos.getX()).put("y", pos.getY()));
						}
					}
				}
			}
		}

		// We then verify x+15 and y-15
		for (int i = x - 1; i < x + 17; i++) {
			for (int p = y - 1; p < y - 17; p--) {
				for (Position pos : localMap2.getSetPos()) {
					if (pos.getX() == i) {
						if (pos.getY() == p) {
							mapRadar.put(new JSONObject().put("x", pos.getX()).put("y", pos.getY()));
						}
					}
				}
			}
		}

		// We then verify x-15 and y+15
		for (int i = x - 1; i < x - 17; i--) {
			for (int p = y - 1; p < y + 17; p++) {
				for (Position pos : localMap2.getSetPos()) {
					if (pos.getX() == i) {
						if (pos.getY() == p) {
							mapRadar.put(new JSONObject().put("x", pos.getX()).put("y", pos.getY()));
						}
					}
				}
			}
		}

		return mapRadar;
	}

	public JSONArray radarEnnemie(LocalMap localMap2) {

		JSONArray mapRadar = new JSONArray();

		System.out.println("mapRadar avant : " + mapRadar);

		System.out.println(localMap2.getSetRover().size());
		// We first Verify if there is some ennemies on x+15 and y+15
		for (int i = x + 1; i < x + 17; i++) {
			for (int p = y + 1; p < y + 17; p++) {
				for (MarsRoverImp rov : localMap2.getSetRover()) {
					if (rov.getX() == i) {
						if (rov.getY() == p) {
							mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name",
									rov.getName()));
							System.out.println(mapRadar);
						}
					}
				}
			}
		}

		// We then verify x-15 and y-15
		for (int i = x - 1; i < x - 17; i--) {
			for (int p = y - 1; p < y - 17; p--) {
				for (MarsRoverImp rov : localMap2.getSetRover()) {
					if (rov.getX() == i) {
						if (rov.getY() == p) {
							mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name",
									rov.getName()));
						}
					}
				}
			}
		}

		// We then verify x+15 and y-15
		for (int i = x - 1; i < x + 17; i++) {
			for (int p = y - 1; p < y - 17; p--) {
				for (MarsRoverImp rov : localMap2.getSetRover()) {
					if (rov.getX() == i) {
						if (rov.getY() == p) {
							mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name",
									rov.getName()));
						}
					}
				}
			}
		}
		// We then verify x-15 and y+15
		for (int i = x - 1; i < x - 17; i--) {
			for (int p = y - 1; p < y + 17; p++) {
				for (MarsRoverImp rov : localMap2.getSetRover()) {
					if (rov.getX() == i) {
						if (rov.getY() == p) {
							mapRadar.put(new JSONObject().put("x", rov.getX()).put("y", rov.getY()).put("name",
									rov.getName()));
						}
					}
				}
			}
		}
		System.out.println("mapRadar après : " + mapRadar);

		return mapRadar;
	}

	@Override
	public MarsRover updateMap(PlanetMap map) {
		set = map.obstaclePositions();
		return this;
	}

	@Override
	public MarsRover configureLaserRange(int range) {
		lazerRange = range;
		return this;
	}
}
