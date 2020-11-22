package cs350f20project.controller.cli.parser;

import java.util.ArrayList;
import java.util.List;

import cs350f20project.controller.ActionProcessor;
import cs350f20project.controller.Controller;
import cs350f20project.controller.cli.CommandLineInterface;
import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.creational.CommandCreatePowerCatenary;
import cs350f20project.controller.command.creational.CommandCreatePowerPole;
import cs350f20project.controller.command.creational.CommandCreatePowerStation;
import cs350f20project.datatype.CoordinatesDelta;
import cs350f20project.datatype.CoordinatesWorld;
import cs350f20project.datatype.Latitude;
import cs350f20project.datatype.Longitude;

/*
22 CREATE POWER CATENARY id1 WITH POLES idn+
23 CREATE POWER POLE id1 ON TRACK id2 DISTANCE number FROM ( START | END )
24 CREATE POWER STATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH ( SUBSTATION | SUBSTATIONS )idn+
25 CREATE POWER SUBSTATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH CATENARIES idn+
 */
public class Power extends ParserBase {
	public Power(Tokenizer tokens) {
		super(tokens);
	}
	
	public A_Command parse(){
		String token = tokens.getNext();
		if(token == null)
			return tokens.invalidToken();
		if(token.equalsIgnoreCase("CATENARY"))
			return catenary();
		if(token.equalsIgnoreCase("POLE")) 
			return pole();
		if(token.equalsIgnoreCase("STATION")) 
			return station();
		if(token.equalsIgnoreCase("SUBSTATION"))
			return substation();
		
		return checkArgs(token);
	}
	
	public A_Command catenary() {
		String id = tokens.getNext();
		if(!Checks.checkID(id, false)) {
			return tokens.invalidToken();
		}
		
		List<String> poleIds = new ArrayList<String>();
		
		if(!tokens.getNext().equalsIgnoreCase("WITH"))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("POLES"))
			return tokens.invalidToken();
		
		String currentPoleId = tokens.getNext();
		if(currentPoleId == null)
			return tokens.invalidToken();
		
		while(currentPoleId != null) {
			if(!Checks.checkID(currentPoleId, false))
				return tokens.invalidToken();
			
			poleIds.add(currentPoleId);
			currentPoleId = tokens.getNext();
		}
		
		return new CommandCreatePowerCatenary(id, poleIds);
	}
	
	public A_Command pole() {
		String poleId = tokens.getNext();
		if(!Checks.checkID(poleId, false))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("ON"))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("TRACK"))
			return tokens.invalidToken();
		
		String trackId = tokens.getNext();
		if(!Checks.checkID(trackId, false))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("DISTANCE"))
			return tokens.invalidToken();
		
		String distanceFromString = tokens.getNext();
		if(distanceFromString == null)
			return tokens.invalidToken();
		Double distanceFrom = Double.parseDouble(distanceFromString);
		// check number?
		
		if(!tokens.getNext().equalsIgnoreCase("FROM"))
			return tokens.invalidToken();
	
		boolean isFromStart = false;
		String startOrEnd = tokens.getNext();
		if(startOrEnd == null)
			return tokens.invalidToken();
		if(startOrEnd.equalsIgnoreCase("START"))
			isFromStart = true;
		
		return new CommandCreatePowerPole(poleId, new TrackLocator(trackId, distanceFrom, isFromStart));
	}
	
	public A_Command station() {
		return null;
	}
	
	public A_Command substation() {
		CoordinatesWorld coords = new CoordinatesWorld(new Latitude(0.0), new Longitude(0.0));
		CoordinatesDelta deltas = new CoordinatesDelta(0.0, 0.0);
		String id1 = tokens.getNext();
		if(!Checks.checkID(id1, false))
			return tokens.invalidToken();
		
		String token = tokens.getNext();
		while(!token.equalsIgnoreCase("DELTA")) {
			verifyArg(token);
			token = tokens.getNext();
		}
		coords = Checks.parseCoordinatesWorld(tokens.getArgs(0), true);
		//progress to another argumentlist for the delta
		tokens.nextArgList();
		while(!token.equalsIgnoreCase("WITH")){
			verifyArg(token);
			token = tokens.getNext();
		}
		deltas = Checks.parseCoordinatesDelta(tokens.getArgs(1));
		token = tokens.getNext();
		if(token.equalsIgnoreCase("CATENARIES"))
			return tokens.invalidToken();
		token = tokens.getNext();
		ArrayList<String> ids = new ArrayList<String>();
		while(token != null) {
			if(Checks.checkID(token, false)) {
				ids.add(token);
				token = tokens.getNext();
			}
		}
		if(ids.size()==0)
			return tokens.invalidToken();
		return new CommandCreatePowerStation(id1, coords, deltas, ids);
		
	}

}
