package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.LoadPlayer;
import com.esiea.tp4A.domain.LocalMap;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.PartySettings;
import com.esiea.tp4A.domain.PlanetMapImp;
import com.esiea.tp4A.domain.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {


	@Test
	void moveForwardwhenNorth() {
		MarsRoverImp marsRoverForward = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverForward.setLocalMap(localMap);
		marsRoverForward.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverForward.GenerateMap(50);
		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward NORTH")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}
	
	@Test
	void moveForwardwhenSouth() {
		MarsRoverImp marsRoverForward = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverForward.setLocalMap(localMap);
		marsRoverForward.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverForward.GenerateMap(50);
		//Turning to SOUTH 
		marsRoverForward.move("r");
		marsRoverForward.move("r");
		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward SOUTH")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.SOUTH);
	}
	
	@Test
	void moveForwardwhenEast() {
		MarsRoverImp marsRoverForward = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverForward.setLocalMap(localMap);
		marsRoverForward.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverForward.GenerateMap(50);
		//Turning to EAST 
		marsRoverForward.move("r");
		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward EAST")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(1, 0, Direction.EAST);
	}
	
	@Test
	void moveForwardwhenWest() {
		MarsRoverImp marsRoverForward = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverForward.setLocalMap(localMap);
		marsRoverForward.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverForward.GenerateMap(50);
		//Turning to WEST 
		marsRoverForward.move("l");
		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward WEST")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(-1, 0, Direction.WEST);
	}

	@Test
	void moveBackwardWhenNorth() {
		MarsRoverImp marsRoverBack = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverBack.setLocalMap(localMap);
		marsRoverBack.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverBack.GenerateMap(50);

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward when NORTH")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.NORTH);
	}
	
	@Test
	void moveBackwardWhenSouth() {
		MarsRoverImp marsRoverBack = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverBack.setLocalMap(localMap);
		marsRoverBack.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverBack.GenerateMap(50);
		//Turning to WEST 
		marsRoverBack.move("l");
		marsRoverBack.move("l");

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward when SOUTH")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.SOUTH);
	}
	
	@Test
	void moveBackwardWhenEast() {
		MarsRoverImp marsRoverBack = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverBack.setLocalMap(localMap);
		marsRoverBack.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverBack.GenerateMap(50);
		//Turning to WEST 
		marsRoverBack.move("r");

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward when EAST")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(-1, 0, Direction.EAST);
	}
	
	@Test
	void moveBackwardWhenWest() {
		MarsRoverImp marsRoverBack = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverBack.setLocalMap(localMap);
		marsRoverBack.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverBack.GenerateMap(50);
		//Turning to WEST 
		marsRoverBack.move("l");

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward when WEST")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(1, 0, Direction.WEST);
	}
	
	@Test
	void changeToEastWhenNorth() {
		MarsRoverImp marsRoverEast = new MarsRoverImp();
		String command = "r";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverEast.setLocalMap(localMap);

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to east when north")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.EAST);
	}
	
	@Test
	void changeToSouthWhenEast() {
		MarsRoverImp marsRoverEast = new MarsRoverImp();
		String command = "r";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverEast.setLocalMap(localMap);
		marsRoverEast.move("r");

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to south when east")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.SOUTH);
	}
	
	@Test
	void changeToWestWhenSouth() {
		MarsRoverImp marsRoverEast = new MarsRoverImp();
		String command = "r";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverEast.setLocalMap(localMap);
		marsRoverEast.move("r");
		marsRoverEast.move("r");

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to west when south")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void changeToNorthWhenWest() {
		MarsRoverImp marsRoverEast = new MarsRoverImp();
		String command = "r";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverEast.setLocalMap(localMap);
		marsRoverEast.move("r");
		marsRoverEast.move("r");
		marsRoverEast.move("r");

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to North when west")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.NORTH);
	}


	@Test
	void changeToWestWhenNorth() {
		MarsRoverImp marsRoverWest = new MarsRoverImp();
		String command = "l";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverWest.setLocalMap(localMap);

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to west when north")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void changeToSouthWhenWest() {
		MarsRoverImp marsRoverWest = new MarsRoverImp();
		String command = "l";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverWest.setLocalMap(localMap);
		marsRoverWest.move(command);

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to west when north")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.SOUTH);
	}
	
	@Test
	void changeToEastWhenSouth() {
		MarsRoverImp marsRoverWest = new MarsRoverImp();
		String command = "l";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverWest.setLocalMap(localMap);
		marsRoverWest.move(command);
		marsRoverWest.move(command);

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to east when south")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.EAST);
	}
	
	@Test
	void changeToNorthWhenEast() {
		MarsRoverImp marsRoverWest = new MarsRoverImp();
		String command = "l";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverWest.setLocalMap(localMap);
		marsRoverWest.move(command);
		marsRoverWest.move(command);
		marsRoverWest.move(command);

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to north when east")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.NORTH);
	}

	@Test
	void moveForwardOnEndOfGridNorthSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end north side ")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -50, Direction.NORTH);
	}
	
	@Test
	void moveForwardOnEndOfGridEastSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("r");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end east side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(-50, 0, Direction.EAST);
	}
	
	@Test
	void moveForwardOnEndOfGridSouthSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("r");
		marsRoverPlanet.move("r");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end south side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 50, Direction.SOUTH);
	}
	
	@Test
	void moveForwardOnEndOfGridWestSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("l");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end south side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(50, 0, Direction.WEST);
	}
	
	@Test
	void moveBackOnEndOfGridSouthSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the back end south side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 50, Direction.NORTH);
	}
	
	@Test
	void moveBackOnEndOfGridWestSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("r");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the back end west side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(50, 0, Direction.EAST);
	}
	
	@Test
	void moveBackOnEndOfGridNorthSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("r");
		marsRoverPlanet.move("r");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the back end north side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -50, Direction.SOUTH);
	}

	@Test
	void moveBackOnEndOfGridEastSide() {
		MarsRoverImp marsRoverPlanet = new MarsRoverImp();
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverPlanet.setLocalMap(localMap);
		marsRoverPlanet.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverPlanet.GenerateMap(100);
		marsRoverPlanet.move("l");

		// We move the rover times before
		for (int i = 0; i < 50; i++) {
			marsRoverPlanet.move(command);
		}

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the back end east side")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(-50, 0, Direction.WEST);
	}
	
	@Test
	void moveForwardWithObstacle() {
		MarsRoverImp marsRoverObstacle = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverObstacle.setLocalMap(localMap);
		marsRoverObstacle.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverObstacle.GenerateMap(100);

		// We initialize an Obstacle just ahead
		PlanetMapImp map = new PlanetMapImp();
		map.setObstacleForTest(0, 1, localMap);

		Assertions.assertThat(marsRoverObstacle.move(command)).as("going through obstacle")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.NORTH);
	}

	@Test
	void lazerAttack() {
		MarsRoverImp marsRoverLazer = new MarsRoverImp();
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverLazer.setLocalMap(localMap);
		marsRoverLazer.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverLazer.GenerateMap(100);

		// We initialize an Obstacle just ahead
		PlanetMapImp map = new PlanetMapImp();
		map.setObstacleForTest(0, 1, localMap);

		marsRoverLazer.configureLaserRange(2);
		marsRoverLazer.move("s");

		Assertions.assertThat(marsRoverLazer.move(command)).as("going through with lazer attack")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}

	//We verify the value of the Lazer after implementing
	@Test
	void lazerRange() {
		MarsRoverImp marsLazerRange = new MarsRoverImp();
		marsLazerRange.configureLaserRange(8);
		assertEquals(marsLazerRange.getLazerRange(), 8);
	}

	// Create a player should be success if name not existing

	@Test
	void isCreatePlayerTrue() {	
		LoadPlayer playerSuccess = new LoadPlayer();
		LocalMap localMap = new LocalMap();
		assertEquals(playerSuccess.createPlayer("player", localMap), true);
	}

	// Create a player shouldn't be succes if name not existing
	@Test
	void isCreatePlayerFalse() {
		LoadPlayer playerEchec = new LoadPlayer();
		LocalMap localMap = new LocalMap();
		playerEchec.createPlayer("player", localMap);
		assertEquals(playerEchec.createPlayer("player", localMap), false);
	}

	// Loading a player should be success if name already exist
	@Test
	void isPlayerExistTrue() {
		LoadPlayer playerLoadingTrue = new LoadPlayer();
		LocalMap localMap = new LocalMap();
		playerLoadingTrue.createPlayer("player", localMap);
		assertEquals(playerLoadingTrue.loadingPlayer("player", localMap), true);
	}

	// Loading a player shouldn't be success if name already exist
	@Test
	void isPlayerExistFalse() {
		LoadPlayer playerLoadingFalse = new LoadPlayer();

		LocalMap localMap = new LocalMap();
		assertEquals(playerLoadingFalse.loadingPlayer("player", localMap), false);
	}

	//Test place is occupated should be true if something is in x,y
	@Test
    void isPlaceOccupatedTrue() {
	    LocalMap localMap = new LocalMap();
	    localMap.fillListObstacle(1,1);
	    assertEquals(localMap.isPlaceOccupated(1,1, null), true);
    }

    //Test place is occupated should be false if something is in x,y
    @Test
    void isPlaceOccupatedFalse() {
        LocalMap localMap = new LocalMap();
        assertEquals(localMap.isPlaceOccupated(1,1, null), false);
    }
    
    // Test that we do not return place is occupated if it's occupated but by a dead rover
    @Test 
    void deadRoverPlaceNotOccupated() {
    	LocalMap localMap = new LocalMap();
    	MarsRoverImp marsRover = new MarsRoverImp();
    	marsRover.setStatus(false);
    	marsRover.setX(1);
    	marsRover.setY(1);
    	marsRover.setName("");
    	localMap.fillListRover(marsRover);
    	assertEquals(localMap.isPlaceOccupated(1, 1, " "), false);
    }
    
    //Verify if the size map is in {10, 30, 60}
    @Test
    void mapSize() {
    	PartySettings party = new PartySettings();
    	party.generateSizeMap();
    	Assertions.assertThat(party.generateSizeMap()).isIn(10, 30, 60);
    	
    }
    
    //Verify if the lazer range is in {5,30,10 000}
    @Test
    void assertLazerRange() {
    	PartySettings party = new PartySettings();
    	party.generateSizeMap();
    	Assertions.assertThat(party.setLazerRange()).isIn(5, 30, 10000);
    	
    }

    //Test that a name is not available
    @Test
    void roverNameNotAvailable() {
    	LocalMap localMap = new LocalMap();
    	LoadPlayer load = new LoadPlayer();
    	load.createPlayer("player", localMap);
    	assertEquals(load.createPlayer("player",localMap), false);
    }
    
    //Test filling LocalMap fillListRover function
    @Test 
    void isFillListRoverTrue() {
    	LocalMap localMap = new LocalMap();
    	MarsRoverImp marsRover = new MarsRoverImp("player");
    	marsRover.setX(1);
    	marsRover.setY(1);
    	marsRover.setDirection(Direction.NORTH);
    	marsRover.setStatus(true);
    	assertEquals(localMap.fillListRover(marsRover), true);
    }
    
    //Test filling LocalMap fillListRover function should return false is place is occupated
    @Test 
    void isFillListRoverFalse	() {
    	LocalMap localMap = new LocalMap();
    	MarsRoverImp marsRover = new MarsRoverImp("player");
    	marsRover.setX(1);
    	marsRover.setY(1);
    	marsRover.setDirection(Direction.NORTH);
    	marsRover.setStatus(true);
    	
    	//Obstacle at the rover place 
    	localMap.fillListObstacle(1, 1);
    	
    	assertEquals(localMap.fillListRover(marsRover), false);
    }
    
    
  //Test filling LocalMap fillListRover function should return false if there is already 50 players
    @Test 
    void isFillListRoverFull() {
    	LocalMap localMap = new LocalMap();
    	MarsRoverImp marsRover = new MarsRoverImp("player");
    	marsRover.setX(1);
    	marsRover.setY(1);
    	marsRover.setDirection(Direction.NORTH);
    	marsRover.setStatus(true);
    	
    	//We create 50 players and fill list obstacle with
    	for (int i=0; i<50; i++) {
    		String name = "player"+i;
    		MarsRoverImp rover = new MarsRoverImp(name);
    		rover.setX(i);
    		rover.setY(i-1);
    		rover.setDirection(Direction.NORTH);
    		rover.setStatus(true);
    		
    		localMap.fillListRover(rover);
    	}
    	assertEquals(localMap.fillListRover(marsRover), false);
    }
    
    //Test LocalMap fillListObstacle
    void isFillListObstacleTrue() {
    	LocalMap localMap = new LocalMap();
    	assertEquals(localMap.fillListObstacle(1, 1), true);
    }
    
  //Test LocalMap fillListObstacle should return false if place is already occupated
    void isFillListObstacleFalse() {
    	LocalMap localMap = new LocalMap();
    	localMap.fillListObstacle(1, 1);
    	assertEquals(localMap.fillListObstacle(1, 1), false);
    }
}

