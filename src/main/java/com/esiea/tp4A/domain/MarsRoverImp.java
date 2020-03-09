package com.esiea.tp4A.domain;

public class MarsRoverImp implements MarsRover {

	private Position position = Position.of(0, 0, Direction.NORTH);
	private Direction direction = position.getDirection();
	private int x = position.getX();
	private int y = position.getY();

	public Position getPosition() {
		return position;
	}

	@Override
	public Position move(String command) {
		switch (command) {
		case "f":
			if (direction.equals(Direction.NORTH)) {
				this.y += 1;
			}
			if (direction.equals(Direction.SOUTH)) {
				this.y -= 1;
			}
			if (direction.equals(Direction.EAST)) {
				this.x += 1;
			}
			if (direction.equals(Direction.WEST)) {
				this.x -= 1;
			}
			break;
		case "b":
			if (direction.equals(Direction.NORTH)) {
				this.y -= 1;
			} else if (direction.equals(Direction.SOUTH)) {
				this.y += 1;
			} else if (direction.equals(Direction.EAST)) {
				this.x -= 1;
			} else if (direction.equals(Direction.WEST)) {
				this.x += 1;
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

		}
		position = Position.of(x, y, direction);
		return position;
	}
}
