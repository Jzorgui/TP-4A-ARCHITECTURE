package com.esiea.tp4A.spring;

import org.json.JSONArray;
import org.json.JSONObject;

import com.esiea.tp4A.domain.LocalMap;
import com.esiea.tp4A.domain.MarsRoverImp;

public class JSONCreator {
	
	public static  JSONObject returnJson(LocalMap localMap, MarsRoverImp marsRover) throws Exception {

		//Building ennemies json part
		JSONArray arrayJsonEnnemies = new JSONArray();
		arrayJsonEnnemies = marsRover.radarEnnemie(localMap);
		
		//Building obstacle json part
		JSONArray arrayJsonObstacle = new JSONArray();
		arrayJsonObstacle = marsRover.radarObstacle(localMap);
		
		//JSON Local-map
		JSONObject localJson = new JSONObject();
		localJson.put("Ennemies", arrayJsonEnnemies);
		localJson.put("Obstacles", arrayJsonObstacle);
		localJson.put("size", marsRover.getxMaxMap()*2);

		//Building current Player json 
		JSONObject jsonPlayer = new JSONObject();
		JSONObject jsonPosition = new JSONObject();
		
		jsonPosition.put("x", marsRover.getX());
		jsonPosition.put("y", marsRover.getY());
		jsonPosition.put("direction", marsRover.getPosition().getDirection());
				
		jsonPlayer.put("name",marsRover.getName());
		jsonPlayer.put("status", marsRover.getStatus());
		jsonPlayer.put("lazerRange", marsRover.getLazerRange());
		jsonPlayer.put("position", jsonPosition);

		jsonPosition.put("x", marsRover.getX());
		jsonPosition.put("y", marsRover.getY());
		jsonPosition.put("direction", marsRover.getPosition().getDirection());
		
		jsonPlayer.put("name",marsRover.getName());
		jsonPlayer.put("status", marsRover.getStatus());
		jsonPlayer.put("lazerRange", marsRover.getLazerRange());
		jsonPlayer.put("position", jsonPosition);
		
		//Final Json
		JSONObject finalJSON = new JSONObject();
		finalJSON.put("player",jsonPlayer);
		finalJSON.put("localmap", localJson);

		return finalJSON;
	}

}