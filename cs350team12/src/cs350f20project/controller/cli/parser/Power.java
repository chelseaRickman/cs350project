package cs350f20project.controller.cli.parser;

import java.util.ArrayList;
import java.util.List;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;
import cs350f20project.controller.command.creational.CommandCreatePowerCatenary;

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
		return null;
	}
	
	public A_Command station() {
		return null;
	}
	
	public A_Command substation() {
		return null;
	}

}
