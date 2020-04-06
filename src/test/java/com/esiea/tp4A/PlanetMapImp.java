package com.esiea.tp4A;

import java.util.HashSet;
import java.util.Set;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

public class PlanetMapImp implements PlanetMap {
	
	private  Set<Position> set = new HashSet<Position>();

	@Override
	public Set<Position> obstaclePositions() {
		Position pos= Position.of(0, 1, Direction.NORTH);
		set.add(Position.of(0, 1, Direction.NORTH));
		return set;
	}
}
