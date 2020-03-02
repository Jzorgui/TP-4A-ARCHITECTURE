package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.Position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

	private MarsRoverImp marsrover=new MarsRoverImp();

	private MarsRover rover;

	@Test
	void moveForward() {
		
		Assertions.assertThat(marsrover.getPosition())
			.isEqualTo(Position.of(0, 1, Direction.NORTH));
	}

}
 