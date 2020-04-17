package com.esiea.tp4A.domain;

public class MarsRoverFunction {
	public boolean lazerAttak(Direction direction, LocalMap localMap, int lazerRange, int x, int y) {
		System.out.println(direction);
		switch (direction) {
			case NORTH:
				for (int i = y + 1; i <= y + lazerRange; i++) {
					for (Position pos : localMap.getSetPos()) {
						if (pos.getX() == x && pos.getY() == i) {
							localMap.getSetPos().remove(pos);
							return true;
						}
					}
					for (MarsRoverImp rov : localMap.getSetRover()) {
						if (rov.getX() == x && rov.getY() == i && rov.getStatus() == true) {
							rov.setStatus(false);
							return true;
						}
					}
				}
				break;
			case SOUTH:
				for (int i = y - 1; i >= y - lazerRange; i--) {
					for (Position pos : localMap.getSetPos()) {
						if (pos.getX() == x && pos.getY() == i) {
							localMap.getSetPos().remove(pos);
							return true;
						}
					}
					for (MarsRoverImp rov : localMap.getSetRover()) {
						if (rov.getX() == x && rov.getY() == i && rov.getStatus() == true) {
							rov.setStatus(false);
							return true;
						}
					}
				}
				break;
			case WEST:
				for (int i = x - 1; i >= x - lazerRange; i--) {
					for (Position pos : localMap.getSetPos()) {
						if (pos.getX() == i && pos.getY() == y) {
							localMap.getSetPos().remove(pos);
							return true;
						}
					}
					for (MarsRoverImp rov : localMap.getSetRover()) {
						if (rov.getX() == i && rov.getY() == y  && rov.getStatus() == true) {
							rov.setStatus(false);
							return true;
						}
					}
				}
				break;
			case EAST:
				for (int i = x + 1; i <= x + lazerRange; i++) {
					for (Position pos : localMap.getSetPos()) {
						if (pos.getX() == i && pos.getY() == y) {
							localMap.getSetPos().remove(pos);
							return true;
						}
					}
					for (MarsRoverImp rov : localMap.getSetRover()) {
						if (rov.getX() == i && rov.getY() == y  && rov.getStatus() == true) {
							rov.setStatus(false);
							return true;
						}
					}
				}
				break;
		}
		return false;
	}
	
}
