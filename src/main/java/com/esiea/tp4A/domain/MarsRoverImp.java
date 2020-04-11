package com.esiea.tp4A.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLazerRange(int lazerRange) {
		this.lazerRange = lazerRange;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
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
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == rov.getPosition().getY()) {
								localMap.getSetPos().remove(rov);
							}
						}
					}
				}
			case SOUTH:
				// Try to destruct an obstacle
				for (Position pos : set) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y - i == pos.getY()) {
								set.remove(pos);
							}
						}
					}
				}
				// Try to desctruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y - i == rov.getPosition().getY()) {
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}
			case WEST:

				// Try to destruct an obstacle
				for (Position pos : set) {
					if (pos.getY() == y) {
						for (int i = 0; i < lazerRange; i++) {
							if (x - i == pos.getX()) {
								set.remove(pos);
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getY() == y) {
						for (int i = 0; i < lazerRange; i++) {
							if (x - i == rov.getPosition().getX()) {
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}
			case EAST:
				// Try to destruct an obstacle
				for (Position pos : set) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == pos.getY()) {
								set.remove(pos);
							}
						}
					}
				}
				// Try to destruct an ennemy
				for (MarsRoverImp rov : localMap.getSetRover()) {
					if (rov.getPosition().getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == rov.getPosition().getY()) {
								localMap.getSetRover().remove(rov);
							}
						}
					}
				}

			}
		}

		position = Position.of(x, y, direction);

		for (Position pos : set) {
			if (pos.getX() == position.getX() && pos.getY() == position.getY()) {
				position = Position.of(initialPosition.getX(), initialPosition.getY(), direction);
			}
		}
		return position;
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
