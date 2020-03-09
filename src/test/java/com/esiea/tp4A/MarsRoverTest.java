package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.Position;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

	private MarsRoverImp marsRoverForward= new MarsRoverImp();
	@Test
	void moveForward() {
		String command="f";
		
		Assertions.assertThat(marsRoverForward.move(command))
			.as("moving forward")
			.extracting(Position::getX,Position::getY,Position::getDirection)
			.containsExactly(0,1,Direction.NORTH);
	}
	
}
 