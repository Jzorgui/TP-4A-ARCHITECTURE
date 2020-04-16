package com.esiea.tp4A.spring;

import org.json.JSONArray;

import org.json.JSONObject;

import com.esiea.tp4A.domain.LocalMap;
import com.esiea.tp4A.domain.MarsRoverImp;

public class JSONCreator {

	public JSONObject returnJson(MarsRoverImp marsRover, LocalMap localMap) throws Exception {

		JSONObject localJson = this.localMapJson(marsRover, localMap);
		JSONObject playerJson = this.playerJson(marsRover);

		// Final Json
		JSONObject finalJSON = new JSONObject();
		finalJSON.put("player", playerJson);
		finalJSON.put("localmap", localJson);

		return finalJSON;
	}

	public JSONObject playerJson(MarsRoverImp marsRover) {
		JSONObject jsonPlayer = new JSONObject();
		jsonPlayer.put("name", marsRover.getName());
		jsonPlayer.put("status", marsRover.getStatus());
		jsonPlayer.put("lazerRange", marsRover.getLazerRange());
		jsonPlayer.put("position", this.playerPositionJson(marsRover));
		return jsonPlayer;
	}
	
	public JSONObject playerPositionJson(MarsRoverImp marsRover) {
		JSONObject jsonPosition = new JSONObject();
		jsonPosition.put("x", marsRover.getX());
		jsonPosition.put("y", marsRover.getY());
		jsonPosition.put("direction", marsRover.getPosition().getDirection());
		return jsonPosition;
	}

	public JSONObject localMapJson(MarsRoverImp marsRover,LocalMap localMap) {
		JSONArray arrayJsonEnnemies = new JSONArray();
		arrayJsonEnnemies = marsRover.radarEnnemie(localMap);
		JSONArray arrayJsonObstacle = new JSONArray();
		arrayJsonObstacle = marsRover.radarObstacle(localMap);
		JSONObject localJson = new JSONObject();
		localJson.put("Ennemies", arrayJsonEnnemies);
		localJson.put("Obstacles", arrayJsonObstacle);
		localJson.put("size", marsRover.getxMaxMap() * 2);
		return localJson;
	}

}