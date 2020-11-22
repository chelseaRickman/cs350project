package cs350f20project.controller.cli.parser;

import java.util.ArrayList;
import java.util.List;

import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.creational.CommandCreatePowerCatenary;
import cs350f20project.controller.command.creational.CommandCreatePowerPole;

public class Power extends ParserBase {
	public Power(Tokenizer tokens) {
		super(tokens);
	}
	
	public A_Command parse(){
		String token = tokens.getNext();
		if(token == null)
			return tokens.InvalidToken();
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
			return tokens.InvalidToken();
		}
		
		List<String> poleIds = new ArrayList<String>();
		
		String withText = tokens.getNext(); // "WITH"
		if(!withText.equalsIgnoreCase("WITH"))
			return tokens.InvalidToken();
		
		String polesText = tokens.getNext(); // "POLES"
		if(!polesText.equalsIgnoreCase("POLES"))
			return tokens.InvalidToken();
		
		String currentPoleId = tokens.getNext();
		if(currentPoleId == null)
			return tokens.InvalidToken();
		
		while(currentPoleId != null) {
			if(!Checks.checkID(currentPoleId))
				return tokens.InvalidToken();
			
			poleIds.add(currentPoleId);
			currentPoleId = tokens.getNext();
		}
		
		return new CommandCreatePowerCatenary(id, poleIds);
	}
	
	public A_Command pole() {
		String poleId = tokens.getNext();
		if(!Checks.checkID(poleId))
			return tokens.InvalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("ON"))
			return tokens.InvalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("TRACK"))
			return tokens.InvalidToken();
		
		String trackId = tokens.getNext();
		if(!Checks.checkID(trackId))
			return tokens.InvalidToken();
		
		if(!tokens.getNext().equalsIgnoreCase("DISTANCE"))
			return tokens.InvalidToken();
		
		String distanceFromString = tokens.getNext();
		if(distanceFromString == null)
			return tokens.InvalidToken();
		Double distanceFrom = Double.parseDouble(distanceFromString);
		// check number?
		
		if(!tokens.getNext().equalsIgnoreCase("FROM"))
			return tokens.InvalidToken();
	
		boolean isFromStart = false;
		String startOrEnd = tokens.getNext();
		if(startOrEnd == null)
			return tokens.InvalidToken();
		if(startOrEnd.equalsIgnoreCase("START"))
			isFromStart = true;
		
		return new CommandCreatePowerPole(poleId, new TrackLocator(trackId, distanceFrom, isFromStart));
	}
	
	public A_Command station() {
		return null;
	}
	
	public A_Command substation() {
		return null;
	}

}
