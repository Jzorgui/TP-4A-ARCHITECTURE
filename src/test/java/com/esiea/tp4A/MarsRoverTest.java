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

	private MarsRoverImp marsRoverForward=new MarsRoverImp();


	@Test
	void moveForward() {
		String command="f";
		marsRoverForward.move(command);
		
		
		System.out.println("   marsRoverForward   :\n x : "+marsRoverForward.getPosition().getX() +" y : "+ marsRoverForward.getPosition().getY()+" Direction : " 
							+ marsRoverForward.getPosition().getDirection());
		
		
		Assertions.assertThat(marsRoverForward.getPosition())
			.isEqualTo(Position.of(0, 1, Direction.NORTH));
	}
}
 