package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class LocalMap {

	private Set<Position> setPos = new HashSet<Position>();
	private Set<MarsRoverImp> setRover = new HashSet<MarsRoverImp>();

	public boolean fillListObstacle(int x, int y) {

		boolean ret = isPlaceOccupated(x, y, "") ? false : true;

		if (ret) {
			setPos.add(Position.of(x, y, Direction.NORTH));
		}
		return ret;
	}

	public boolean fillListRover(MarsRoverImp rover) {
		int x = rover.getX();
		int y = rover.getY();

		if (isPlaceOccupated(x, y, "")) {
			return false;
		} else  {
			if (setRover.size() > 50) {
				return false;
			}
			setRover.add(rover);
			return true;
		}
	}

	public boolean isPlaceOccupated(int x, int y, String name) {
		for (Position pos : setPos) {
			if (pos.getX() == x && pos.getY() == y) {
				return true;
			}
		}
		for (MarsRoverImp rov : setRover) {
			if (rov.getX() == x && rov.getY() == y && !rov.getName().equals(name) && rov.getStatus()==true) {
				return true;
			}
		}
		return false;
	}

	public Set<Position> getSetPos() {
		return setPos;
	}

	public Set<MarsRoverImp> getSetRover() {
		return setRover;
	}

}
