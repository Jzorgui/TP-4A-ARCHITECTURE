package com.esiea.tp4A.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esiea.tp4A.domain.MarsRoverImp;

@Controller
public class SpringController {
	
	private String pAction=null;
	private MarsRoverImp marsRover;

	@GetMapping("/")
	public String getPlayer(@RequestParam(required =false) String playerName,@RequestParam(required =false, defaultValue="") String action,ModelMap map) {
		pAction = action;
		String name = playerName;
		map.put("playerName", name);
		return "pages/home";
	}

}
