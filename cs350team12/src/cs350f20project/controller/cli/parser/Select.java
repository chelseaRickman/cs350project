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
			return tokens.InvalidToken();
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
		return tokens.InvalidToken();
	}
	
	public A_Command drawbridge() {
		return null;
	}
	
	//MOVED
	public A_Command roundhouse() {
		String id = tokens.getNext();
		if(!Checks.checkID(id)) {
			return tokens.InvalidToken();
		}
		
		String positionText = tokens.getNext(); // "POSITION"
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

	//MOVED
	public A_Command instructSwitch() {
		String id = tokens.getNext();
		if(!Checks.checkID(id)) {
			return tokens.InvalidToken();
		}
		
		String pathText = tokens.getNext(); // "PATH"
		
		boolean isPrimary = false;
		String primaryOrSecondary = tokens.getNext();
		if(primaryOrSecondary.equalsIgnoreCase("PRIMARY")) {
			isPrimary = true;
		}
		
		return new CommandBehavioralSelectSwitch(id, isPrimary);
	}
	
}

