package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImp implements PlanetMap {

	private Set<Position> set = new HashSet<Position>();
	
	@Override
	public Set<Position> obstaclePositions() {
		set.add(Position.of(0, 1, Direction.NORTH));
		return set;
	}
}
