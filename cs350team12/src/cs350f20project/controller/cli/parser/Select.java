package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
/*
6  DO SELECT DRAWBRIDGE id POSITION ( UP | DOWN )
7  DO SELECT ROUNDHOUSE id POSITION angle ( CLOCKWISE | COUNTERCLOCKWISE )
8  DO SELECT SWITCH id PATH ( PRIMARY | SECONDARY )
 */
import cs350f20project.controller.command.behavioral.CommandBehavioralSelectSwitch;

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
			//Stuff
		}
		if(token.equalsIgnoreCase("SWITCH")) {
			return switchSelect(tokens);
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
		return null;
	}
	
	
	public A_Command switchSelect(Tokenizer tokens) {
	String switchid = tokens.getNext();
	if(!tokens.getNext().equalsIgnoreCase("PATH"))
		return tokens.invalidToken();
	String check = tokens.getNext();
	boolean primorsec = tokens.booleanFromString(check, "PRIMARY", "SECONDARY");
	return new CommandBehavioralSelectSwitch(switchid, primorsec);
	}
	
}

