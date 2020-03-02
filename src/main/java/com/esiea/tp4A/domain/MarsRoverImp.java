package com.esiea.tp4A.domain;

public class MarsRoverImp implements MarsRover {

	private Position position = Position.of(0, 0, Direction.NORTH);
	private Direction direction=position.getDirection();
	private int x=position.getX();
	private int y=position.getY();

	public Position getPosition() {
		return position;
	}
	
	@Override
	public Position move(String command) {
		switch(command){
			case "f":
				if (direction.equals("NORTH")) {
					this.y += 1;
				}
				if (direction.equals("SOUTH")) {
					this.y -= 1;
				}
				if (direction.equals("EAST")) {
					this.x += 1;
				}if (direction.equals("WEST")) {
					this.x -= 1;
				}
				break;
			case "b":
			
				break;
			case "l":
			
				break;
			case "r":
			
				break;
				
		}
		return Position.of(x, y, Direction.NORTH);
	}
}
