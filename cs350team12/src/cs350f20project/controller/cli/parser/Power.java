package cs350f20project.controller.cli.parser;

import java.util.ArrayList;
import java.util.List;

import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.creational.CommandCreatePowerCatenary;
import cs350f20project.controller.command.creational.CommandCreatePowerPole;

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
		if(!Checks.checkID(id)) {
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
			if(!Checks.checkID(currentPoleId))
				return tokens.invalidToken();
			
			poleIds.add(currentPoleId);
			currentPoleId = tokens.getNext();
		}
		
		return new CommandCreatePowerCatenary(id, poleIds);
	}
	
	public A_Command pole() {
		String poleId = tokens.getNext();
		if(!Checks.checkID(poleId))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("ON"))
			return tokens.invalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("TRACK"))
			return tokens.invalidToken();
		
		String trackId = tokens.getNext();
		if(!Checks.checkID(trackId))
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
		String id1 = tokens.getNext();
		if(!Checks.checkID(id1))
			return tokens.invalidToken();
		return null;
		
	}

}
