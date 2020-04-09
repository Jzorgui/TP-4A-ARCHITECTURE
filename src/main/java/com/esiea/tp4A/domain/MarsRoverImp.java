package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class MarsRoverImp implements MarsRover {

	
	//Attribute
	private String name;
	private int lazerRange = 0;
	private boolean status=true;
	private Position position = Position.of(0, 0, Direction.NORTH);
	private Direction direction = position.getDirection();
	private int x = position.getX();
	private int y = position.getY();
	private Set<Position> set = new HashSet<Position>();
	
	//Constructors
	public MarsRoverImp(String name) {
		this.name = name;
	}
	public MarsRoverImp() {
	}
	
	
	//Getters and Setters
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
	
	//Functions
	@Override
	public Position move(String command) {
		Position initialPosition = position;
		switch (command) {
		case "f":
			if (direction.equals(Direction.NORTH)) {
				if (this.y < 50) {
					this.y += 1;
				} else if (this.y == 50) {
					this.y = -49;
				}
			} else if (direction.equals(Direction.SOUTH)) {
				if (this.y > -50) {
					this.y -= 1;
				} else if (this.y == -50) {
					this.y = 49;
				}
			} else if (direction.equals(Direction.EAST)) {
				if (this.x < 50) {
					this.x += 1;
				} else if (this.x == 50) {
					this.x = -49;
				}
			} else if (direction.equals(Direction.WEST)) {
				if (this.x > -50) {
					this.x -= 1;
				} else if (this.x == -50) {
					this.x = 49;
				}

			}
			break;
		case "b":
			if (direction.equals(Direction.NORTH)) {
				if (this.y > -50) {
					this.y -= 1;
				} else if (this.y == -50) {
					this.y = 49;
				}
			} else if (direction.equals(Direction.SOUTH)) {
				if (this.y < 50) {
					this.y += 1;
				} else if (this.y == 50) {
					this.y = -49;
				}
			} else if (direction.equals(Direction.EAST)) {
				if (this.x > -50) {
					this.x -= 1;
				} else if (this.x == -50) {
					this.x = 49;
				}
			} else if (direction.equals(Direction.WEST)) {
				if (this.x < 50) {
					this.x += 1;
				} else if (this.x == 50) {
					this.x = -49;
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
				for (Position pos : set) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == pos.getY()) {
								set.remove(pos);
							}
						}
					}
				}
			case SOUTH:
				for (Position pos : set) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y - i == pos.getY()) {
								set.remove(pos);
							}
						}
					}
				}
			case WEST:
				for (Position pos : set) {
					if (pos.getY() == y) {
						for (int i = 0; i < lazerRange; i++) {
							if (x - i == pos.getX()) {
								set.remove(pos);
							}
						}
					}
				}
			case EAST:
				for (Position pos : set) {
					if (pos.getX() == x) {
						for (int i = 0; i < lazerRange; i++) {
							if (y + i == pos.getY()) {
								set.remove(pos);
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
