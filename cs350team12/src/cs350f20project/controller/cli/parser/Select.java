package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectRoundhouse;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectSwitch;
import cs350f20project.datatype.Angle;
/*
6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE )
8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY )
 */

public class Select extends ParserBase{
	
	public Select(Tokenizer tokens) {
		super(tokens);
	}

	public A_Command parse() {
		String token = tokens.getNext();
		if(token == null)
			return tokens.invalidToken();
		if(token.equalsIgnoreCase("DRAWBRIDGE")){
			//Stuff
		}
		if(token.equalsIgnoreCase("ROUNDHOUSE")) {
			return roundhouse();
		}
		if(token.equalsIgnoreCase("SWITCH")) {
			return instructSwitch();
		}
		if(verifyArg(token) == true) {
			return parse();
		}
		return tokens.invalidToken();
	}
	
	public A_Command drawbridge() {
		return null;
	}
	
	public A_Command roundhouse() {
		String id = tokens.getNext();
		if(!Checks.checkID(id)) {
			return tokens.invalidToken();
		}
		
		if(!tokens.getNext().equalsIgnoreCase("POSITION"))
			return tokens.invalidToken();
		Angle angle = new Angle(Double.parseDouble(tokens.getNext()));
		// Check for valid angle?
		
		boolean isClockwise = false;
		String direction = tokens.getNext();
		// check valid direction?
		if(direction.equalsIgnoreCase("CLOCKWISE")) {
			isClockwise = true;
		}
		
		return new CommandBehavioralSelectRoundhouse(id, angle, isClockwise);
	}

	public A_Command instructSwitch() {
		String id = tokens.getNext();
		if(!Checks.checkID(id)) {
			return tokens.invalidToken();
		}
		
		if(!tokens.getNext().equalsIgnoreCase("PATH"))
			return tokens.invalidToken();
		boolean isPrimary = false;
		String primaryOrSecondary = tokens.getNext();
		if(primaryOrSecondary.equalsIgnoreCase("PRIMARY")) {
			isPrimary = true;
		}
		
		return new CommandBehavioralSelectSwitch(id, isPrimary);
	}
	
}

