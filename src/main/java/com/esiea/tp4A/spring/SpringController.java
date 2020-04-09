package com.esiea.tp4A.spring;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.esiea.tp4A.domain.MarsRoverImp;
import com.esiea.tp4A.domain.Position;

@Controller
public class SpringController {
	
	private String pAction=null;
	private static MarsRoverImp marsRover;
	private static Set<String> setStringPlayer= new HashSet<String>();
	private static Set<MarsRoverImp> setPlayer= new HashSet<MarsRoverImp>();
	private static int x,y;
	private String direction;
	private int randomId = (int) (Math.random() * ( 01 - 1000 ));
	private String randomName= "random"+randomId;
	private String roverName;
	
	

	@GetMapping("/")
	public String getPlayer(@RequestParam(required =false) String playerName,@RequestParam(required =false, defaultValue="") String action,ModelMap map) {
		pAction = action;
		roverName = playerName;	
		
		
		
		if(setStringPlayer.add(roverName)) {
		marsRover = new MarsRoverImp(roverName);
		setPlayer.add(marsRover);
		
		} else {
			for (MarsRoverImp rov :setPlayer) {
				if (rov.getName().equals(roverName)){
					marsRover = rov;
				}
			}
		}
		

		Position pos= Position.of(marsRover.getPosition().getX(),marsRover.getPosition().getY(),marsRover.getPosition().getDirection());
		x=pos.getX();
		y=pos.getY();
		direction=pos.getDirection().toString();
		
		marsRover.move(action);
		
		map.put("playerName", roverName);
		map.put("x",x);
		map.put("y",y);
		map.put("direction",direction);
		return "pages/home";
	}

}
