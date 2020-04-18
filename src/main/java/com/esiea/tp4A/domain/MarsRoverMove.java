package com.esiea.tp4A.domain;

public class MarsRoverMove {

	public void moveForward(Direction direction, MarsRoverImp marsRover) {
		if (marsRover.getPosition().getDirection().equals(Direction.NORTH)) {
			if (marsRover.getY() < marsRover.getyMaxMap()) {
				marsRover.setY(marsRover.getY() + 1);
			} else if (marsRover.getY() == marsRover.getyMaxMap()) {
				marsRover.setY(marsRover.getyMinMap());
			}
		} else if (direction.equals(Direction.SOUTH)) {
			if (marsRover.getY() > marsRover.getyMinMap()) {
				marsRover.setY(marsRover.getY() - 1);
			} else if (marsRover.getY() == marsRover.getyMinMap()) {
				marsRover.setY(marsRover.getyMaxMap());
			}
		} else if (direction.equals(Direction.EAST)) {
			if (marsRover.getX() < marsRover.getxMaxMap()) {
				marsRover.setX(marsRover.getX() + 1);
			} else if (marsRover.getX() == marsRover.getxMaxMap()) {
				marsRover.setX(marsRover.getxMinMap());
			}
		} else if (direction.equals(Direction.WEST)) {
			if (marsRover.getX() > marsRover.getxMinMap()) {
				marsRover.setX(marsRover.getX() - 1);
			} else if (marsRover.getX() == marsRover.getxMinMap()) {
				marsRover.setX(marsRover.getxMaxMap());
			}
		}
	}

	public void moveBack(Direction direction, MarsRoverImp marsRover) {
		if (marsRover.getPosition().getDirection().equals(Direction.NORTH)) {
			if (marsRover.getY() > marsRover.getyMinMap()) {
				marsRover.setY(marsRover.getY() - 1);
			} else if (marsRover.getY() == marsRover.getyMinMap()) {
				marsRover.setY(marsRover.getyMaxMap());
			}
		} else if (direction.equals(Direction.SOUTH)) {
			if (marsRover.getY() < marsRover.getyMaxMap()) {
				marsRover.setY(marsRover.getY() + 1);
			} else if (marsRover.getY() == marsRover.getyMaxMap()) {
				marsRover.setY(marsRover.getyMinMap());
			}
		} else if (direction.equals(Direction.EAST)) {
			if (marsRover.getX() > marsRover.getxMinMap()) {
				marsRover.setX(marsRover.getX() - 1);
			} else if (marsRover.getX() == marsRover.getxMinMap()) {
				marsRover.setX(marsRover.getxMaxMap());
			}
		} else if (direction.equals(Direction.WEST)) {
			if (marsRover.getX() < marsRover.getxMaxMap()) {
				marsRover.setX(marsRover.getX() + 1);
			} else if (marsRover.getX() == marsRover.getxMaxMap()) {
				marsRover.setX(marsRover.getxMinMap());
			}
		}
	}
	
	public void turnRight(Direction direction, MarsRoverImp marsRover) {
		if (direction.equals(Direction.NORTH)) {
			marsRover.setDirection(Direction.EAST);
		} else if (direction.equals(Direction.SOUTH)) {
			marsRover.setDirection(Direction.WEST);
		} else if (direction.equals(Direction.EAST)) {
			marsRover.setDirection(Direction.SOUTH);
		} else if (direction.equals(Direction.WEST)) {
			marsRover.setDirection(Direction.NORTH);
		}
	}
	
	public void turnLeft(Direction direction, MarsRoverImp marsRover) {
		if (direction.equals(Direction.NORTH)) {
			marsRover.setDirection(Direction.WEST);
		} else if (direction.equals(Direction.SOUTH)) {
			marsRover.setDirection(Direction.EAST);
		} else if (direction.equals(Direction.EAST)) {
			marsRover.setDirection(Direction.NORTH);
		} else if (direction.equals(Direction.WEST)) {
			marsRover.setDirection(Direction.SOUTH);
		}
	}
}