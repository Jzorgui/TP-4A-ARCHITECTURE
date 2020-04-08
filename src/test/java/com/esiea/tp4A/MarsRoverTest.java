package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.PlanetMapImp;
import com.esiea.tp4A.domain.Position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {

	private MarsRoverImp marsRoverForward = new MarsRoverImp();

	@Test
	void moveForward() {
		String command = "f";

		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}

	private MarsRoverImp marsRoverBack = new MarsRoverImp();

	@Test
	void moveBackward() {
		String command = "b";

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.NORTH);
	}

	private MarsRoverImp marsRoverEast = new MarsRoverImp();

	@Test
	void changeToEast() {
		String command = "r";

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to east")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.EAST);
	}

	private MarsRoverImp marsRoverWest = new MarsRoverImp();

	@Test
	void changeToWest() {
		String command = "l";

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to west")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.WEST);
	}

	private MarsRoverImp marsRoverPlanet = new MarsRoverImp();

	@Test
	void moveForwardOnEndOfGrid() {
		String command = "f";

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -49, Direction.NORTH);
	}

	private MarsRoverImp marsRoverObstacle = new MarsRoverImp();

	@Test
	void moveForwardWithObstacle() {
		String command = "f";

		PlanetMapImp map = new PlanetMapImp();
		marsRoverObstacle.updateMap(map);

		Assertions.assertThat(marsRoverObstacle.move(command)).as("going through obstacle")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.NORTH);
	}

	   private MarsRoverImp marsRoverLazer=new MarsRoverImp();
	    @Test
	    void lazerAttack() {
	        String command="f";
	        
	        PlanetMapImp map = new PlanetMapImp();
	        marsRoverLazer.updateMap(map);
	        
	        marsRoverLazer.configureLaserRange(2);
	        marsRoverLazer.move("s");

	 

	        Assertions.assertThat(marsRoverLazer.move(command))
	            .as("going through with lazer attack")
	            .extracting(Position::getX,Position::getY,Position::getDirection)
	            .containsExactly(0, 1, Direction.NORTH);
	    }
}
