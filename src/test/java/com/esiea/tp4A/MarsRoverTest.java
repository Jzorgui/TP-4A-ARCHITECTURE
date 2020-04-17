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

	private MarsRoverImp marsRoverForward = new MarsRoverImp();

	@Test
	void moveForward() {
		String command = "f";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverForward.setLocalMap(localMap);
		marsRoverForward.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverForward.GenerateMap(50);

		Assertions.assertThat(marsRoverForward.move(command)).as("moving forward")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}

	private MarsRoverImp marsRoverBack = new MarsRoverImp();

	@Test
	void moveBackward() {
		String command = "b";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverBack.setLocalMap(localMap);
		marsRoverBack.setPosition(Position.of(0, 0, Direction.NORTH));
		marsRoverBack.GenerateMap(50);

		Assertions.assertThat(marsRoverBack.move(command)).as("moving backward")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.NORTH);
	}

	private MarsRoverImp marsRoverEast = new MarsRoverImp();

	@Test
	void changeToEast() {
		String command = "r";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverEast.setLocalMap(localMap);

		Assertions.assertThat(marsRoverEast.move(command)).as("looking to east")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.EAST);
	}

	private MarsRoverImp marsRoverWest = new MarsRoverImp();

	@Test
	void changeToWest() {
		String command = "l";

		// We initialize the Rover for test
		LocalMap localMap = new LocalMap();
		marsRoverWest.setLocalMap(localMap);

		Assertions.assertThat(marsRoverWest.move(command)).as("looking to west")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 0, Direction.WEST);
	}

	private MarsRoverImp marsRoverPlanet = new MarsRoverImp();

	@Test
	void moveForwardOnEndOfGrid() {
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

		Assertions.assertThat(marsRoverPlanet.move(command)).as("going to the end")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -50, Direction.NORTH);
	}

	private MarsRoverImp marsRoverObstacle = new MarsRoverImp();

	@Test
	void moveForwardWithObstacle() {
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

	private MarsRoverImp marsRoverLazer = new MarsRoverImp();

	@Test
	void lazerAttack() {
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

	private MarsRoverImp marsLazerRange = new MarsRoverImp();

	//We verify the value of the Lazer after implementing
	@Test
	void lazerRange() {
		marsLazerRange.configureLaserRange(8);
		assertEquals(marsLazerRange.getLazerRange(), 8);
	}

	// Create a player should be success if name not existing
	LoadPlayer playerSuccess = new LoadPlayer();
	
	@Test
	void isCreatePlayerTrue() {
		LocalMap localMap = new LocalMap();
		assertEquals(playerSuccess.createPlayer("player", localMap), true);
	}

	// Create a player shouldn't be succes if name not existing
	LoadPlayer playerEchec = new LoadPlayer();

	@Test
	void isCreatePlayerFalse() {
		LocalMap localMap = new LocalMap();
		playerEchec.createPlayer("player", localMap);
		assertEquals(playerEchec.createPlayer("player", localMap), false);
	}

	// Loading a player should be success if name already exist
	LoadPlayer playerLoadingTrue = new LoadPlayer();

	@Test
	void isPlayerExistTrue() {

		LocalMap localMap = new LocalMap();
		playerLoadingTrue.createPlayer("player", localMap);
		assertEquals(playerLoadingTrue.loadingPlayer("player", localMap), true);
	}

	// Loading a player shouldn't be success if name already exist
	LoadPlayer playerLoadingFalse = new LoadPlayer();

	@Test
	void isPlayerExistFalse() {

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
    
}

