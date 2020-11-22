package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetDirection;

public class Set extends ParserBase{
	public Set(Tokenizer tokens) {
		super(tokens);
	}

	public A_Command parse() {
		String token = tokens.getNext();
		if(token == null)
			return tokens.invalidToken();
		if(token.equalsIgnoreCase("REFERENCE"))
			return reference();
		if(token.equalsIgnoreCase("SPEED")) {
			Speed s = new Speed(tokens);
			return s.parse();
		}
		if(token.equalsIgnoreCase("DIRECTION"))
			return direction();
		if(verifyArg(token) == true) {
			return parse();
		}
		return tokens.invalidToken();
	}
	
	public A_Command reference() {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("ENGINE")) {
			Engine e = new Engine(tokens);
			return e.setReference();
		}
			
		return null;
	}
	
	public A_Command direction() {
		String id = tokens.getArgs(0).get(0);
		if(!Checks.checkID(id)) {
			return tokens.invalidToken();
		}
		
		boolean isForward = false;
		String direction = tokens.getNext();
		if(direction.equalsIgnoreCase("FORWARD")) {
			isForward = true;
		}
		
		return new CommandBehavioralSetDirection(id, isForward);
		
	}
}
