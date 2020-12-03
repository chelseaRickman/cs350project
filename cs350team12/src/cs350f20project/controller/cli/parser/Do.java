package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectBridge;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectRoundhouse;
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectSwitch;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetDirection;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetReference;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetSpeed;
import cs350f20project.datatype.Angle;

/*
This class handles all the DO commands
2  DO BRAKE id
6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE )
8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY )
11 DO SET id DIRECTION ( FORWARD | BACKWARD )
12 DO SET REFERENCE ENGINE id
15 DO SET id SPEED number
 */

public class Do extends ParserBase{
	public Do(Tokenizer tokens) {
		super(tokens);
	}
	
	public A_Command parse(){
		String token = tokens.getNext();
		if(token == null)
			return tokens.invalidToken();
		if(token.equalsIgnoreCase("BRAKE"))
			return brake(tokens);
		if(token.equalsIgnoreCase("SELECT")) {
			return doSelect();
		}
		if(token.equalsIgnoreCase("SET")) {
			return doSet();
		}
		return checkArgs(token);
	}
	
	// Determines which SELECT method needs to be called
	public A_Command doSelect() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.invalidToken();
		else if(nextToken.equalsIgnoreCase("DRAWBRIDGE"))
			return selectDrawbridge();
		else if(nextToken.equalsIgnoreCase("ROUNDHOUSE"))
			return selectRoundhouse();
		else if(nextToken.equalsIgnoreCase("SWITCH"))
			return selectSwitch();
		
		return tokens.invalidToken();
	}
	
	// Determines which SET method needs to be called
	public A_Command doSet() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.invalidToken();
		if(nextToken.equalsIgnoreCase("REFERENCE"))
			return setReference();
		else {
			return setId(nextToken);
		}
	}
	
	
	public A_Command brake(Tokenizer tokens) {
		// 2  DO BRAKE id
		String token = tokens.getNext();
		if(!Checks.checkID(token, false)) {
			return tokens.invalidToken();
		}
		return new CommandBehavioralBrake(token);
	}

	// DO SELECT commands
	
	private A_Command selectDrawbridge() {
		// 6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
		// When entering this method, tokens.getNext() should be id
		if(tokens.size() > 6) return tokens.invalidToken();
		String id = tokens.get(3);
		if(!Checks.checkID(id, false)) return tokens.invalidToken();
		if(!tokens.get(4).equalsIgnoreCase("POSITION")) return tokens.invalidToken();
		return new CommandBehavioralSelectBridge(id, Checks.booleanFromString(tokens.get(5), "UP", "DOWN"));
	}
	
	private A_Command selectRoundhouse() {
		// 7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE )
		// When entering this method, tokens.getNext() should be id
		String id = tokens.get(3);
		if(!Checks.checkID(id, false)) {
			return tokens.invalidToken();
		}
		
		String stringAngle = tokens.get(5);
		if(!Checks.checkStringIsDouble(stringAngle)) {
			return tokens.invalidToken();
		}
		Angle angle = new Angle(Double.parseDouble(stringAngle));

		boolean isClockwise = false;
		String direction = tokens.get(6);
		if(!Checks.checkStringIsOneOfTheseValues(direction, new String[] {"CLOCKWISE", "COUNTERCLOCKWISE"})) {
			return tokens.invalidToken();
		}
		
		if(direction.equalsIgnoreCase("CLOCKWISE")) {
			isClockwise = true;
		}
		
		return new CommandBehavioralSelectRoundhouse(id, angle, isClockwise);
	}
	
	private A_Command selectSwitch() {
		// 8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY )
		// When entering this method, tokens.getNext() should be id
		
		//id
		String id = tokens.getNext(); 
		if(!Checks.checkID(id, false)) {
			return tokens.invalidToken();
		}
		
		//PATH
		if(!tokens.getNext().equalsIgnoreCase("PATH")) { 
			return tokens.invalidToken();
		}
		
		// (PRIMARY | SECONDARY)
		boolean isPrimary = false;
		String primaryOrSecondary = tokens.getNext(); 
		if(!Checks.checkStringIsOneOfTheseValues(primaryOrSecondary, new String[] {"PRIMARY", "SECONDARY"})) {
			return tokens.invalidToken();
		}
		
		if(primaryOrSecondary.equalsIgnoreCase("PRIMARY")) {
			isPrimary = true;
		}
		
		return new CommandBehavioralSelectSwitch(id, isPrimary);
	}
	
	// DO SET commands
	
	private A_Command setReference() {
		// 12 DO SET REFERENCE ENGINE id
		// When entering this method, tokens.getNext() should be "ENGINE"	
		String id = tokens.get(4);
		if(!Checks.checkID(id, false)) {
			return tokens.invalidToken();
		}
		
		return new CommandBehavioralSetReference(id);
	}
	
	private A_Command setId(String id) {
		// 11 DO SET id DIRECTION ( FORWARD | BACKWARD )
		//15 DO SET id SPEED number
		if(!Checks.checkID(id, false)) {
			return tokens.invalidToken();
		}
		
		//DIRECTION or SPEED
		String directionOrSpeed = tokens.getNext();
		if(!Checks.checkStringIsOneOfTheseValues(directionOrSpeed, new String[] {"DIRECTION", "SPEED"})) {
			return tokens.invalidToken();
		}
		
		if(directionOrSpeed.equalsIgnoreCase("DIRECTION"))
			return setIdDirection(id);
		else if(directionOrSpeed.equalsIgnoreCase("SPEED"))
			return setIdSpeed(id);
		
		return tokens.invalidToken();
	}
	
	// setId helper methods
	
	private A_Command setIdDirection(String id) {
		// 11 DO SET id DIRECTION ( FORWARD | BACKWARD )
		
		//(FORWARD | BACKWARD)
		boolean isForward = false;
		String direction = tokens.getNext();
		if(!Checks.checkStringIsOneOfTheseValues(direction, new String[] {"FORWARD", "BACKWARD"}))
			return tokens.invalidToken();
		
		if(direction.equalsIgnoreCase("FORWARD")) {
			isForward = true;
		}
		
		return new CommandBehavioralSetDirection(id, isForward);
	}
	
	private A_Command setIdSpeed(String id) {
		// 15 DO SET id SPEED number
		
		//number
		String number = tokens.getNext();
		if(!Checks.checkStringIsDouble(number)) {
			return tokens.invalidToken();
		}
		
		double speed = Double.parseDouble(number);
		
		return new CommandBehavioralSetSpeed(id, speed);
	}
}