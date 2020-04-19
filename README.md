# TP-4A-ARCHITECTURE


[![Build Status](https://travis-ci.com/Jzorgui/TP-4A-ARCHITECTURE.svg?branch=master)](https://travis-ci.com/Jzorgui/TP-4A-ARCHITECTURE)

[![codecov](https://codecov.io/gh/Jzorgui/TP-4A-ARCHITECTURE/branch/master/graph/badge.svg)](https://codecov.io/gh/Jzorgui/TP-4A-ARCHITECTURE)

Online game : https://playrover.cfapps.io/

The game : 

You are a MarsRover but on your planet there is other rovers who want to kill you, so kill them first !

How to play ? 
First give us your playerName
Press f to go forward
Press b to go back
Press l to turn left
Press r to turn right
Press s to fire 

If you die you will be desactivated and will not be able to move.

To start the game localy, start the server and go on http://localhost:8080/
You will see a graphic interface of the game.
Other endpoint available :
First create a player by sending a POST request on http://localhost:8080/api/player/{yourPlayerName}
Then you will be able to retrieving your player data by sending a GET request on same endpoint.
Finally, you will be able to move your player by sending a PATCH request on http://localhost:8080/api/player/{yourPlayerName}/{command}
All of those request's answers are in JSON format.


What is used?

For this project we used JAVA 1.8
Maven 3.6.3
SpringFramework 2.2.2 RELEASE (server part)
Junit (test part)
Pivotal Cloud Foundry (To put the game online)


