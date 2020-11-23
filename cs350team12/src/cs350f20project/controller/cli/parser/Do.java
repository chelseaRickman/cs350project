package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;

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
			return tokens.InvalidToken();
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
			return tokens.InvalidToken();
		else if(nextToken.equalsIgnoreCase("DRAWBRIDGE"))
			return selectDrawbridge();
		else if(nextToken.equalsIgnoreCase("ROUNDHOUSE"))
			return selectRoundhouse();
		else if(nextToken.equalsIgnoreCase("SWITCH"))
			return selectSwitch();
		
		return tokens.InvalidToken();
	}
	
	// Determines which SET method needs to be called
	public A_Command doSet() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.InvalidToken();
		if(nextToken.equalsIgnoreCase("REFERENCE"))
			return setReference();
		else {
			return setId(nextToken);
		}
	}
	
	
	public A_Command brake(Tokenizer tokens) {
		// 2  DO BRAKE id
		String token = tokens.getNext();
		if(!isStringStandardJavVar(token.substring(0, 1)))
			return tokens.InvalidToken();
		return new CommandBehavioralBrake(token);
		
	}
	
	// DO SELECT commands
	
	private A_Command selectDrawbridge() {
		// 6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
		// When entering this method, tokens.getNext() should be id
		return null;
	}
	
	private A_Command selectRoundhouse() {
		// 7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE )
		// When entering this method, tokens.getNext() should be id
		return null;
	}
	
	private A_Command selectSwitch() {
		// 8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY )
		// When entering this method, tokens.getNext() should be id
		return null;
	}
	
	// DO SET commands
	
	private A_Command setReference() {
		// 12 DO SET REFERENCE ENGINE id
		// When entering this method, tokens.getNext() should be "ENGINE"
		return null;
	}
	
	private A_Command setId(String id) {
		/* 11 DO SET id DIRECTION ( FORWARD | BACKWARD )
		 * 15 DO SET id SPEED number
		 * When entering this method, the token param should be the id at this point so ensure that is the case
		 * Can create private helper methods for each of these rules, can use Tokenizer.get(index) to see if "DIRECTION" or "SPEED"
		 * if neither "DIRECTION" or "SPEED", then invalid token
		 */
		
		return null;
	}
	
	//Check for standard Java variable name, underscore included
	public boolean isStringStandardJavVar(String str) 
	{ 
	    return (str.matches("[a-zA-Z_$]")); 
	}
	
	
}