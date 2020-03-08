package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.Position;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

	private MarsRoverImp marsrover=new MarsRoverImp();


	@Test
	void moveForward() {
		String command="f";
		marsrover.move(command);
		
		
		System.out.println(" x : "+marsrover.getPosition().getX() +" y : "+ marsrover.getPosition().getY()+" Direction : " 
							+ marsrover.getPosition().getDirection());
		
		
		Assertions.assertThat(marsrover.getPosition())
			.isEqualTo(Position.of(0, 1, Direction.NORTH));
	}
	

}
 