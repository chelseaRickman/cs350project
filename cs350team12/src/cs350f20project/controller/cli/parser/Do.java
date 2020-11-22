package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;

/*
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
			Select select = new Select(tokens);
			return select.parse();
		}
		if(token.equalsIgnoreCase("SET")) {
			Set s = new Set(tokens);
			return s.parse();
		}
		return checkArgs(token);
	}

	public A_Command brake(Tokenizer tokens) {
		String token = tokens.getNext();
		if(!isStringStandardJavVar(token.substring(0, 1)))
			return tokens.InvalidToken();
		return new CommandBehavioralBrake(token);
		
	}
	
	//Check for standard Java variable name, underscore included
	public boolean isStringStandardJavVar(String str) 
	{ 
	    return (str.matches("[a-zA-Z_$]")); 
	}
}