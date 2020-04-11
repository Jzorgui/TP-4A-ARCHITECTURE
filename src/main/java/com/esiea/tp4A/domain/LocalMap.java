package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class LocalMap {

	private Set<Position> setPos = new HashSet<Position>();
	private Set<MarsRoverImp> setRover = new HashSet<MarsRoverImp>();

	public boolean fillListObstacle(int x, int y) {

		boolean ret = isPlaceOccupated(x, y) ? false : true;

		if (ret) {
			setPos.add(Position.of(x, y, Direction.NORTH));
		}
		return ret;
	}

	public boolean fillListRover(MarsRoverImp rover) {
		int x = rover.getX();
		int y = rover.getY();

		if (isPlaceOccupated(x,y)) {
			return false;
		} else if (!isPlaceOccupated(x,y)) {
			if (setRover.size() > 5) {
				return false;
			}
			setRover.add(rover);
			return true;
		}
		return false;
	}

	public boolean isPlaceOccupated(int x, int y) {
		
		boolean bReturn=false;

		for (Position pos : setPos) {
			if (pos.getX() == x) {
				if (pos.getY() == y) {
					bReturn=true;
				}
			}
		}

		for (MarsRoverImp rov : setRover) {
			if (rov.getX() == x) {
				if (rov.getY() == y) {
					bReturn=true;
				}
			}
		}
		return bReturn;
	}

	public void afficher() {
		for (MarsRoverImp rov : setRover) {
			System.out.println("rover name : " + rov.getName() + " x : " + rov.getX() + " y : " + rov.getY());
		}
	}
	public void afficherObstacle() {
		for (Position pos : setPos) {
			System.out.println(" x : " + pos.getX() + " y : " + pos.getY());
		}
	}

	public Set<Position> getSetPos() {
		return setPos;
	}

	public Set<MarsRoverImp> getSetRover() {
		return setRover;
	}

}
